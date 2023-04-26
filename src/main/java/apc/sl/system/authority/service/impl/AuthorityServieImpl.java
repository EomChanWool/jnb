package apc.sl.system.authority.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.system.authority.service.AuthorityService;
@Service
public class AuthorityServieImpl implements AuthorityService {
	@Resource
	private AuthorityMapper authorityMapper;

	@Override
	public int selectProgramAccessLev(String str) {
		return authorityMapper.selectProgramAccessLev(str);
	}

	@Override
	public int selectAccess(String str) {
		return authorityMapper.selectAccess(str);
	}
}
