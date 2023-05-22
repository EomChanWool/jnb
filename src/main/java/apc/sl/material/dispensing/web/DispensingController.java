package apc.sl.material.dispensing.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apc.sl.basicInfo.bom.service.BomService;
import apc.sl.material.dispensing.service.DispensingService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class DispensingController {
	@Autowired
	private DispensingService dispensingService;
	
	@Autowired
	private BomService bomService;
	
	@RequestMapping("/sl/material/dispensing/dispensingList.do")
	public String investList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = dispensingService.selectDispensingListToCnt(searchVO);
		
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
		List<?> dispensingList = dispensingService.selectDispensingList(searchVO);
		model.put("dispensingList", dispensingList);
		model.put("paginationInfo", paginationInfo);
		return "sl/material/dispensing/dispensingList";
	}
	
	@RequestMapping("/sl/material/dispensing/registDispensing.do")
	public String registInvest(ModelMap model) {
		List<?> workOrderList = dispensingService.selectWorkOrderList();
		model.put("workOrderList", workOrderList);
//		List<?> materialList = investService.selectMaterialList();
//		model.put("materialList", materialList);
		return "sl/material/dispensing/dispensingRegist";
	}
	
	@RequestMapping("/sl/material/dispensing/registDispensingOk.do")
	public String registInvestOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		dispensingService.registDispensing(map);
		
		//sm_material 재고 갱신
//		map.put("cnt", "-"+map.get("inCnt"));
//		investService.updateMaterialStock(map);
		
		//자재투입시 해당 작업지시 상태 변경
//		map.put("state", "1");
//		investService.updateWorkOrder(map);
		
		//수주 상태 변경
//		map.replace("state", "2");
//		investService.updateOrders(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/material/dispensing/dispensingList.do";
	}
	
	@RequestMapping("/sl/material/dispensing/modifyDispensing.do")
	public String modifyInvest(@RequestParam Map<String, Object> map, ModelMap model) {
		
		
		Map<String, Object> detail = dispensingService.selectDispensingInfo(map);
		
		//detail.put("curItemCd", detail.get("itemCd"));
		//detail.put("curCnt", detail.get("inCnt"));
		model.put("dispensingVO", detail);
		
		
		map.put("bIdx", detail.get("bIdx"));
		
		Map<String, Object> detailbom = bomService.selectBomInfo(map);
		model.put("bomVO", detailbom);
		String str = detailbom.toString().replaceAll("[{}]", "");
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
		
		
		return "sl/material/dispensing/dispensingModify";
	}
	
	@RequestMapping("/sl/material/dispensing/modifyDispensingOk.do")
	public String modifyInvestOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		map.put("userId", session.getAttribute("user_id"));
		dispensingService.modifyDispensing(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/material/dispensing/dispensingList.do";
	}
	
	
	
	@RequestMapping("/sl/material/dispensing/deleteDispensing.do")
	public String deleteInvest(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		
		dispensingService.deleteDispensing(map);
		
		//sm_marterial 재고 개수 갱신
//		map.put("cnt", map.get("inCnt"));
//		investService.updateMaterialStock(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/material/dispensing/dispensingList.do";
	}
}
