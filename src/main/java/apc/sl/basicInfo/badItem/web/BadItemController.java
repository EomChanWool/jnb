package apc.sl.basicInfo.badItem.web;

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

import apc.sl.basicInfo.badItem.service.BadItemService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BadItemController {
	@Autowired
	private BadItemService badItemService;
	
	@RequestMapping("/sl/basicInfo/badItem/badItemList.do")
	public String BadItemList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = badItemService.selectBadItemListToCnt(searchVO);
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
		List<?> badItemList = badItemService.selectBadItemList(searchVO);
		model.put("badItemList", badItemList);
		List<?> badCateList = badItemService.selectBadCateList();
		model.put("badCateList", badCateList);
		model.put("paginationInfo", paginationInfo);
		return "sl/basicInfo/badItem/badItemList";
	}
	
	@RequestMapping("/sl/basicInfo/badItem/registBadItem.do")
	public String registBadItem() {
		return "sl/basicInfo/badItem/badItemRegist";
	}
	
	@RequestMapping("/sl/basicInfo/badItem/registBadItemOk.do")
	public String registBadItemOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		badItemService.registBadItem(map);
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/basicInfo/badItem/badItemList.do";
	}
	
	@RequestMapping("/sl/basicInfo/badItem/modifyBadItem.do")
	public String modifyBadItem(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = badItemService.selectBadItemInfo(map);
		model.put("biVO", detail);
		return "sl/basicInfo/badItem/badItemModify";
	}
	
	@RequestMapping("/sl/basicInfo/badItem/modifyBadItemOk.do")
	public String modifyBadItemOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		badItemService.modifyBadItem(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/basicInfo/badItem/badItemList.do";
	}
	
	@RequestMapping("/sl/basicInfo/badItem/deleteBadItem.do")
	public String deleteBadItem(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) {
		badItemService.deleteBadItem(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/basicInfo/badItem/badItemList.do";
	}
}
