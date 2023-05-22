package apc.sl.basicInfo.facility.web;

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

import apc.sl.basicInfo.facility.service.FacilityService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class FacilityController {

	@Autowired
	private FacilityService facilityService;
	
	@RequestMapping("/sl/basicInfo/facility/facilityList.do")
	public String FacilityList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		int totCnt = facilityService.selectFacilityListToCnt(searchVO);
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
		
		List<?> facilityList = facilityService.selectFacilityList(searchVO);
		model.put("facilityList", facilityList);
		model.put("paginationInfo", paginationInfo);
		
		return "sl/basicInfo/facility/facilityList";
	}
	
	@RequestMapping("/sl/basicInfo/facility/registFacility.do")
	public String registFactory(@RequestParam Map<String, Object> map, ModelMap model) {
		
		
		
		List<?> factoryList = facilityService.selectFactoryList();
		model.put("factoryList", factoryList);
		
		return "sl/basicInfo/facility/facilityRegist";
	}
	
	@RequestMapping("/sl/basicInfo/facility/registFacilityOk.do")
	public String registFactoryOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		//설비명 중복체크
		int exists = facilityService.selectExistsFacility(map);
		
		if(exists != 0) {
			redirectAttributes.addFlashAttribute("msg","중복되는 설비명입니다.");
			redirectAttributes.addFlashAttribute("factoryList", map);
			
			return "redirect:/sl/basicInfo/facility/registFacility.do";
		}
		map.put("userId", session.getAttribute("user_id"));
		facilityService.registFacility(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		
		return "redirect:/sl/basicInfo/facility/facilityList.do";
	}
	
	
	@RequestMapping("/sl/basicInfo/facility/modifyFacility.do")
	public String modifyFacility(@RequestParam Map<String, Object> map, ModelMap model) {
		
		
		
										
		Map<String, Object> detail = facilityService.selectFacility(map);
		
		
		
		model.put("facilityVO", detail);
		
		
		
		
		return "sl/basicInfo/facility/facilityModify";
	}
	
	@RequestMapping("/sl/basicInfo/facility/modifyFacilityOk.do")
	public String modifyFacilityOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		
		
		
		
		map.put("userId", session.getAttribute("user_id"));
		
		facilityService.modifyFacility(map);
		
		
		//비가동상태일때 비가동관리 등록
		if(map.get("faStatus").equals("비가동")) {
			facilityService.registNonOpSt(map);
		}		
		else if(map.get("faStatus").equals("가동")){
			//가동 상태일때 비가동 종료날짜 등록
			facilityService.modifyNonOp(map);
		}
		
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
						
		return "redirect:/sl/basicInfo/facility/facilityList.do";
	}
	
	
	@RequestMapping("/sl/basicInfo/facility/deleteFacility.do")
	public String deleteFacility(@RequestParam Map<String, Object>map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		facilityService.deleteFacility(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/basicInfo/facility/facilityList.do";
	}
	
	
		
	
	
	
}
