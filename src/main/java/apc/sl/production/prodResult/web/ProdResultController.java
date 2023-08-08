package apc.sl.production.prodResult.web;

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

import apc.sl.production.prodResult.service.ProdResultService;
import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ProdResultController {
	@Autowired
	private ProdResultService prodResultService;
	
	@RequestMapping("/sl/production/prodResult/prodResultList.do")
	public String prodResultList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = prodResultService.selectProdResultListToCnt(searchVO);
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
		List<?> prResultList = prodResultService.selectProdResultList(searchVO);
		model.put("prResultList", prResultList);
		model.put("paginationInfo", paginationInfo);
		return "sl/production/prodResult/prodResultList";
	}
	
	@RequestMapping("/sl/production/prodResult/registProdResult.do")
	public String registProdResult(ModelMap model) {
		List<?> woList = prodResultService.selectWorkOrderList();
		model.put("woList", woList);
		return "sl/production/prodResult/prodResultRegist";
	}
	
	@RequestMapping(value="/sl/production/prodResult/prReWorkOrderInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView prReWorkOrderInfoAjax(@RequestParam Map<String, Object> map) {
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> list = prodResultService.selectWorkOrderInfo(map);
		Map<String, Object> list2 = prodResultService.selectPrReReSt(map);
		mav.setViewName("jsonView");
		mav.addObject("wo_info", list);
		mav.addObject("pr_info", list2);

		
		return mav;
	}
	
	@RequestMapping("/sl/production/prodResult/registProdResultOk.do")
	public String registProdResultOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//품질검사 공정의 경우 품질관리-검사관리에서 등록시 자동 등록됨
		if(map.get("prListNm").equals("품질검사")) {
			redirectAttributes.addFlashAttribute("msg", "품질검사는 품질관리-검사관리에서 등록 바랍니다.");
			return "redirect:/sl/production/prodResult/prodResultList.do";
		}
		
		//작업지시번호 체크
		int exists = prodResultService.selectExistsWorkOrderIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg","존재하지 않는 작업지시번호 입니다.");
			redirectAttributes.addFlashAttribute("prodResultVO", map);
			return "redirect:/sl/production/prodResult/registProdResult.do";
		}
		
		//이미 등록되었는지 체크
		exists = prodResultService.selectExistsProdResult(map);
		if(exists != 0) {
			redirectAttributes.addFlashAttribute("msg", "이미 존재하는 내역입니다.");
			redirectAttributes.addFlashAttribute("prodResultVO", map);
			return "redirect:/sl/production/prodResult/registProdResult.do";
		}
		
		//BOM 유무 검사
		exists = prodResultService.selectExistsItemBom(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "해당 제품의 BOM이 존재하지 않습니다.");
			return "redirect:/sl/production/prodResult/prodResultList.do";
		}
		
		//map.put("userId", session.getAttribute("user_id"));
		
		
		
		
		//작업상태가 작업종료일때 sm_process갱신
		if(map.get("prReState").equals("1")) {
			//작지에 해당하는 아이템코드의 BOM정보를 토대로 자재의 재고 관리 (재고가 부족할 시 alert)
			if(map.get("prListNm").equals("원자재 이송")) {
				List<?> prodPercentList = prodResultService.selectProdPercentList(map);
				String str = updateMaterialStock(prodPercentList, redirectAttributes);
				if(!str.isEmpty()) {
					redirectAttributes.addFlashAttribute("msg", str+"의 재고가 부족합니다.");
					return "redirect:/sl/production/prodResult/registProdResult.do";
				}
			}
			
		
			
			//modify = true --> sm_process에서 현재 공정 순서,idx,nm 등 수정하겠음.
			map.put("modify","true");
			Map<String, Object> process = prodResultService.selectProcessSeqInfo(map);
			int processSeq = Integer.parseInt(process.get("prCurSeq")+"");
			map.put("curSeq", processSeq);
			map.put("curStDte", "pr_st_time"+processSeq);
			map.put("curEdDte", "pr_ed_time"+processSeq);
			map.put("curCnt", "pr_cnt"+processSeq);
			map.put("curFaulty", "pr_faulty"+processSeq);
			map.put("nextIdx", "pr_list_idx"+(processSeq+1));
			map.put("nextNm", "pr_list_nm"+(processSeq+1));
			map.put("userId", session.getAttribute("user_id"));
			updateProcess(process,map);
			prodResultService.registProdResult(map);
			prodResultService.updateProcess(map);
			if(map.get("prListNm").equals("원자재 이송")) {
				
				prodResultService.registDispensing(map); // 불출관리 등록;
			}
			
			if(map.get("prListNm").equals("저장탱크 이송")) {
				
				
				prodResultService.updateProduct(map); //제품 재고 업데이트
			}
			
			redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
			return "redirect:/sl/production/prodResult/prodResultList.do";
			
		}
		
		prodResultService.registProdResult(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/production/prodResult/prodResultList.do";
	}
	
	@RequestMapping("/sl/production/prodResult/modifyProdResult.do")
	public String modifyProdResult(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> woList = prodResultService.selectWorkOrderList();
		model.put("woList", woList);
		Map<String, Object> detail = prodResultService.selectProdResultInfo(map);
		model.put("prodResultVO", detail);
		return "sl/production/prodResult/prodResultModify";
	}
	
	@RequestMapping("/sl/production/prodResult/modifyProdResultOk.do")
	public String modifyProdResultOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		prodResultService.modifyProdResult(map);
		//작업상태가 작업종료일때 sm_process갱신
		if(map.get("prReState").equals("1")) {
			//작지에 해당하는 아이템코드의 BOM정보를 토대로 자재의 재고 관리 (재고가 부족할 시 alert)
			if(map.get("prListNm").equals("원자재 이송")) {
				List<?> prodPercentList = prodResultService.selectProdPercentList(map);
				updateMaterialStock(prodPercentList, redirectAttributes);
			}
			
			//modify = false --> sm_process에서 수정하고자 하는 순서의 공정시작시간, 공정종료시간, 생산수량, 불량수량만 갱신
			map.put("modify","false");
			Map<String, Object> process = prodResultService.selectProcessSeqInfo(map);
			int processSeq = Integer.parseInt(map.get("curSeq")+"");
			map.put("curStDte", "pr_st_time"+processSeq);
			map.put("curEdDte", "pr_ed_time"+processSeq);
			map.put("curCnt", "pr_cnt"+processSeq);
			map.put("curFaulty", "pr_faulty"+processSeq);
			
			if(map.get("curPrReState").equals("0") && map.get("prReState").equals("1")) {
				//이전 상태가 작업중이였다가 작업완료로 변경되었을 경우
				map.replace("modify", "true");
				map.put("nextIdx", "pr_list_idx"+(processSeq+1));
				map.put("nextNm", "pr_list_nm"+(processSeq+1));
			}
			
			if(map.get("curPrReState").equals("0") && map.get("prReState").equals("1")) {
				updateProcess(process,map);
			}
			map.put("userId", session.getAttribute("user_id"));
			prodResultService.updateProcess(map);
		}
		
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/production/prodResult/prodResultList.do";
	}
	
	@RequestMapping("/sl/production/prodResult/deleteProdResult.do")
	public String deleteProdResultOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		prodResultService.deleteProdResult(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/production/prodResult/prodResultList.do";
	}
	
	private String updateMaterialStock(List<?> list, RedirectAttributes redirectAttributes) {
		
		
		
		 EgovMap str1 = (EgovMap) list.get(0);
	

		
		//String[] str1 = list.get(0).toString().split(", ");
		
		//int total = Integer.parseInt(str1[0].split("=")[1]);
		int total = Integer.parseInt(str1.get("woPdtCnt")+"");
//		String woIdx = str1[1].split("=")[1];
		String woIdx = str1.get("woIdx")+"";
		//재고확인
		//for(int i=2;i<str1.length;i+=2) {
			//Map<String, Object> map = new HashMap<>();
			//String[] temp1 = str1[i].split("=");
			//String[] temp2 = str1[i+1].split("=");
//			float cnt = total/100*(Float.parseFloat(temp2[1].replace("}", "")));
//			map.put("itemCd", temp1[1]);
//			map.put("cnt", cnt);
//			Map<String, Object> enough = prodResultService.selectEnoughStock(map);
//			int num = Integer.parseInt(enough.get("count")+"");
//			if(num == 1) {
				//재고가 없으면 자재명 리턴
//				return enough.get("itemName")+"";
//			}
//		}
		
		for(int i=2; i<str1.size(); i+=2) {
			Map<String, Object> map = new HashMap<>();
			
			float cnt = total/100*(Float.parseFloat(str1.get(str1.get(i+1))+""));
			map.put("itemCd", str1.get(str1.get(i))+"");
			map.put("cnt", cnt);
			Map<String, Object> enough = prodResultService.selectEnoughStock(map);
			int num = Integer.parseInt(enough.get("count")+"");
			if(num == 1) {
				//재고가 없으면 자재명 리턴
				return enough.get("itemName")+"";
			}
		}
		//재고 갱신
//		for(int i=2;i<str1.length;i+=2) {
//			Map<String, Object> map = new HashMap<>();
//			String[] temp1 = str1[i].split("=");
//			String[] temp2 = str1[i+1].split("=");
//			float cnt = total/100*(Float.parseFloat(temp2[1].replace("}", "")));
//			map.put("itemCd", temp1[1]);
//			map.put("cnt", cnt);
//			prodResultService.updateItem(map);
//		}
		
		for(int i=2;i<str1.size();i+=2) {
			
			Map<String, Object> map = new HashMap<>();
			
			float cnt = total/100*(Float.parseFloat(str1.get(str1.get(i+1))+""));
			map.put("itemCd", str1.get(str1.get(i))+"");
			map.put("cnt", cnt);
			prodResultService.updateItem(map);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("woIdx", woIdx);
		map.put("state", "1");
		prodResultService.updateWorkOrder(map);
		
		return "";
	}
	
	private void updateProcess(Map<String, Object> process, Map<String, Object> map) {
		//완료된 공정이 마지막 공정이면 sm_work_order, sm_orders 상태를 작업 완료로
		String lastProcessNm = prodResultService.selectLastProcessNm(map);
		if(process.get("prCurNm") != null) {
			if(process.get("prCurNm").equals(lastProcessNm)) {
				//sm_work_order 상태 갱신
				map.put("state", "2");
				prodResultService.updateWorkOrder(map);
				//sm_orders 상태 갱신
				prodResultService.updateOrders(map);
			}
		}
	}
}
