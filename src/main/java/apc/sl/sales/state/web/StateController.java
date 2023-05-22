package apc.sl.sales.state.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import apc.sl.sales.state.service.StateService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class StateController {

	@Autowired
	private StateService stateService;
	
	
	@RequestMapping("/sl/sales/state/stateList.do")
	public String stateList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		int totCnt = stateService.selectStateListToCnt(searchVO);
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
		
		List<?> stateList = stateService.selectStateList(searchVO);
		model.put("stateList", stateList);
		
		model.put("paginationInfo", paginationInfo);
		
		return "sl/sales/state/stateList";
	}
	
	@RequestMapping("/sl/sales/state/noPayList.do")
	public String noPayList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = stateService.selectNoPayListToCnt(searchVO);
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
		
		List<?> noPayList =  stateService.selectNoPayList(searchVO);
		model.put("noPayList", noPayList);
		model.put("paginationInfo", paginationInfo);
		
		
		return "sl/sales/state/noPayList";
	}
	
	@RequestMapping("/sl/sales/state/stateMonthList.do")
	public String stateMonthList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		int totCnt = stateService.selectStateMonthListToCnt(searchVO);
		
		/** pageing setting */
		searchVO.setPageSize(10);
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); // 현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10); // 한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(searchVO.getPageSize()); // 페이징 리스트의 사이즈
		paginationInfo.setTotalRecordCount(totCnt);
		
		if(searchVO.getSearchCondition2().equals("")) {
			searchVO.setSearchCondition2("2023");
		}
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<?> stateMonthList = stateService.selectStateMonthList(searchVO);
		List<?> orYearList = stateService.selectOrYearList(searchVO);
		model.put("stateMonthList", stateMonthList);
		model.put("orYearList", orYearList);
		model.put("paginationInfo", paginationInfo);
		
		
		return "sl/sales/state/stateMonthList";
	}
	
	@RequestMapping("/sl/sales/state/deliveryStateList.do")
	public String deliveryStateList(ModelMap model, HttpSession session) {
		
		List<?> deliveryStateList = stateService.selectDeliveryStateList();
		
		model.put("deliveryStateList", deliveryStateList);
		
		return "sl/sales/state/deliveryStateList";
	}
	
}
