package apc.sl.material.outsourcing.web;

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

import apc.sl.material.outsourcing.service.OutsourcingService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class OutsourcingController {
	@Autowired
	private OutsourcingService outsourcingService;
	
	@RequestMapping("/sl/material/outsourcing/outsourcingList.do")
	public String outsourcingList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = outsourcingService.selectOutsourcingListToCnt(searchVO);
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
		List<?> outSourcingList = outsourcingService.selectOutsourcingList(searchVO);
		model.put("outSourcingList", outSourcingList);
		model.put("paginationInfo", paginationInfo);
		return "sl/material/outsourcing/outsourcingList";
	}
	
	@RequestMapping("/sl/material/outsourcing/registOutsourcing.do")
	public String registOutsourcing(ModelMap model) {
		List<?> workOrderList = outsourcingService.selectWorkOrderList();
		model.put("workOrderList", workOrderList);
		return "sl/material/outsourcing/outsourcingRegist";
	}
	
	@RequestMapping(value="/sl/material/outsourcing/outSourcingItemInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView outsourcingItemInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = outsourcingService.selectWorkOrderInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("wo_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/material/outsourcing/registOutsourcingOk.do")
	public String registOutsourcingOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//해당작지 존재여부 체크
		int exists = outsourcingService.selectExistsWoIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg","존재하지 않는 작업지시번호 입니다.");
			return "redirect:/sl/material/outsourcing/registOutSourcing.do";
		}
		
		//등록이 되면 sm_work_order의 상태를 외주(3)
		map.put("state", "3");
		outsourcingService.updateWorkOrder(map);
		
		map.put("userId", session.getAttribute("user_id"));
		outsourcingService.registOutSourcing(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/material/outsourcing/outsourcingList.do";
	}
	
	@RequestMapping("/sl/material/outsourcing/modifyOutsourcing.do")
	public String modifyOutsourcing(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> workOrderList = outsourcingService.selectWorkOrderList();
		model.put("workOrderList", workOrderList);
		if(!map.isEmpty()) {
			Map<String, Object> detail = outsourcingService.selectOutsourcingInfo(map);
			detail.put("curWoIdx",detail.get("woIdx"));
			model.put("outsourcingVO", detail);
		}
		return "sl/material/outsourcing/outsourcingModify";
	}
	
	@RequestMapping("/sl/material/outsourcing/modifyOutsourcingOk.do")
	public String modifyOutsourcingOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//해당작지 존재여부 체크
		int exists = outsourcingService.selectExistsWoIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg","존재하지 않는 작업지시번호 입니다.");
			redirectAttributes.addFlashAttribute("outsourcingVO", map);
			return "redirect:/sl/material/outsourcing/modifyOutSourcing.do";
		}
		
		if(!map.get("woIdx").equals(map.get("curWoIdx"))) {
			//이전 작지의 상태를 작업완료(2)로 새로운 작지의 상태를 외주(3)로
			String woIdx = map.get("woIdx")+"";
			map.put("state","3");
			outsourcingService.updateWorkOrder(map);
			map.replace("state", "2");
			map.replace("woIdx", map.get("curWoIdx"));
			outsourcingService.updateWorkOrder(map);
			map.replace("woIdx", woIdx);
		}
		
		map.put("userId", session.getAttribute("user_id"));
		outsourcingService.modifyOutsourcing(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/material/outsourcing/outsourcingList.do";
	}
	
	@RequestMapping("/sl/material/outsourcing/deleteOutsourcing.do")
	public String deleteOutsourcing(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("state", "2");
		outsourcingService.updateWorkOrder(map);
		outsourcingService.deleteOutsourcing(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/material/outsourcing/outsourcingList.do";
	}
}
