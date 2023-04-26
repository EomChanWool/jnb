package apc.sl.monitoring.dashBoard.web;

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

import apc.sl.monitoring.actualOutput.service.ActualOutputService;
import apc.sl.monitoring.actualOutput.web.ActualOutputController;
import apc.sl.monitoring.dashBoard.service.DashBoardService;
import apc.sl.monitoring.ordersOutput.service.OrdersOutputService;
import apc.util.SearchVO;

@Controller
public class DashBoardController {
	@Autowired
	private DashBoardService dashBoardService;
	@Autowired
	private OrdersOutputService ordersOutputService;
	@Autowired
	private ActualOutputService actualOutputService;
	
	@RequestMapping("/sl/monitoring/dashBoard.do")
	public String dashBoardList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String date = format.format(now);
		String[] temp = date.split("-");
		model.put("year", temp[0]);
		model.put("month", temp[1]);
		model.put("day", temp[2]);
		
		//수주량(제품 종류 상관없이 총 제품 수주량)
		List<?> orderCntList = ordersOutputService.selectOrdersCnt(searchVO);
		model.put("orderCntList", orderCntList);
		//생산량(제품 종류 상관없이 총 제품 생산량)
		List<?> prodCntList = ordersOutputService.selectProdCnt(searchVO);
		model.put("prodCntList",prodCntList);
		//출하량
		List<?> deliveryCntList = ordersOutputService.selectOrdersCnt(searchVO);
		model.put("deliveryCntList", deliveryCntList);
	
		//생산실적 현황
		//일일 프레스,벤딩 현황
		Map<String, Object> map = new HashMap<>();
		map.put("processNm", "프레스성형/벤딩");
		Map<String, Object> pressBendingData = actualOutputService.selectPressBendingCnt(map);
		if(pressBendingData == null) {
			pressBendingData = new HashMap<>();
			pressBendingData.put("prReCnt", 0);
		}
		model.put("pressBendingData", pressBendingData);
		//일일 PLC데이터
		Map<String, Object> plc = actualOutputService.selectPlcCnt();
		if(plc == null) {
			plc = new HashMap<>();
			plc.put("plCnt", 0);
		}
		model.put("plcData", plc);
		
		//재고현황
		List<?> itemList = dashBoardService.selectItemList();
		model.put("itemList", itemList);
		
		//공지사항
		List<?> noticeList = dashBoardService.selectNoticeList(searchVO);
		model.put("noticeList", noticeList);
		return "sl/monitoring/dashBoard/dashBoard";
	}
	
	@RequestMapping("/sl/monitoring/detailNotice.do")
	public String detailNotice(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = dashBoardService.selectNoticeInfo(map);
		model.put("noticeVO", detail);
		return "sl/monitoring/notice/detailNotice";
	}
}
