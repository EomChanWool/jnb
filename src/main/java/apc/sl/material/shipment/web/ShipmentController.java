package apc.sl.material.shipment.web;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apc.sl.material.shipment.service.ShipmentService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ShipmentController {
	@Autowired
	private ShipmentService shipmentService;
	
	private String filePath = "resources/conf/images/picture/";
	
	@RequestMapping("/sl/material/shipment/shipmentList.do")
	public String shipmentList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = shipmentService.selectShipmentListToCnt(searchVO);
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
		List<?> shipmentList = shipmentService.selectShipmentList(searchVO);
		model.put("shipmentList", shipmentList);
		model.put("paginationInfo", paginationInfo);
		
		return "sl/material/shipment/shipmentList";
	}
	
	@RequestMapping("/sl/material/shipment/registShipment.do")
	public String registShipment(ModelMap model) {
		List<?> orderList = shipmentService.selectOrdersList();
		model.put("orderList", orderList);
		return "sl/material/shipment/shipmentRegist";
	}
	
	@RequestMapping(value="/sl/material/shipment/shipmentOrdersInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView shipmentOrdersInfoAjax(@RequestParam Map<String, Object> map) {
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> list = shipmentService.selectOrdersInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("orders_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/material/shipment/registShipmentOk.do")
	public String registShipmentOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session){
		//수주번호 체크
		int exists = shipmentService.selectOrdersIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 수주번호 입니다.");
			map.replace("orIdx", "");
			redirectAttributes.addFlashAttribute("shipmentVO", map);
			return "redirect:/sl/material/shipment/registShipment.do";
		}
		map.put("userId", session.getAttribute("user_id"));
		shipmentService.registShipment(map);
		
		//sm_orders의 or_state상태를 출하로(3)
		map.put("state","3");
		shipmentService.updateOrders(map);
	
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/material/shipment/shipmentList.do";
	}
	
	@RequestMapping("/sl/material/shipment/modifyShipment.do")
	public String modifyShipment(@RequestParam Map<String, Object> map, ModelMap model) {
		System.out.println("출고 수정 맵 확인 : " + map.toString());
		if(!map.isEmpty()) {
			Map<String, Object> detail = shipmentService.selectShipmentInfo(map);
			detail.put("curOrIdx", detail.get("orIdx"));
			model.put("shipmentVO", detail);
		}
		return "sl/material/shipment/shipmentModify";
	}
	
	@RequestMapping("/sl/material/shipment/modifyShipmentOk.do")
	public String modifyShipmentOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		
		System.out.println("모디파이오케이 : " + map.toString());
		
		shipmentService.modifyShipment(map);
		
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/material/shipment/shipmentList.do";
	}
	
	@RequestMapping("/sl/material/shipment/detailShipment.do")
	public String detailShipment(@RequestParam Map<String, Object> map, ModelMap model) {
		
		Map<String, Object> detail = shipmentService.selectShipmentInfo(map);
		model.put("shipmentVO", detail);
		return "sl/material/shipment/shipmentDetail";
	}
	
	@RequestMapping("/sl/material/shipment/deleteShipment.do")
	public String deleteShipment(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		shipmentService.deleteShipment(map);

		map.put("state", "2");
		
		shipmentService.updateOrders(map);
		
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/material/shipment/shipmentList.do";
	}
}
