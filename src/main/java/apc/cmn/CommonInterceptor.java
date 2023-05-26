package apc.cmn;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import apc.sl.system.authority.service.AuthorityService;
import apc.util.ScriptAlert;


public class CommonInterceptor extends HandlerInterceptorAdapter {
	@Inject
	private AuthorityService authorityService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String str1 = request.getRequestURL().toString();
		String[] str2 = str1.split("/");
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("memberVO");
		//사용자 권한 레벨
		int userLev = Integer.parseInt(userMap.get("saLev")+"");
		//프로그램 접근 권한 체크
		//System.out.println("확인" + str2[5]);
		int programAccessLev = authorityService.selectProgramAccessLev(str2[5]); 
		if(session.getAttribute("user_id") == null) {
			ScriptAlert.alertAndMovePage(response, "접근 권한이 없습니다.", "/sl/main.do");
			return false;
		}else {
			if(userLev < programAccessLev) {
				ScriptAlert.alertAndBackPage(response, "접근 권한이 없습니다.");
			}
		}
		
		//System.out.println(str2.length);
		if(!str2[str2.length-1].contains("dashBoard")) {
			//CRUD권한 체크
			if(str2[6].contains("Ok") || str2[6].contains("delete")) {
				System.out.println("CRUD권한 체크");
				int crud = authorityService.selectAccess(userMap.get("mId")+"");
				if(crud == 0) {
					ScriptAlert.alertAndBackPage(response, "등록/수정/삭제 권한이 없습니다.");
				}
			}
		}
		
		return true;
	}
	
	public void goMain(HttpServletRequest request, HttpServletResponse response,String msg,String place) throws Exception {
		FlashMap flashMap = new FlashMap();
		flashMap.put("msg", msg);
		FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		response.sendRedirect(request.getContextPath()+"/"+place+"/main.do");
	}
}
