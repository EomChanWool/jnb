package apc.sl.quality.incongruentState.web;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apc.sl.quality.incongruentState.service.IncongruentStateService;
import apc.util.SearchVO;

@Controller
public class IncongruentStateController {
	
	@Autowired
	private IncongruentStateService incongruentStateService;
	
	@RequestMapping("/sl/quality/incongruentState/incongruentStateList.do")
	public String incongruentStateList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		if(searchVO.getSearchCondition2().equals("")) {
			searchVO.setSearchCondition2("2023");
		}
		
		List<?> inYearList = incongruentStateService.selectInYearList(searchVO);
		
		model.put("inYearList", inYearList);
		
	
		
		List<?> incoStateList = incongruentStateService.selectIncoStateList(searchVO);
		model.put("incoStateList", incoStateList);
		
		List<?> incongruityList = incongruentStateService.selectIncongruity(searchVO);
		
		model.put("incongruityList", incongruityList);
		
		
		
		
		
		return "sl/quality/incongruentState/incongruentState";
	}

}
