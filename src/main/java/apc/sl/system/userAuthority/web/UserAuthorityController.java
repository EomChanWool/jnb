package apc.sl.system.userAuthority.web;

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

import apc.sl.system.userAuthority.service.UserAuthorityService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class UserAuthorityController {
	@Autowired
	private UserAuthorityService userAuthorityService;
	
	@RequestMapping("/sl/system/userAuthority/userAuthorityList.do")
	public String userAuthorityList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = userAuthorityService.selectUserAuthorityListToCnt(searchVO);
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
		List<?> userAuthorityList = userAuthorityService.selectUserAuthorityList(searchVO);
		model.put("userAuthorityList", userAuthorityList);
		model.put("paginationInfo", paginationInfo);
		return "sl/system/userAuthority/userAuthorityList";
	}
	
	@RequestMapping("/sl/system/userAuthority/registUserAuthority.do")
	public String registUserAuthority(ModelMap model) {
		List<?> memberList = userAuthorityService.selectMemberList();
		model.put("memberList", memberList);
		return "sl/system/userAuthority/userAuthorityRegist";
	}
	
	@RequestMapping("/sl/system/userAuthority/registUserAuthorityOk.do")
	public String registUserAuthorityOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//사용자ID 확인
		int exists = userAuthorityService.selectExistsMemberId(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 사용자ID 입니다.");
			redirectAttributes.addFlashAttribute("userAuthorityVO", map);
			return "redirect:/sl/system/userAuthority/registUserAuthority.do";
		}
		
		//이미 등록되었는지 체크
		exists = userAuthorityService.selectExistsUserAuthority(map);
		if(exists != 0) {
			redirectAttributes.addFlashAttribute("msg", "이미 등록된 사용자입니다.");
			redirectAttributes.addFlashAttribute("userAuthorityVO", map);
			return "redirect:/sl/system/userAuthority/registUserAuthority.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		userAuthorityService.registUserAuthority(map);
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/system/userAuthority/userAuthorityList.do";
	}
	
	@RequestMapping("/sl/system/userAuthority/modifyUserAuthority.do")
	public String modifyUserAuthority(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> memberList = userAuthorityService.selectMemberList();
		model.put("memberList", memberList);
		
		Map<String, Object> detail = userAuthorityService.selectUserAuthorityInfo(map);
		model.put("userAuthorityVO", detail);
		return "sl/system/userAuthority/userAuthorityModify";
	}
	
	@RequestMapping("/sl/system/userAuthority/modifyUserAuthorityOk.do")
	public String modifyUserAuthorityOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		userAuthorityService.modifyUserAuthority(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/system/userAuthority/userAuthorityList.do";
	}
	
	@RequestMapping("/sl/system/userAuthority/deleteUserAuthority.do")
	public String deleteUserAuthority(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) {
		userAuthorityService.deleteUserAuthority(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/system/userAuthority/userAuthorityList.do";
	}
}
