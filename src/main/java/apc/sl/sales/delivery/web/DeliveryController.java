package apc.sl.sales.delivery.web;

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

import apc.sl.sales.delivery.service.DeliveryService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class DeliveryController {
	@Autowired
	private DeliveryService deliveryService;
	
	@RequestMapping("/sl/sales/delivery/deliveryList.do")
	public String deliveryList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = deliveryService.selectDeliveryListToCnt(searchVO);
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
		List<?> deliveryList = deliveryService.selectDeliveryList(searchVO);
		model.put("deliveryList", deliveryList);
		model.put("paginationInfo", paginationInfo);
		return "sl/sales/delivery/deliveryList";
	}
	
	@RequestMapping("/sl/sales/delivery/registDelivery.do")
	public String registDelivery(ModelMap model) {
		List<?> orderList = deliveryService.selectOrdersList();
		model.put("orderList", orderList);
		return "sl/sales/delivery/deliveryRegist";
	}
	
	@RequestMapping(value="/sl/sales/delivery/deliveryOrdersInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView deliveryOrdersInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> list = deliveryService.selectOrdersInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("orders_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/sales/delivery/registDeliveryOk.do")
	public String registDeliveryOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session){
		//수주번호 체크
		int exists = deliveryService.selectOrdersIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 수주번호 입니다.");
			map.replace("orIdx", "");
			redirectAttributes.addFlashAttribute("deliveryVO", map);
			return "redirect:/sl/sales/delivery/registDelivery.do";
		}
		map.put("userId", session.getAttribute("user_id"));
		deliveryService.registDelivery(map);
		
		//sm_orders의 or_state상태를 출하로(3)
		map.put("state","3");
		deliveryService.updateOrders(map);
		
	
		if(map.get("deState").equals("1")) {
			
		
			deliveryService.updateProduct(map);
		}
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/sales/delivery/deliveryList.do";
	}
	
	@RequestMapping("/sl/sales/delivery/modifyDelivery.do")
	public String modifyDelivery(@RequestParam Map<String, Object> map, ModelMap model) {
		if(!map.isEmpty()) {
			Map<String, Object> detail = deliveryService.selectDeliveryInfo(map);
			detail.put("curOrIdx", detail.get("orIdx"));
			model.put("deliveryVO", detail);
		}
		return "sl/sales/delivery/deliveryModify";
	}
	
	@RequestMapping("/sl/sales/delivery/modifyDeliveryOk.do")
	public String modifyDeliveryOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		deliveryService.modifyDelivery(map);
		
		
		
		  if(map.get("deState").equals("1")) {
		  
		  
		  deliveryService.updateProduct(map); }
		 
		  else if(map.get("deState").equals("0")) {
			  
			  
			  deliveryService.updateProduct2(map); }
		
		
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/sales/delivery/deliveryList.do";
	}
	
	@RequestMapping("/sl/sales/delivery/detailDelivery.do")
	public String detailDelivery(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = deliveryService.selectDeliveryInfo(map);
		model.put("deliveryVO", detail);
	
		return "sl/sales/delivery/deliveryDetail";
	}
	
	@RequestMapping("/sl/sales/delivery/deleteDelivery.do")
	public String deleteDelivery(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		deliveryService.deleteDelivery(map);

		map.put("state", "2");
		deliveryService.updateOrders(map);
		
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/sales/delivery/deliveryList.do";
	}
}
