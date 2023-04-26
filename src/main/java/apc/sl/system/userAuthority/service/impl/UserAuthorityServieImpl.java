package apc.sl.system.userAuthority.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.system.userAuthority.service.UserAuthorityService;
import apc.util.SearchVO;
@Service
public class UserAuthorityServieImpl implements UserAuthorityService {
	@Resource
	private UserAuthorityMapper userAuthorityMapper;

	@Override
	public int selectUserAuthorityListToCnt(SearchVO searchVO) {
		return userAuthorityMapper.selectUserAuthorityListToCnt(searchVO);
	}

	@Override
	public List<?> selectUserAuthorityList(SearchVO searchVO) {
		return userAuthorityMapper.selectUserAuthorityList(searchVO);
	}

	@Override
	public List<?> selectMemberList() {
		return userAuthorityMapper.selectMemberList();
	}

	@Override
	public int selectExistsMemberId(Map<String, Object> map) {
		return userAuthorityMapper.selectExistsMemberId(map);
	}

	@Override
	public int selectExistsUserAuthority(Map<String, Object> map) {
		return userAuthorityMapper.selectExistsUserAuthority(map);
	}

	@Override
	public void registUserAuthority(Map<String, Object> map) {
		userAuthorityMapper.registUserAuthority(map);
	}

	@Override
	public Map<String, Object> selectUserAuthorityInfo(Map<String, Object> map) {
		return userAuthorityMapper.selectUserAuthorityInfo(map);
	}

	@Override
	public void modifyUserAuthority(Map<String, Object> map) {
		userAuthorityMapper.modifyUserAuthority(map);
	}

	@Override
	public void deleteUserAuthority(Map<String, Object> map) {
		userAuthorityMapper.deleteUserAuthority(map);
	}

}
