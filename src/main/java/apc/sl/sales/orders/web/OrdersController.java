package apc.sl.sales.orders.web;

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

import apc.sl.sales.orders.service.OrdersService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("/sl/sales/ordersManage/ordersList.do")
	public String ordersList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = ordersService.selectOrdersListToCnt(searchVO);
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
		List<?> ordersList = ordersService.selectOrdersList(searchVO);
		model.put("ordersList", ordersList);
		model.put("paginationInfo", paginationInfo);
		return "sl/sales/orders/ordersList";
	}
	
	@RequestMapping("/sl/sales/ordersManage/registOrders.do")
	public String registOrders(ModelMap model) {
		Map<String, Object> map = new HashMap<>();
		map.put("esIdx","");
		List<?> estimateList = ordersService.selectEstimateList(map);
		model.put("estimateList", estimateList);
		return "sl/sales/orders/ordersRegist";
	}
	
	@RequestMapping(value="/sl/sales/ordersManage/estimateInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView estimateInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = ordersService.selectEstimateInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("estimate_ajax", list);
		return mav;
	}
	
	@RequestMapping("/sl/sales/ordersManage/registOrdersOk.do")
	public String registOrdersOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		ordersService.registOrders(map);
		//견적서 상태 갱신
		map.put("state","1");
		ordersService.updateEstimate(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/sales/ordersManage/ordersList.do";
	}
	
	@RequestMapping("/sl/sales/ordersManage/modifyOrders.do")
	public String modifyOrders(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = ordersService.selectOrdersInfo(map);
		model.put("ordersVO", detail);
		
		List<?> estimateList = ordersService.selectEstimateList(map);
		model.put("estimateList", estimateList);
		return "sl/sales/orders/ordersModify";
	}
	
	@RequestMapping("/sl/sales/ordersManage/modifyOrdersOk.do")
	public String modifyOrdersOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		ordersService.modifyOrders(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/sales/ordersManage/ordersList.do";
	}
	
	@RequestMapping("/sl/sales/ordersManage/detailOrders.do")
	public String detailOrders(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = ordersService.selectOrdersInfo(map);
		model.put("ordersVO", detail);
		return "sl/sales/orders/ordersDetail";
	}
	
	@RequestMapping("/sl/sales/ordersManage/deleteOrders.do")
	public String deleteOrders(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		ordersService.deleteOrders(map);
		//견적서 상태 갱신
		map.put("state","0");
		ordersService.updateEstimate(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/sales/ordersManage/ordersList.do";
	}
}
