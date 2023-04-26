package apc.sl.system.sysAuthority.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.system.sysAuthority.service.SysAuthorityService;
import apc.util.SearchVO;
@Service
public class SysAuthorityServieImpl implements SysAuthorityService {
	@Resource
	private SysAuthorityMapper sysAuthorityMapper;

	@Override
	public int selectSysAuthorityListToCnt(SearchVO searchVO) {
		return sysAuthorityMapper.selectSysAuthorityListToCnt(searchVO);
	}

	@Override
	public List<?> selectSysAuthorityList(SearchVO searchVO) {
		return sysAuthorityMapper.selectSysAuthorityList(searchVO);
	}

	@Override
	public int selectExistsSysAuthority(Map<String, Object> map) {
		return sysAuthorityMapper.selectExistsSysAuthority(map);
	}
	
	@Override
	public void registSysAuthority(Map<String, Object> map) {
		sysAuthorityMapper.registSysAuthority(map);
	}

	@Override
	public Map<String, Object> selectSysAuthorityInfo(Map<String, Object> map) {
		return sysAuthorityMapper.selectSysAuthorityInfo(map);
	}

	@Override
	public void modifySysAuthority(Map<String, Object> map) {
		sysAuthorityMapper.modifySysAuthority(map);
	}

	@Override
	public void deleteSysAuthority(Map<String, Object> map) {
		sysAuthorityMapper.deleteSysAuthority(map);
	}

}
