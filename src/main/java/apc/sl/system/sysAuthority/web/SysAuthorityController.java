package apc.sl.system.sysAuthority.web;

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

import apc.sl.system.sysAuthority.service.SysAuthorityService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class SysAuthorityController {
	@Autowired
	private SysAuthorityService sysAuthorityService;
	
	@RequestMapping("/sl/system/sysAuthority/sysAuthorityList.do")
	public String sysAuthorityList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = sysAuthorityService.selectSysAuthorityListToCnt(searchVO);
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
		List<?> sysAuthorityList = sysAuthorityService.selectSysAuthorityList(searchVO);
		model.put("sysAuthorityList", sysAuthorityList);
		model.put("paginationInfo", paginationInfo);
		return "sl/system/sysAuthority/sysAuthorityList";
	}
	
	@RequestMapping("/sl/system/sysAuthority/registSysAuthority.do")
	public String registSysAuthority() {
		return "sl/system/sysAuthority/sysAuthorityRegist";
	}
	
	@RequestMapping("/sl/system/sysAuthority/registSysAuthorityOk.do")
	public String registSysAuthorityOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		sysAuthorityService.registSysAuthority(map);
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/system/sysAuthority/sysAuthorityList.do";
	}
	
	@RequestMapping("/sl/system/sysAuthority/modifySysAuthority.do")
	public String modifySysAuthority(@RequestParam Map<String, Object> map, ModelMap model) {
		if(!map.isEmpty()) {
			Map<String, Object> detail = sysAuthorityService.selectSysAuthorityInfo(map);
			model.put("sysAuthorityVO", detail);
		}
		return "sl/system/sysAuthority/sysAuthorityModify";
	}
	
	@RequestMapping("/sl/system/sysAuthority/modifySysAuthorityOk.do")
	public String modifySysAuthorityOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		sysAuthorityService.modifySysAuthority(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/system/sysAuthority/sysAuthorityList.do";
	}
	
	@RequestMapping("/sl/system/sysAuthority/deleteSysAuthority.do")
	public String deleteSysAuthority(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) {
		sysAuthorityService.deleteSysAuthority(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/system/sysAuthority/sysAuthorityList.do";
	}
}
