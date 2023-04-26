package apc.sl.basicInfo.storage.web;

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

import apc.sl.basicInfo.storage.service.StorageService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class StorageController {
	@Autowired
	private StorageService storageService;
	
	@RequestMapping("/sl/basicInfo/storage/storageList.do")
	public String storageList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = storageService.selectStorageListToCnt(searchVO);
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
		List<?> storageList = storageService.selectStorageList(searchVO);
		model.put("storageList", storageList);
		List<?> companyList = storageService.selectCompanyList();
		model.put("companyList", companyList);
		model.put("paginationInfo", paginationInfo);
		return "sl/basicInfo/storage/storageList";
	}
	
	@RequestMapping("/sl/basicInfo/storage/registStorage.do")
	public String registStorage(ModelMap model) {
		List<?> companyList = storageService.selectCompanyList();
		model.put("companyList", companyList);
		List<?> comnCodeList = storageService.selectComnCodeList();
		model.put("comnCodeList", comnCodeList);
		return "sl/basicInfo/storage/storageRegist";
	}
	
	@RequestMapping("/sl/basicInfo/storage/registStorageOk.do")
	public String registStorageOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//사업장코드 확인
		int exists = storageService.selectExistsCompany(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 사업장코드입니다.");
			redirectAttributes.addFlashAttribute("storageVO", map);
			return "redirect:/sl/basicInfo/storage/registStorage.do";
		}
		map.put("userId", session.getAttribute("user_id"));
		storageService.registStorage(map);
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/basicInfo/storage/storageList.do";
	}
	
	@RequestMapping("/sl/basicInfo/storage/modifyStorage.do")
	public String modifyStorage(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> companyList = storageService.selectCompanyList();
		model.put("companyList", companyList);
		List<?> comnCodeList = storageService.selectComnCodeList();
		model.put("comnCodeList", comnCodeList);
		
		if(!map.isEmpty()) {
			Map<String, Object> detail = storageService.selectStorageInfo(map);
			model.put("storageVO", detail);
		}
		return "sl/basicInfo/storage/storageModify";
	}
	
	@RequestMapping("/sl/basicInfo/storage/modifyStorageOk.do")
	public String modifyStorageOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//사업장코드 확인
		int exists = storageService.selectExistsCompany(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 사업장코드입니다.");
			redirectAttributes.addFlashAttribute("storageVO", map);
			return "redirect:/sl/basicInfo/storage/registStorage.do";
		}
		map.put("userId", session.getAttribute("user_id"));
		storageService.modifyStorage(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/basicInfo/storage/storageList.do";
	}
	
	@RequestMapping("/sl/basicInfo/storage/deleteStorage.do")
	public String deleteStorage(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		storageService.deleteStorage(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/basicInfo/storage/storageList.do";
	}
}
