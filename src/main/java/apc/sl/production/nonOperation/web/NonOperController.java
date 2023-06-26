package apc.sl.production.nonOperation.web;

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

import apc.sl.production.nonOperation.service.NonOperService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class NonOperController {
	@Autowired
	private NonOperService nonOperService;
	
	@RequestMapping("/sl/production/nonOper/nonOperList.do")
	public String prodResultList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = nonOperService.selectNonOperListToCnt(searchVO);
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
		List<?> nonOperList = nonOperService.selectNonOperList(searchVO);
		model.put("nonOperList", nonOperList);
		
		//사업장 목록
		List<?> companyList = nonOperService.selectCompanyList();
		model.put("companyList", companyList);
		
		
		model.put("paginationInfo", paginationInfo);
		return "sl/production/nonOper/nonOperList";
	}
	
	@RequestMapping(value="/sl/production/nonOper/nonOperFactoryListAjax.do", method=RequestMethod.POST)
	public ModelAndView nonOperFactoryListAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> factoryList = nonOperService.selectFactoryList(map);
		mav.setViewName("jsonView");
		mav.addObject("fList_ajax", factoryList);
		return mav;
	}
	
	@RequestMapping(value="/sl/production/nonOper/nonOperFacilityListAjax.do", method=RequestMethod.POST)
	public ModelAndView nonOperFacilityListAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> facilityList = nonOperService.selectFacilityList(map);
		mav.setViewName("jsonView");
		mav.addObject("faList_ajax", facilityList);
		return mav;
	}
	
	@RequestMapping("/sl/production/nonOper/registNonOper.do")
	public String registNonOper(ModelMap model) {
		List<?> companyList = nonOperService.selectCompanyList();
		model.put("companyList", companyList);
		return "sl/production/nonOper/nonOperRegist";
	}
	
	@RequestMapping("/sl/production/nonOper/registNonOperOk.do")
	public String registNonOperOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		if(existsCodeCheck(map, redirectAttributes)) {
			return "redirect:/sl/production/nonOper/registNonOper.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		nonOperService.registNonOper(map);
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/production/nonOper/nonOperList.do";
	}
	
	@RequestMapping("/sl/production/nonOper/modifyNonOper.do")
	public String modifyNonOper(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> companyList = nonOperService.selectCompanyList();
		model.put("companyList", companyList);
		
		if(!map.isEmpty()) {
			Map<String, Object> detail = nonOperService.selectNonOperInfo(map);
			model.put("nonOperVO", detail);
		}
		return "sl/production/nonOper/nonOperModify";
	}
	
	@RequestMapping("/sl/production/nonOper/modifyNonOperOk.do")
	public String modifyNonOperOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		if(existsCodeCheck(map, redirectAttributes)) {
			return "redirect:/sl/production/nonOper/modifyNonOper.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		nonOperService.modifyNonOper(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/production/nonOper/nonOperList.do";
	}
	
	@RequestMapping("/sl/production/nonOper/deleteNonOper.do")
	public String deleteNonOper(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		nonOperService.deleteNonOper(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/production/nonOper/nonOperList.do";
	}
	
	private boolean existsCodeCheck(Map<String, Object> map, RedirectAttributes redirectAttributes) {
		//사업장 코드 확인
		int exists = nonOperService.selectCompanyIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 사업장코드 입니다.");
			redirectAttributes.addFlashAttribute("nonOperVO", map);
			return true;
		}
		//공장 코드 확인
		exists = nonOperService.selectFactoryCode(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 공장코드 입니다.");
			redirectAttributes.addFlashAttribute("nonOperVO", map);
			return true;
		}
		//설비 코드 확인
		exists = nonOperService.selectFacilityIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 설비코드 입니다.");
			redirectAttributes.addFlashAttribute("nonOperVO", map);
			return true;
		}
		
		return false;
	}
}
