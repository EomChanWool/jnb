package apc.sl.system.progAuthority.web;

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

import apc.sl.system.progAuthority.service.ProgAuthorityService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ProgAuthorityController {
	@Autowired
	private ProgAuthorityService progAuthorityService;
	
	@RequestMapping("/sl/system/progAuthority/progAuthorityList.do")
	public String progAuthorityList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = progAuthorityService.selectProgAuthorityListToCnt(searchVO);
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
		List<?> progAuthorityList = progAuthorityService.selectProgAuthorityList(searchVO);
		model.put("progAuthorityList", progAuthorityList);
		model.put("paginationInfo", paginationInfo);
		return "sl/system/progAuthority/progAuthorityList";
	}
	
	@RequestMapping("/sl/system/progAuthority/registProgAuthority.do")
	public String registProgAuthority(ModelMap model) {
		List<?> programList = progAuthorityService.selectProgramList();
		model.put("programList", programList);
		List<?> sysAuthorityList = progAuthorityService.selectSysAuthorityList();
		model.put("sysAuthorityList", sysAuthorityList);
		return "sl/system/progAuthority/progAuthorityRegist";
	}
	
	@RequestMapping("/sl/system/progAuthority/registProgAuthorityOk.do")
	public String registProgAuthorityOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//프로그램 권한 등록전에는 사용 여부 N
		//프로그램코드 확인
		int exists = progAuthorityService.selectExistsProgramIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 프로그램 코드 입니다.");
			redirectAttributes.addFlashAttribute("progAuthorityVO", map);
			return "redirect:/sl/system/progAuthority/registProgAuthority.do";
		}
		
		//권한코드 확인
		exists = progAuthorityService.selectExistsSysAuthorityIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 권한코드 입니다.");
			redirectAttributes.addFlashAttribute("progAuthorityVO", map);
			return "redirect:/sl/system/progAuthority/registProgAuthority.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		progAuthorityService.registProgAuthority(map);
		
		//프로그램 사용으로 전환
		map.put("state", "Y");
		progAuthorityService.updateProgam(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/system/progAuthority/progAuthorityList.do";
	}
	
	@RequestMapping("/sl/system/progAuthority/modifyProgAuthority.do")
	public String modifyProgAuthority(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> programList = progAuthorityService.selectProgramList();
		model.put("programList", programList);
		List<?> sysAuthorityList = progAuthorityService.selectSysAuthorityList();
		model.put("sysAuthorityList", sysAuthorityList);
		
		Map<String, Object> detail = progAuthorityService.selectProgAuthorityInfo(map);
		model.put("progAuthorityVO", detail);
		return "sl/system/progAuthority/progAuthorityModify";
	}
	
	@RequestMapping("/sl/system/progAuthority/modifyProgAuthorityOk.do")
	public String modifyProgAuthorityOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		progAuthorityService.modifyProgAuthority(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/system/progAuthority/progAuthorityList.do";
	}
	
	@RequestMapping("/sl/system/progAuthority/deleteProgAuthority.do")
	public String deleteProgAuthority(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) {
		progAuthorityService.deleteProgAuthority(map);
		
		//프로그램 미사용으로 전환
		map.put("state", "N");
		progAuthorityService.updateProgam(map);
		
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/system/progAuthority/progAuthorityList.do";
	}
}
