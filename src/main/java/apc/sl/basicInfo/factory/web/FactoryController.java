package apc.sl.basicInfo.factory.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apc.sl.basicInfo.factory.service.FactoryService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class FactoryController {
	@Autowired
	private FactoryService factoryService;
	
	@RequestMapping("/sl/basicInfo/factory/factoryList.do")
	public String factoryList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = factoryService.selectFactoryListToCnt(searchVO);
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
		
		List<?> factoryList = factoryService.selectFactoryList(searchVO);
		model.put("factoryList", factoryList);
		model.put("paginationInfo", paginationInfo);
		
		return "sl/basicInfo/factory/factoryList";
	}
	
	@RequestMapping("/sl/basicInfo/factory/registFactory.do")
	public String registFactory(ModelMap model) {
		List<?> companyList = factoryService.selectCompanyList();
		model.put("companyList", companyList);
		List<?> comnCodeList = factoryService.selectComnCodeList();
		model.put("comnCodeList", comnCodeList);
		return "sl/basicInfo/factory/factoryRegist";
	}
	
	@RequestMapping("/sl/basicInfo/factory/registFactoryOk.do")
	public String registFactoryOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//사업장코드 체크
		int exists = factoryService.selectExistsCompany(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg","존재하지 않는 사업장코드 입니다.");
			redirectAttributes.addFlashAttribute("factoryVO", map);
			return "redirect:/sl/basicInfo/factory/registFactory.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		factoryService.registFactory(map);
		
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/factory/factoryList.do";
	}
	
	@RequestMapping("/sl/basicInfo/factory/modifyFactory.do")
	public String modifyFactory(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> companyList = factoryService.selectCompanyList();
		model.put("companyList", companyList);
		List<?> comnCodeList = factoryService.selectComnCodeList();
		model.put("comnCodeList", comnCodeList);
		
		Map<String, Object> detail = factoryService.selectFactory(map);
		model.put("factoryVO", detail);
		return "sl/basicInfo/factory/factoryModify";
	}
	
	@RequestMapping("/sl/basicInfo/factory/modifyFactoryOk.do")
	public String modifyFactoryOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//사업장코드 체크
		int exists = factoryService.selectExistsCompany(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg","존재하지 않는 사업장코드 입니다.");
			redirectAttributes.addFlashAttribute("factoryVO", map);
			return "redirect:/sl/basicInfo/factory/registFactory.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		factoryService.modifyFactory(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/basicInfo/factory/factoryList.do";
	}
	
	@RequestMapping("/sl/basicInfo/factory/deleteFactory.do")
	public String deleteFactory(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		factoryService.deleteFactory(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/basicInfo/factory/factoryList.do";
	}
}
