package apc.sl.system.program.web;

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

import apc.sl.system.program.service.ProgramService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ProgramController {
	@Autowired
	private ProgramService programService;
	
	@RequestMapping("/sl/system/program/programList.do")
	public String programList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = programService.selectProgramListToCnt(searchVO);
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
		List<?> programList = programService.selectProgramList(searchVO);
		model.put("programList", programList);
		model.put("paginationInfo", paginationInfo);
		return "sl/system/program/programList";
	}
	
	@RequestMapping("/sl/system/program/registProgram.do")
	public String registProgram() {
		return "sl/system/program/programRegist";
	}
	
	@RequestMapping("/sl/system/program/registProgramOk.do")
	public String registProgramOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		programService.registProgram(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/system/program/programList.do";
	}
	
	@RequestMapping("/sl/system/program/modifyProgram.do")
	public String modifyProgram(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = programService.selectProgramInfo(map);
		model.put("programVO", detail);
		return "sl/system/program/programModify";
	}
	
	@RequestMapping("/sl/system/program/modifyProgramOk.do")
	public String modifyProgramOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		programService.modifyProgram(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/system/program/programList.do";
	}
	
	@RequestMapping("/sl/system/program/deleteProgram.do")
	public String deleteProgram(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		programService.deleteProgram(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/system/program/programList.do";
	}
}
