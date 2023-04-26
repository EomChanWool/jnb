package apc.sl.system.comnCode.web;

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

import apc.sl.system.comnCode.service.ComnCodeService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ComnCodeController {
	@Autowired
	private ComnCodeService comnCodeService;
	
	@RequestMapping("/sl/system/comnCode/comnCodeList.do")
	public String comnCodeList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = comnCodeService.selectComnCodeListToCnt(searchVO);
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
		List<?> comnCodeList = comnCodeService.selectComnCodeList(searchVO);
		model.put("comnCodeList", comnCodeList);
		model.put("paginationInfo", paginationInfo);
		return "sl/system/comnCode/comnCodeList";
	}
	
	@RequestMapping("/sl/system/comnCode/registComnCode.do")
	public String registComnCode() {
		return "sl/system/comnCode/comnCodeRegist";
	}
	
	@RequestMapping("/sl/system/comnCode/registComnCodeOk.do")
	public String registComnCodeOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		comnCodeService.registComnCode(map);
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/system/comnCode/comnCodeList.do";
	}
	
	@RequestMapping("/sl/system/comnCode/modifyComnCode.do")
	public String modifyComnCode(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = comnCodeService.selectComnCodeInfo(map);
		model.put("comnCodeVO", detail);
		return "sl/system/comnCode/comnCodeModify";
	}
	
	@RequestMapping("/sl/system/comnCode/modifyComnCodeOk.do")
	public String modifyComnCodeOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		comnCodeService.modifyComnCode(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/system/comnCode/comnCodeList.do";
	}
	
	@RequestMapping("/sl/system/comnCode/deleteComnCode.do")
	public String deleteComnCode(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) {
		comnCodeService.deleteComnCode(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/system/comnCode/comnCodeList.do";
	}
}
