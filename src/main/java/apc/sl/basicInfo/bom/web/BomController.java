package apc.sl.basicInfo.bom.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apc.sl.basicInfo.bom.service.BomService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BomController {
	@Autowired
	private BomService bomService;
	
	@RequestMapping("/sl/basicInfo/bom/bomList.do")
	public String storageList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = bomService.selectBomListToCnt(searchVO);
		/** pageing setting */
		searchVO.setPageSize(10);
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); // 현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10); // 한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(searchVO.getPageSize()); // 페이징 리스트의 사이즈
		paginationInfo.setTotalRecordCount(totCnt);
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		List<?> bomList = bomService.selectBomList(searchVO);
		model.put("bomList", bomList);
		model.put("paginationInfo", paginationInfo);
		return "sl/basicInfo/BOM/bomList";
	}
	
	@RequestMapping("/sl/basicInfo/bom/registBom.do")
	public String registBom(ModelMap model) {
		List<?> prodList = bomService.selectProdList();
		model.put("prodList", prodList);
		List<?> materialList = bomService.selectMaterialList();
		model.put("materialList", materialList);
		return "sl/basicInfo/BOM/bomRegist";
	}
	
	@RequestMapping(value="/sl/sales/bom/bomItemInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView bomItemInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = bomService.selectItemInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("item_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/basicInfo/bom/registBomOk.do")
	public String registBomOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//제품코드 존재여부 체크
		int exists = bomService.selectExistsProdCd(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 제품코드 입니다.");
			redirectAttributes.addFlashAttribute("bomVO", map);
			return "redirect:/sl/basicInfo/bom/registBom.do";
		}
		
		//이미 등록여부 체크
		exists = bomService.selectExistsBom(map);
		if(exists == 1) {
			redirectAttributes.addFlashAttribute("msg", "이미 등록된 제품코드 입니다.");
			redirectAttributes.addFlashAttribute("bomVO", map);
			return "redirect:/sl/basicInfo/bom/registBom.do";
		}

		map.put("userId", session.getAttribute("user_id"));
		bomService.registBom(map);
		String bIdx = bomService.selectBomIdx();
		map.put("bIdx", bIdx);
		bomService.registBomInfo(map);
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/basicInfo/bom/bomList.do";
	}
	
	@RequestMapping("/sl/basicInfo/bom/modifyBom.do")
	public String modifyBom(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> materialList = bomService.selectMaterialList();
		model.put("materialList", materialList);
		
		Map<String, Object> detail = bomService.selectBomInfo(map);
		model.put("bomVO", detail);
		String str = detail.toString().replaceAll("[{}]", "");
		String[] temp1 = str.split(", ");
		List<Map<String, Object>> bomMtList = new ArrayList<>();
		
		for(int i=2;i<temp1.length;i+=3) {
			Map<String, Object> tempMap = new HashMap<>();
			String itemCd = temp1[i].split("=")[1];
			String itemName = temp1[i+1].split("=")[1];
			String cnt = temp1[i+2].split("=")[1];
			tempMap.put("itemCd", itemCd);
			tempMap.put("itemName", itemName);
			tempMap.put("cnt", cnt);
			bomMtList.add(tempMap);
		}
		model.put("bomMtList", bomMtList);
		
		return "sl/basicInfo/BOM/bomModify";
	}
	
	@RequestMapping("/sl/basicInfo/bom/modifyBomOk.do")
	public String modifyBomOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		bomService.modifyBom(map);
		bomService.modifyBomInfo(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/basicInfo/bom/bomList.do";
	}
	
	@RequestMapping("/sl/basicInfo/bom/deleteBom.do")
	public String deleteBom(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		bomService.deleteBom(map);
		bomService.deleteBomInfo(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/basicInfo/bom/bomList.do";
	}
}
