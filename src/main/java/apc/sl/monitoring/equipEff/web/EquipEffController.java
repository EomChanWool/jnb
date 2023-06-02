package apc.sl.monitoring.equipEff.web;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.RequestParam;

import apc.sl.monitoring.equipEff.service.EquipEffService;
import apc.util.SearchVO;

@Controller
public class EquipEffController {
	
	@Autowired
	private EquipEffService equipEffService;
	
	
	@RequestMapping("/sl/monitoring/equipEff/equipEff.do")
	public String equipEff(@ModelAttribute("searchVO")SearchVO searchVO, ModelMap model, HttpSession session) {
		LocalDate now = LocalDate.now();
		
		
		if(searchVO.getSearchCondition2().equals("")) {
			searchVO.setSearchCondition2(now.getYear()+"");
		}
		
		List<?> plcYearList = equipEffService.selectPlcYearList(searchVO);
		
		model.put("plcYear", plcYearList);
		
		
		List<?> eq1 = equipEffService.selectEquioEffCnt1(searchVO);
		
		model.put("eq1", eq1);
		
		
		
		List<?> eq2 = equipEffService.selectEquioEffCnt2(searchVO);
		
		model.put("eq2", eq2);
		
		
		
		
		return "sl/monitoring/equipEff/equipEff";
	}
	
	
/**	@RequestMapping("/sl/monitoring/equipEff/equipEff.do")
	public String equipEff(@ModelAttribute("searchVO")SearchVO searchVO, ModelMap model, HttpSession session) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String nowFor = now.format(formatter);
		
		System.out.println(nowFor);
		
		List<Map<String, Object>> equipList = equipEffService.selectEquipEff();
		
	//	List<String> str = new ArrayList<String>();
	//	for(int i=0;i<equipList.size();i++) {
	//		Map<String, Object> temp = new HashMap<>();
	//		temp = equipList.get(i);
	//		str.add(temp.get("faRegDte")+"");		}
		System.out.println("이큅리스트 : " + equipList);
		
		
		
		//등록일부터 지금까지의 시간 (분)
		List<Long> str = new ArrayList<>();
		for (Map<String, Object> temp : equipList) {
		    String faRegDteStr = temp.get("faRegDte") + "";
		    LocalDateTime faRegDte = LocalDateTime.parse(faRegDteStr, formatter);
		    LocalDateTime nowDateTime = LocalDateTime.parse(nowFor, formatter);
		    
		    long minutesDiff = java.time.Duration.between(faRegDte, nowDateTime).toMinutes();
		    str.add(minutesDiff);
		}


		System.out.println(str);
		//설비이름
		List<String> str2 = new ArrayList<String>();
		
		for(int i=0;i<equipList.size();i++) {
			Map<String, Object> temp2 = new HashMap<>();
			temp2 = equipList.get(i);
			String status = temp2.get("faStatus")+"";
			if(status.equals("가동")) {
			str2.add(temp2.get("faName")+"");
			}
		}
		System.out.println(str2);
		model.put("faName", str2);
		//등록일 - 비가동이었던 총 시간
		List<Long> str3 = new ArrayList<>();
		
		for(int i=0;i<equipList.size();i++) {
			Map<String, Object> temp3 = new HashMap<>();
			temp3 = equipList.get(i);
			String noTime = temp3.get("noTime") +"";
			long regTime = str.get(i);
			long noTimeMin = Long.parseLong(noTime);
			
			long minTime = regTime-noTimeMin;
			str3.add(minTime);
		}
		
		System.out.println(str3);
		//가동상태 인 비율 계산
		List<Long> str4 = new ArrayList<>();
		
		for(int i=0;i<equipList.size();i++) {
			double percent = ((double) str3.get(i) /(double) str.get(i)) * 100;
			
			long percentLong = (long) percent;
			
			
			str4.add(percentLong);
		}
		
		
		model.put("percent", str4);
		
		
		
		
		return "sl/monitoring/equipEff/equipEff";
	} **/

}
