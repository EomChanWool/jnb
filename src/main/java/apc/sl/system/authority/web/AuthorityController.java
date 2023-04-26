package apc.sl.system.authority.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import apc.sl.system.authority.service.AuthorityService;

@Controller
public class AuthorityController {
	@Autowired
	private AuthorityService authorityService;
	
}
