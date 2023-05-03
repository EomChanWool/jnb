package apc.sl.production.prodPlan.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apc.sl.production.prodPlan.service.ProdPlanService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ProdPlanController {
	@Autowired
	private ProdPlanService prodPlanService;
	
	@RequestMapping("/sl/production/prodPlan/prodPlanList.do")
	public String workOrderList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = prodPlanService.selectProdPlanListToCnt(searchVO);
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
		List<?> prodPlanList = prodPlanService.selectProdPlanList(searchVO);
		model.put("prodPlanList", prodPlanList);
		model.put("paginationInfo", paginationInfo);
		return "sl/production/prodPlan/prodPlanList";
	}
	
	@RequestMapping("/sl/production/prodPlan/registProdPlan.do")
	public String registProdPlan(ModelMap model) {
		List<?> ordersList = prodPlanService.selectOrdersList();
		model.put("ordersList", ordersList);
		
		return "sl/production/prodPlan/prodPlanRegist";
	}
	
	@RequestMapping(value="/sl/production/prodPlan/prodPlanItemListAjax.do", method=RequestMethod.POST)
	public ModelAndView prodPlanItemListAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<Map<String, Object>> itemList = new ArrayList<>();
		Map<String, Object> estimateItemList = prodPlanService.selectEstimateItemList(map);
		if(estimateItemList == null) {
			Map<String, Object> temp = new HashMap<>();
			temp.put("prodCd","");
			temp.put("prodName", "");
			temp.put("prodCnt", "");
			itemList.add(temp);
			mav.setViewName("jsonView");
			mav.addObject("item_ajax", itemList);
			return mav;
		}
		
		System.out.println("이에스티 : " + estimateItemList);
		System.out.println("이에스티 사이즈 : " + estimateItemList.size());
		
		for(int i=1;i<estimateItemList.size();i+=2) {
			Map<String, Object> temp = new HashMap<>();
			int num = Math.abs(i/2)+1;
			String prod = "esProd"+num;
			String cnt = "esCnt"+num;
			String prodCd = estimateItemList.get(prod)+"";
			
			String prodCnt = estimateItemList.get(cnt)+"";
			String prodName = prodPlanService.selectItemName(prodCd);
			temp.put("prodCd", prodCd);
			temp.put("prodName", prodName);
			temp.put("prodCnt", prodCnt);
			itemList.add(temp);
		}
		mav.setViewName("jsonView");
		mav.addObject("item_ajax", itemList);
		
		return mav;
	}
	
	@RequestMapping(value="/sl/production/prodPlan/prodPlanItemCntAjax.do", method=RequestMethod.POST)
	public ModelAndView prodPlanItemCntAjax(@RequestParam Map<String, Object> map) {
		
		System.out.println("맵확인 : " + map);
		ModelAndView mav = new ModelAndView();
		Map<String, Object> temp = new HashMap<>();
		Map<String, Object> estimateItemList = prodPlanService.selectEstimateItemList(map);
		
		
		for(int i=1;i<estimateItemList.size();i+=2) {
			temp = new HashMap<>();
			int num = Math.abs(i/2)+1;
			String prod = "esProd"+num;
			String cnt = "esCnt"+num;
			String prodCd = estimateItemList.get(prod)+"";
			String prodCnt = estimateItemList.get(cnt)+"";
			if(map.get("itemCd").equals(prodCd)) {
				temp.put("itemCnt", prodCnt);
				break;
			}
		}
		mav.setViewName("jsonView");
		mav.addObject("item_cnt", temp);
		System.out.println("템프 : " + temp);
		return mav;
	}
	
	@RequestMapping("/sl/production/prodPlan/registProdPlanOk.do")
	public String registProdPlanOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//수주코드 확인
		System.out.println("맵확인2 : " + map);
		int exists = prodPlanService.selectExistsOrdersIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 수주번호입니다.");
			redirectAttributes.addFlashAttribute("prdtPlanVO", map);
			return "redirect:/sl/production/prodPlan/registProdPlan.do";
		}
		
		//품목코드 확인
		exists = prodPlanService.selectExistsItemCode(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 아이템코드 입니다.");
			redirectAttributes.addFlashAttribute("prdtPlanVO", map);
			return "redirect:/sl/production/prodPlan/registProdPlan.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		prodPlanService.registProdPlan(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/production/prodPlan/prodPlanList.do";
	}
	
	@RequestMapping("/sl/production/prodPlan/modifyProdPlan.do")
	public String modifyProdPlan(@RequestParam Map<String, Object> map, ModelMap model) {
//		List<?> itemList = prodPlanService.selectItemList();
//		model.put("itemList", itemList);
		System.out.println("생산계획수정 맵 : " + map);
		if(!map.isEmpty()) {
			Map<String, Object> detail = prodPlanService.selectProdPlanInfo(map);
			model.put("prodPlanVO", detail);
		}
		
		return "sl/production/prodPlan/prodPlanModify";
	}
	
	@RequestMapping("/sl/production/prodPlan/modifyProdPlanOk.do")
	public String modifyProdPlanOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//아이템코드 확인
		int exists = prodPlanService.selectExistsItemCode(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 아이템코드 입니다.");
			redirectAttributes.addFlashAttribute("prdtPlanVO", map);
			return "redirect:/sl/production/prodPlan/modifyProdPlan.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		prodPlanService.modifyProdPlan(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/production/prodPlan/prodPlanList.do";
	}
	
	@RequestMapping("/sl/production/prodPlan/detailProdPlan.do")
	public String detailProdPlan(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = prodPlanService.selectProdPlanInfo(map);
		model.put("prodPlanVO", detail);
		return "sl/production/prodPlan/prodPlanDetail";
	}
	
	@RequestMapping("/sl/production/prodPlan/deleteProdPlan.do")
	public String deleteProdPlan(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		prodPlanService.deleteProdPlan(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/production/prodPlan/prodPlanList.do";
	}
}
