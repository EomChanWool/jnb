package apc.sl.kpi.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import apc.sl.kpi.service.KpiService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class KpiController {
	@Autowired
	private KpiService kpiService;
	
	@RequestMapping("/sl/kpi/kpiGoal/kpiGoalList.do")
	public String kpiList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		if(searchVO.getSearchCondition().equals("")) {
			searchVO.setSearchCondition("생산량");
			searchVO.setSearchCondition2(getYears().get("exYear")+"");
		}
		
		int totCnt = kpiService.selectKpiListToCnt(searchVO);
		/** pageing setting */
		searchVO.setPageSize(10);
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); // 현재 페이지 번호
		paginationInfo.setRecordCountPerPage(5); // 한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(searchVO.getPageSize()); // 페이징 리스트의 사이즈
		paginationInfo.setTotalRecordCount(totCnt);
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		List<?> kpiList = kpiService.selectKpiList(searchVO);
		model.put("kpiList", kpiList);
		model.put("paginationInfo", paginationInfo);
		model.put("date", getYears());
		
		List<?> kpiGraphList = kpiService.selectKpiGraphList(searchVO);
		model.put("kpiGraphList", kpiGraphList);
		if(searchVO.getSearchCondition().equals("생산량")) {
			//총생산량
			List<?> dataList = kpiService.selectOutput(searchVO);
			model.put("dataList", dataList);
		}else if(searchVO.getSearchCondition().equals("매출액")) {
			//매출액
			List<?> dataList = kpiService.selectSales(searchVO);
			model.put("dataList", dataList);
		}
		return "sl/kpi/kpiList";
	}
	
	
	@RequestMapping("/sl/kpi/kpiState/kpiStateList.do")
	public String graphKpi(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		if(searchVO.getSearchCondition().equals("")) {
			searchVO.setSearchCondition("생산량");
			searchVO.setSearchCondition2(getYears().get("exYear")+"");
		}
		List<?> kpiList = kpiService.selectKpiList(searchVO);
		model.put("kpiList", kpiList);
		model.put("date", getYears());
		
		List<?> kpiGraphList = kpiService.selectKpiGraphList(searchVO);
		model.put("kpiGraphList", kpiGraphList);
		if(searchVO.getSearchCondition().equals("생산량")) {
			//총생산량
			List<?> dataList = kpiService.selectOutput(searchVO);
			model.put("dataList", dataList);
		}else if(searchVO.getSearchCondition().equals("매출액")) {
			//매출액
			List<?> dataList = kpiService.selectSales(searchVO);
			model.put("dataList", dataList);
		}
		
		return "sl/kpi/kpiGraph";
	}
	
	@RequestMapping("/sl/kpi/kpiGoal/registKpi.do")
	public String registKpi(ModelMap model) {
		model.put("date", getYears());
		model.put("kpiVO", getYears());
		return "sl/kpi/kpiRegist";
	}
	
	@RequestMapping("/sl/kpi/kpiGoal/registKpiOk.do")
	public String registKpiOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		int exists = kpiService.selectExistsKpi(map);
		if(exists != 0) {
			redirectAttributes.addFlashAttribute("msg", "이미 등록된 내역입니다.");
			redirectAttributes.addFlashAttribute("kpiVO", map);
			return "redirect:/sl/kpi/kpiGoal/kpiGoalList.do";
		}
		
		kpiService.registKpi(map);
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/kpi/kpiGoal/kpiGoalList.do";
	}
	
	@RequestMapping("/sl/kpi/kpiGoal/modifyKpi.do")
	public String modifyKpi(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = kpiService.selectKpiInfo(map);
		model.put("kpiVO", detail);
		model.put("date", getYears());
		return "sl/kpi/kpiModify";
	}
	
	@RequestMapping("/sl/kpi/kpiGoal/modifyKpiOk.do")
	public String modifyKpiOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		if(!map.get("curYear").equals(map.get("exYear")) && !map.get("curMonth").equals(map.get("exMonth"))) {
			int exists = kpiService.selectExistsKpi(map);
			if(exists != 0) {
				redirectAttributes.addFlashAttribute("msg", "이미 등록된 내역입니다.");
				redirectAttributes.addFlashAttribute("kpiVO", map);
				return "redirect:/sl/kpi/kpiGoal/modifyKpi.do";
			}
		}
		kpiService.modifyKpi(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/kpi/kpiGoal/kpiGoalList.do";
	}
	
	@RequestMapping("/sl/kpi/kpiGoal/deleteKpi.do")
	public String deleteKpi(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		kpiService.deleteKpi(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/kpi/kpiGoal/kpiGoalList.do";
	}
	
	private Map<String, Object> getYears(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Date now = new Date();
		int begin = Integer.parseInt(format.format(now))-40;
		int end = begin+50;
		Map<String, Object> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("exYear", format.format(now));
		return map;
	}
}
