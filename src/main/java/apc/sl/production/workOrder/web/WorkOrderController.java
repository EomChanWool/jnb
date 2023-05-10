package apc.sl.production.workOrder.web;

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

import apc.sl.production.workOrder.service.WorkOrderService;
import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class WorkOrderController {
	@Autowired
	private WorkOrderService workOrderService;
	
	@RequestMapping("/sl/production/workOrder/workOrderList.do")
	public String workOrderList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = workOrderService.selectWorkOrderListToCnt(searchVO);
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
		List<?> workOrderList = workOrderService.selectWorkOrderList(searchVO);
		model.put("workOrderList", workOrderList);
		model.put("paginationInfo", paginationInfo);
		return "sl/production/workOrder/workOrderList";
	}
	
	@RequestMapping("/sl/production/workOrder/registWorkOrder.do")
	public String registWorkOrder(ModelMap model) {
		List<?> prodPlanList = workOrderService.selectProdPlanList();
		model.put("prodPlanList", prodPlanList);
		List<?> processList = workOrderService.selectProcessGroupList();
		model.put("processList", processList);
		return "sl/production/workOrder/workOrderRegist";
	}
	
	@RequestMapping(value="/sl/production/workOrder/workOrderProdPlanInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView workOrderProdPlanInfoAjax(@RequestParam Map<String, Object> map) {
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> info = workOrderService.selectWoProdPlanInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("pp_info", info);
		System.out.println("작업지시 등록 맵 : " + mav);
		return mav;
	}
	
	@RequestMapping("/sl/production/workOrder/registWorkOrderOk.do")
	public String registWorkOrderOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//생산계획코드 체크
		int exists = workOrderService.selectExistsProdPlan(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg","존재하지 않는 생산계획코드 입니다.");
			redirectAttributes.addFlashAttribute("workOrderVO", map);
			return "redirect:/sl/production/workOrder/registWorkOrder.do";
		}
		
		//제품번호 체크
		exists = workOrderService.selectItemCode(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 제품코드 입니다.");
			redirectAttributes.addFlashAttribute("workOrderVO", map);
			return "redirect:/sl/production/workOrder/registWorkOrder.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		workOrderService.registWorkOrder(map);
		
		//해당 작지에 대한 공정목록 생성
		createProcess(map, session);
		
		//생산계획 상태변경
		map.put("state", "1");
		workOrderService.updateProdPlan(map);
		
		//수주정보 상태변경
		workOrderService.updateOrder(map);
		
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/production/workOrder/workOrderList.do";
	}
	
	@RequestMapping("/sl/production/workOrder/modifyWorkOrder.do")
	public String modifyWorkOrder(@RequestParam Map<String, Object> map, ModelMap model) {
		System.out.println("작업지시 수정 : " + map);
		if(!map.isEmpty()) {
			Map<String, Object> detail = workOrderService.selectWorkOrderInfo(map);
			model.put("workOrderVO", detail);
		}
		
		return "sl/production/workOrder/workOrderModify";
	}
	
	@RequestMapping("/sl/production/workOrder/modifyWorkOrderOk.do")
	public String modifyWorkOrderOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		workOrderService.modifyWorkOrder(map);
		
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/production/workOrder/workOrderList.do";
	}
	
	@RequestMapping("/sl/production/workOrder/detailWorkOrder.do")
	public String deatail(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = workOrderService.selectWorkOrderInfo(map);
		model.put("workOrderVO", detail);
		return "sl/production/workOrder/workOrderDetail";
	}
	
	@RequestMapping("/sl/production/workOrder/deleteWorkOrder.do")
	public String deleteWorkOrder(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		workOrderService.deleteWorkOrder(map);
		
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/production/workOrder/workOrderList.do";
	}
	
	private void createProcess(Map<String, Object> map, HttpSession session) {
		//해당 작지에 대한 공정목록 생성
		System.out.println("프로세스 : " + map);
		map.put("group", map.get("process"));
		System.out.println(map.get("process"));
		List<?> processList = workOrderService.selectProcessList(map);
		System.out.println(processList);
		Map<String, Object> process = new HashMap<>();
		for(int i=0;i<processList.size();i++) {
			/*
			 * System.out.println("확인 : " + processList.get(i).toString()); EgovMap eg =
			 * (EgovMap)processList.get(i); String strValue = eg.get("prListIdx")+"";
			 * System.out.println(strValue);
			 */
			EgovMap eg = (EgovMap) processList.get(i);
			String idx = eg.get("prListIdx")+"";
			String nm = eg.get("prListNm")+"";
			process.put("idx"+(i+1) , idx);
			process.put("nm"+(i+1), nm);
			
			
			//String[] str1 = processList.get(i).toString().split(", ");
			//String[] idx = str1[0].split("="); //ex [pr_list_idx, PR-001-001]
			//String[] nm = str1[2].split("="); //ex [pr_list_nm, 원자재 이송]
			//process.put("idx"+(i+1), idx[1]); //ex PR-001-001
			//process.put("nm"+(i+1), nm[1].substring(0, nm[1].length()-1)); // 원자재 이송
		}
		process.put("woItnDte", map.get("woItnDte"));
		process.put("totCnt", processList.size());
		process.put("userId", session.getAttribute("user_id"));
		workOrderService.registProcess(process);
	}
}
