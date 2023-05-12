package apc.sl.monitoring.ordersOutput.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import apc.sl.monitoring.ordersOutput.service.OrdersOutputService;
import apc.util.SearchVO;

@Controller
public class OrdersOutputController {
	@Autowired
	private OrdersOutputService ordersOutputService;
	
	@RequestMapping("/sl/monitoring/ordersOutput/ordersOutput.do")
	public String actualOutput(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
//		List<?> data = ordersOutputService.selectOrderOutputData(searchVO);
//		model.put("dataList", data);
		
		if(searchVO.getSearchCondition2().equals("")) {
			searchVO.setSearchCondition2("2023");
		}
		
		//년도 목록
		List<?>orYearList = ordersOutputService.selectOrYearList(searchVO);
		model.put("orYearList", orYearList);
		
		//수주량(제품 종류 상관없이 총 제품 수주량)
		List<?> orderCntList = ordersOutputService.selectOrdersCnt(searchVO);
		model.put("orderCntList", orderCntList);
		//생산량(제품 종류 상관없이 총 제품 생산량)
		List<?> prodCntList = ordersOutputService.selectProdCnt(searchVO);
		model.put("prodCntList",prodCntList);
		//출하량
		List<?> deliveryCntList = ordersOutputService.selectOrderOutputData(searchVO);
		model.put("deliveryCntList", deliveryCntList);
		
		return "sl/monitoring/ordersOutput/ordersOutput";
	}
}
