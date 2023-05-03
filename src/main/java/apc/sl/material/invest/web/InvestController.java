package apc.sl.material.invest.web;

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

import apc.sl.material.invest.service.InvestService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class InvestController {
	@Autowired
	private InvestService investService;
	
	@RequestMapping("/sl/material/invest/investList.do")
	public String investList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = investService.selectInvestListToCnt(searchVO);
		
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
		List<?> investList = investService.selectInvestList(searchVO);
		model.put("investList", investList);
		model.put("paginationInfo", paginationInfo);
		return "sl/material/invest/investList";
	}
	
	@RequestMapping("/sl/material/invest/registInvest.do")
	public String registInvest(ModelMap model) {
		List<?> workOrderList = investService.selectWorkOrderList();
		model.put("workOrderList", workOrderList);
		List<?> materialList = investService.selectMaterialList();
		model.put("materialList", materialList);
		return "sl/material/invest/investRegist";
	}
	
	@RequestMapping("/sl/material/invest/registInvestOk.do")
	public String registInvestOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		investService.registInvest(map);
		
		//sm_material 재고 갱신
		map.put("cnt", "-"+map.get("inCnt"));
		investService.updateMaterialStock(map);
		
		//자재투입시 해당 작업지시 상태 변경
		map.put("state", "1");
		investService.updateWorkOrder(map);
		
		//수주 상태 변경
//		map.replace("state", "2");
//		investService.updateOrders(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/material/invest/investList.do";
	}
	
	@RequestMapping("/sl/material/invest/modifyInvest.do")
	public String modifyInvest(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = investService.selectInvestInfo(map);
		detail.put("curItemCd", detail.get("itemCd"));
		detail.put("curCnt", detail.get("inCnt"));
		model.put("investVO", detail);
		List<?> workOrderList = investService.selectWorkOrderList();
		model.put("workOrderList", workOrderList);
		List<?> materialList = investService.selectMaterialList();
		model.put("materialList", materialList);
		
		return "sl/material/invest/investModify";
	}
	
	@RequestMapping("/sl/material/invest/modifyInvestOk.do")
	public String modifyInvestOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		String itemCd = map.get("itemCd")+"";
		map.put("cnt", "");
		if(!map.get("curItemCd").equals(map.get("itemCd"))) {
			/*
			 * sm_material 재고 개수 갱신
			 * 자재가 바뀌었을때 이전 자재의 재고는 원래대로
			 */
			map.replace("cnt", map.get("curCnt"));
			map.replace("itemCd", map.get("curItemCd"));
			investService.updateMaterialStock(map);
			//변경된 자재의 재고는 빼줌
			map.replace("cnt", "-"+map.get("inCnt"));
			map.replace("itemCd", itemCd);
			investService.updateMaterialStock(map);
		}else {
			//자재가 안바뀌고 수량만 변경 되었을때
			int cnt = Integer.parseInt(map.get("curCnt")+"") - Integer.parseInt(map.get("inCnt")+"");
			map.replace("cnt", cnt);
			investService.updateMaterialStock(map);
		}
		String woIdx = map.get("woIdx")+"";
		map.put("state", "");
		//이전 작지번호에 대한 자재투입 이력이 이전에 더 존재하면 상태는 그대로
		int exists = investService.selectInvestWorkOrder(map);
		if(exists == 1) {
			map.replace("state", "0");
			map.replace("woIdx", map.get("curWoIdx"));
			investService.updateWorkOrder(map);
		}
		map.replace("state", "1");
		map.replace("woIdx", woIdx);
		investService.updateWorkOrder(map);
		map.put("userId", session.getAttribute("user_id"));
		investService.modifyInvest(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/material/invest/investList.do";
	}
	
	@RequestMapping("/sl/material/invest/detailInvest.do")
	public String detailInvest(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = investService.selectInvestInfo(map);
		model.put("investVO", detail);
		return "sl/material/invest/investDetail";
	}
	
	@RequestMapping("/sl/material/invest/deleteInvest.do")
	public String deleteInvest(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		investService.deleteInvest(map);
		
		//sm_marterial 재고 개수 갱신
		map.put("cnt", map.get("inCnt"));
		investService.updateMaterialStock(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/material/invest/investList.do";
	}
}
