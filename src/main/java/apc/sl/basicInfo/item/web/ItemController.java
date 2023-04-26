package apc.sl.basicInfo.item.web;

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

import apc.sl.basicInfo.item.service.ItemService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/sl/basicInfo/item/itemList.do")
	public String itemList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = itemService.selectItemListToCnt(searchVO);
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
		List<?> itemList = itemService.selectItemList(searchVO);
		model.put("itemList", itemList);
		List<?> companyList = itemService.selectCompanyList();
		model.put("companyList", companyList);
		model.put("paginationInfo", paginationInfo);
		return "sl/basicInfo/item/itemList";
	}
	
	@RequestMapping("/sl/basicInfo/item/registItem.do")
	public String registItem(ModelMap model) {
		List<?> companyList = itemService.selectCompanyList();
		model.put("companyList", companyList);
		List<?> comnCodeList = itemService.selectComnCodeList();
		model.put("comnCodeList", comnCodeList);
		List<?> accountList = itemService.selectAccountList();
		model.put("accountList", accountList);
		
		return "sl/basicInfo/item/itemRegist";
	}
	
	@RequestMapping("/sl/basicInfo/item/registItemOk.do")
	public String registItemOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//사업장확인
		int exists = itemService.selectExistsCompany(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 사업장코드입니다.");
			redirectAttributes.addFlashAttribute("itemVO", map);
			return "redirect:/sl/basicInfo/item/registItem.do";
		}
		
		if(map.get("itemType").equals("제품")) {
			map.put("type","PD");
		}else if(map.get("itemType").equals("자재")) {
			map.put("type","MT");
		}
		
		map.put("userId", session.getAttribute("user_id"));
		itemService.registItem(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/item/itemList.do";
	}
	
	@RequestMapping("/sl/basicInfo/item/modifyItem.do")
	public String modifyItem(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> companyList = itemService.selectCompanyList();
		model.put("companyList", companyList);
		List<?> comnCodeList = itemService.selectComnCodeList();
		model.put("comnCodeList", comnCodeList);
		
		Map<String, Object> detail = itemService.selectItemInfo(map);
		model.put("itemVO", detail);
		return "sl/basicInfo/item/itemModify";
	}
	
	@RequestMapping("/sl/basicInfo/item/modifyItemOk.do")
	public String modifyItemOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//사업장확인
		int exists = itemService.selectExistsCompany(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 사업장코드입니다.");
			redirectAttributes.addFlashAttribute("itemVO", map);
			return "redirect:/sl/basicInfo/item/registItem.do";
		}
				
		map.put("userid", session.getAttribute("user_id"));
		itemService.modifyItem(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/basicInfo/item/itemList.do";
	}
	
	@RequestMapping("/sl/basicInfo/item/deleteItem.do")
	public String deleteItem(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		itemService.deleteItem(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/basicInfo/item/itemList.do";
	}
}
