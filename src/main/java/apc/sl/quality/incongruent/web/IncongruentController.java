package apc.sl.quality.incongruent.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import apc.util.SearchVO;

@Controller
public class IncongruentController {

	@RequestMapping("/sl/quality/incongruent/incongruentList.do")
	public String incongruentList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		
		
		
		
		return "";
	}
	
}
