package apc.sl.basicInfo.member.web;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apc.sl.basicInfo.member.service.MemberService;
import apc.util.SHA256;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private SHA256 sha256;
	@Resource
	private Properties fileProperties;
	
	@RequestMapping("/sl/login.do")
	public String login(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		Map<String, Object> member = memberService.selectMember(map);
		if(member == null) {
			redirectAttributes.addFlashAttribute("msg", "아이디가없습니다");
			return "redirect:/sl/main.do";
		} else {
			if(!sha256.encrypt(map.get("password")+"").equals(member.get("mPwd")+"")) {
				redirectAttributes.addFlashAttribute("member",member);
				redirectAttributes.addFlashAttribute("msg", "비밀번호불일치");
				return "redirect:/sl/main.do";
			}
		}
		
		//로그인 성공시 세션값 부여
		session.setAttribute("memberVO", member);
		session.setAttribute("user_id", member.get("mId"));
		session.setMaxInactiveInterval(0);
		
		//시스템로그 기록
		member.put("ip", getUserIp());
		member.put("note", "로그인");
		memberService.insertSystemLog(member);
		
//		if(Integer.parseInt(member.get("mLev")+"") == 2) {
//			return "redirect:/sl/basicInfo/member/memberList.do";
//		}else {
//			return "redirect:/sl/material/income/incomeList.do";
//		}
		return "redirect:/sl/basicInfo/member/memberList.do";
	}
	
	@RequestMapping("/sl/logout.do")
	public String logout(@RequestParam Map<String,Object> info, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("memberVO");
		
		//시스템로그 기록
		Map<String, Object> log = (Map<String, Object>) session.getAttribute("memberVO");
		log.put("ip", getUserIp());
		log.put("note","로그아웃");
		memberService.insertSystemLog(log);
		
		if(obj != null) {
			session.removeAttribute("memberVO");
			session.removeAttribute("user_id");
		}
		
		redirectAttributes.addFlashAttribute("msg", "로그아웃되었습니다");
		return "redirect:/sl/main.do";
	}
	
	@RequestMapping("/sl/basicInfo/member/memberList.do")
	public String memberList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = memberService.selectMemberListToCnt(searchVO);
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
		List<?> memberList = memberService.selectMemberList(searchVO);
		model.put("memberList", memberList);
		model.put("paginationInfo", paginationInfo);
		return "sl/basicInfo/member/memberList";
	}
	
	@RequestMapping("/sl/basicInfo/member/registMember.do")
	public String registMember(ModelMap model) {
		List<?> authorityList = memberService.selectAuthorityList();
		model.put("authorityList", authorityList);
		return "sl/basicInfo/member/memberRegist";
	}
	
	@RequestMapping(value="/sl/basicInfo/member/idChkAjax.do", method = RequestMethod.POST)
	public ModelAndView selectidChkAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String exists = memberService.selectId(map);
		mav.setViewName("jsonView");
		mav.addObject("id",exists);
		return mav;
	}
	
	@RequestMapping("/sl/basicInfo/member/registMemberOk.do")
	public String registMemberOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
		//권한확인
		int exists = memberService.selectExistsAuthority(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 권한레벨 입니다.");
			redirectAttributes.addFlashAttribute("memVO", map);
			return "redirect:/sl/basicInfo/member/registMember.do";
		}
		
		String pwd = sha256.encrypt(map.get("mPwd").toString());
		map.put("mPwd", pwd);
		memberService.registMemberOk(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/member/memberList.do";
	}
	
	@RequestMapping("/sl/basicInfo/member/modifyMember.do")
	public String modifyMember(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> authorityList = memberService.selectAuthorityList();
		model.put("authorityList", authorityList);
		if(!map.isEmpty()) {
			Map<String, Object> detail = memberService.selectMemberInfo(map);
			model.put("memVO", detail);
		}
		return "sl/basicInfo/member/memberModify";
	}
	
	@RequestMapping("/sl/basicInfo/member/modifyMemberOk.do")
	public String modifyMemberOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
		//권한확인
		int exists = memberService.selectExistsAuthority(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 권한레벨 입니다.");
			redirectAttributes.addFlashAttribute("memVO", map);
			return "redirect:/sl/basicInfo/member/modifyMember.do";
		}
				
		String pwd = sha256.encrypt(map.get("mPwd").toString());
		map.put("mPwd", pwd);
		memberService.modifyMember(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/basicInfo/member/memberList.do";
	}
	
	@RequestMapping("/sl/basicInfo/member/deleteMember.do")
	public String deleteMember(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) {
		memberService.deleteMember(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/basicInfo/member/memberList.do";
	}
	
	public String getUserIp() throws Exception {
		String ip = null;
        HttpServletRequest request = 
        ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        ip = request.getHeader("X-Forwarded-For");
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-Real-IP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-RealIP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }
        if(ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
        	InetAddress address = InetAddress.getLocalHost();
        	ip = address.getHostAddress();
        }
		return ip;
	}
}
