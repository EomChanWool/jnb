package apc.sl.monitoring.prodAggregate.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import apc.sl.monitoring.prodAggregate.service.ProdAggregateService;
import apc.util.SearchVO;

@Controller
public class ProdAggregateController {
	
	@Autowired
	private ProdAggregateService prodAggregateService;
	
	@RequestMapping("/sl/monitoring/prodAggregate/prodAggregate.do")
	public String ProdAggregate(@ModelAttribute("searchVO")SearchVO searchVO, ModelMap model, HttpSession session) {
		
		if(searchVO.getSearchCondition2().equals("")) {
			searchVO.setSearchCondition2("2023");
		}
		
		//생산완료시점 년수
		List<?> prYearList = prodAggregateService.selectPrYearList(searchVO);
		model.put("prYearList", prYearList);
		
		//생산건수
		List<?> prodList = prodAggregateService.selectProd(searchVO);
		model.put("prodList", prodList);
		
		
		
		return "sl/monitoring/prodAggregate/prodAggregate";
	}

}
