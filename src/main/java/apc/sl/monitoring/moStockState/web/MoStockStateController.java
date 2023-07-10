package apc.sl.monitoring.moStockState.web;

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

import apc.sl.material.stockAdjust.service.StockAdjustService;
import apc.sl.material.stockState.service.StockStateService;
import apc.sl.monitoring.actualOutput.service.ActualOutputService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MoStockStateController {
	@Autowired
	private StockStateService stockStateService;
	
	@Autowired
	private StockAdjustService stockAdjustService;
	
	@RequestMapping("/sl/monitoring/moStockState/moStockState.do")
	public String stockStateList1(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		if(searchVO.getSearchCondition() == "") {
			searchVO.setSearchCondition("제품");
		}
		
		int totCnt = stockStateService.selectStockStateListToCnt(searchVO);
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
		List<?> stockStateList = stockStateService.selectMoStockStateList(searchVO);
		model.put("stockStateList", stockStateList);
		model.put("paginationInfo", paginationInfo);
		
		return "sl/monitoring/stockState/stockState";
	}
	
	@RequestMapping("/sl/monitoring/moStockState/modifyMoStockState.do")
	public String modifyMoStockState(@RequestParam Map<String, Object> map, ModelMap model) {
		
		Map<String, Object> detail = stockStateService.selectMoStoDetail(map);
		String type = "자재";
		List<?> materialList = stockAdjustService.selectItemList(type);
		int loType = 2;
		List<?> tankList = stockAdjustService.selectTankLocation(loType);
		model.put("tankList", tankList);
		model.put("materialList", materialList);
		model.put("MoStoDetail", detail);
		
		return "sl/monitoring/stockState/stockStateModify";
	}
	
	@RequestMapping("/sl/monitoring/moStockState/modifyMoStockStateOk.do")
	public String modifyMoStockStateOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		map.put("userId",session.getAttribute("user_id"));
		
		stockStateService.updateStockPro(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		
		return "redirect:/sl/monitoring/moStockState/moStockState.do";
	}
}
