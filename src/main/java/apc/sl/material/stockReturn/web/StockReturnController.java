package apc.sl.material.stockReturn.web;

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


import apc.sl.material.stockReturn.service.StockReturnSerivce;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class StockReturnController {
	
	@Autowired
	private StockReturnSerivce stockReturnSerivce;

	
	@RequestMapping("/sl/material/stockReturn/stockReturnList.do")
	public String stockReturnList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		int totCnt = stockReturnSerivce.selectStockReturnListToCnt(searchVO);
		
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
		
		List<?> stockReturnList = stockReturnSerivce.selectStockReturnList(searchVO);
		
		model.put("stockReturnList", stockReturnList);
		model.put("paginationInfo", paginationInfo);
		return "sl/material/stockReturn/stockReturnList";
	}
	
	@RequestMapping("/sl/material/stockReturn/registStockReturn.do")
	public String registStockReturn(ModelMap model) {
		
		List<?> woList = stockReturnSerivce.selectWoList();
		
		model.put("woList",woList);
		
		List<?> materialList = stockReturnSerivce.selectMaterialList();
		model.put("materialList", materialList);
		
		return "sl/material/stockReturn/stockReturnRegist";
	}
	
	@RequestMapping(value="/sl/material/stockReturn/stockReturnInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView stockReturnInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> list = stockReturnSerivce.selectInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("woInfo", list);
		
		return mav;
	}
	
	@RequestMapping(value="/sl/material/stockReturn/bomItemInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView BomItemInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = stockReturnSerivce.selectItemInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("item_info", list);		
		System.out.println("리스트값 : " + list);
		return mav;
	}
	
	
	
	@RequestMapping("/sl/material/stockReturn/registStockReturnOk.do")
	public String registStockReturnOk(@RequestParam Map<String, Object> map , RedirectAttributes redirectAttributes, HttpSession session) {
		
		int exists = stockReturnSerivce.selectWoIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 작업지시번호 입니다.");
			map.replace("tiIdx", "");
			redirectAttributes.addFlashAttribute("stVO",map);
			return "redirect:/sl/material/stockReturn/registStockReturn.do";
		}
		
		
		
		
		
		map.put("userId", session.getAttribute("user_id"));
		
		stockReturnSerivce.registStockReturn(map);
		
		//재고 갱신
				for(int i=1;i<=10;i++) {
					if(map.get("itemCd"+i) == null) break;
					
					Map<String, Object> temp = new HashMap<>();
					temp.put("itemCd", map.get("itemCd"+i));
					temp.put("cnt", "-"+map.get("cnt"+i));
					stockReturnSerivce.updateMaterialStock(temp);
				}
		//작업지시서 생산량 수정	
				stockReturnSerivce.updateWorkOrderCnt(map);
				
		//생산실적 상태변경, 프로세스 진행상태 변경		
			if(map.get("woGroup").equals("혼합")) {
				stockReturnSerivce.updateProdResult(map);
				stockReturnSerivce.updateProcessSet(map);
			}
			
			if(map.get("woGroup").equals("혼합/반응")) {
				System.out.println("이프문안에 공정 : " + map.get("woGroup"));
				stockReturnSerivce.updateProdResult2(map);
				stockReturnSerivce.updateProcessSet2(map);
			}
				
		
		redirectAttributes.addFlashAttribute("msg", "등록되었습니다.");
		
		
		return "redirect:/sl/material/stockReturn/stockReturnList.do";
	}
	
	@RequestMapping("/sl/material/stockReturn/modifyStockReturn.do")
	public String modifyStockReturn(@RequestParam Map<String, Object> map, ModelMap model) {
		
		Map<String, Object> detail = stockReturnSerivce.selectDetailInfo(map);
		
		model.put("streVO", detail);
		
		String str = detail.toString().replaceAll("[{}]", "");
		String[] temp1 = str.split(", ");
		List<Map<String, Object>> bomMtList = new ArrayList<>();
		
		
		
		for(int i=9;i<temp1.length;i+=3) {
			Map<String, Object> tempMap = new HashMap<>();
			String itemCd = temp1[i].split("=")[1];
			String itemName = temp1[i+1].split("=")[1];
			String cnt = temp1[i+2].split("=")[1];
			tempMap.put("itemCd", itemCd);
			tempMap.put("itemName", itemName);
			tempMap.put("cnt", cnt);
			bomMtList.add(tempMap);
		}
		
		model.put("itemList", bomMtList);
		
		
		
		return "sl/material/stockReturn/stockReturnModify";
	}
	
	@RequestMapping("/sl/material/stockReturn/modifyStockReturnOk.do")
	public String modifyStockReturnOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		map.put("userId", session.getAttribute("user_id"));
		stockReturnSerivce.modifyStockReturn(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		
		
		return "redirect:/sl/material/stockReturn/stockReturnList.do";
	}
	
	@RequestMapping("/sl/material/stockReturn/detailStockReturn.do")
	public String detailStockReturn(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = stockReturnSerivce.selectDetailInfo(map);
		model.put("stockReturnVO", detail);
		
		String str = detail.toString().replaceAll("[{}]", "");
		String[] temp1 = str.split(", ");
		List<Map<String, Object>> bomMtList = new ArrayList<>();
		
		System.out.println("렝쓰확인 : " + temp1.length);
		
		for(int i=9;i<temp1.length;i+=3) {
			Map<String, Object> tempMap = new HashMap<>();
			String itemCd = temp1[i].split("=")[1];
			String itemName = temp1[i+1].split("=")[1];
			String cnt = temp1[i+2].split("=")[1];
			tempMap.put("itemCd", itemCd);
			tempMap.put("itemName", itemName);
			tempMap.put("cnt", cnt);
			bomMtList.add(tempMap);
		}
		
		model.put("itemList", bomMtList);
		
		
		
		return "sl/material/stockReturn/stockReturnDetail";
	}
	
	
	@RequestMapping("/sl/material/stockReturn/deleteStockReturn.do")
	public String deleteStockReturn(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		stockReturnSerivce.deleteStockReturn(map);
		
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/material/stockReturn/stockReturnList.do";
	}
}
