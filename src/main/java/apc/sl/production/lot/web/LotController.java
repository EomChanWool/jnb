package apc.sl.production.lot.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import apc.sl.production.lot.service.LotService;
import apc.util.SearchVO;

@Controller
public class LotController {

	@Autowired LotService lotService;
	
	
	@RequestMapping("/sl/production/lot/lotList.do")
	public String lotList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		
		return "";
	}
}
