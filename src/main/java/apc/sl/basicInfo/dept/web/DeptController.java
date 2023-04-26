package apc.sl.basicInfo.dept.web;

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

import apc.sl.basicInfo.dept.service.DeptService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/sl/basicInfo/dept/deptList.do")
	public String deptList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = deptService.selectDeptListToCnt(searchVO);
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
		
		List<?> deptList = deptService.selectDeptList(searchVO);
		model.put("deptList", deptList);
		model.put("paginationInfo", paginationInfo);
		
		return "sl/basicInfo/dept/deptList";
	}
	
	@RequestMapping("/sl/basicInfo/dept/registDept.do")
	public String registDept(ModelMap model) {
		List<?> companyList = deptService.selectCompanyList();
		model.put("companyList", companyList);
		List<?> comnCodeList = deptService.selectComnCodeList();
		model.put("comnCodeList", comnCodeList);
		return "sl/basicInfo/dept/deptRegist";
	}
	
	@RequestMapping("/sl/basicInfo/dept/registdeptOk.do")
	public String registDeptOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//사업장확인
		int exists = deptService.selectExistsCompany(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 사업장입니다.");
			redirectAttributes.addFlashAttribute("deptVO", map);
			return "redirect:/sl/basicInfo/dept/registDept.do";
		}
		
		map.put("ccName", "부서");
		map.put("userId", session.getAttribute("user_id"));
		deptService.registDept(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/dept/deptList.do";
	}
	
	@RequestMapping("/sl/basicInfo/dept/modifyDept.do")
	public String modifyDept(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> comnCodeList = deptService.selectComnCodeList();
		model.put("comnCodeList", comnCodeList);
		
		Map<String, Object> detail = deptService.selectDeptInfo(map);
		model.put("deptVO", detail);
		return "sl/basicInfo/dept/deptModify";
	}
	
	@RequestMapping("/sl/basicInfo/dept/modifydeptOk.do")
	public String modifyDeptOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		deptService.modifyDept(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/basicInfo/dept/deptList.do";
	}
	
	@RequestMapping("/sl/basicInfo/dept/deleteDept.do")
	public String deleteDept(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) {
		deptService.deleteDept(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/basicInfo/dept/deptList.do";
	}
}
