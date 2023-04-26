package apc.sl.system.progAuthority.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.system.progAuthority.service.ProgAuthorityService;
import apc.util.SearchVO;
@Service
public class ProgAuthorityServieImpl implements ProgAuthorityService {
	@Resource
	private ProgAuthorityMapper progAuthorityMapper;

	@Override
	public int selectProgAuthorityListToCnt(SearchVO searchVO) {
		return progAuthorityMapper.selectProgAuthorityListToCnt(searchVO);
	}

	@Override
	public List<?> selectProgAuthorityList(SearchVO searchVO) {
		return progAuthorityMapper.selectProgAuthorityList(searchVO);
	}

	@Override
	public List<?> selectProgramList() {
		return progAuthorityMapper.selectProgramList();
	}

	@Override
	public List<?> selectSysAuthorityList() {
		return progAuthorityMapper.selectSysAuthorityList();
	}

	@Override
	public int selectExistsProgramIdx(Map<String, Object> map) {
		return progAuthorityMapper.selectExistsProgramIdx(map);
	}

	@Override
	public int selectExistsSysAuthorityIdx(Map<String, Object> map) {
		return progAuthorityMapper.selectExistsSysAuthorityIdx(map);
	}

	@Override
	public void registProgAuthority(Map<String, Object> map) {
		progAuthorityMapper.registProgAuthority(map);
	}

	@Override
	public void updateProgam(Map<String, Object> map) {
		progAuthorityMapper.updateProgam(map);
	}

	@Override
	public Map<String, Object> selectProgAuthorityInfo(Map<String, Object> map) {
		return progAuthorityMapper.selectProgAuthorityInfo(map);
	}

	@Override
	public void modifyProgAuthority(Map<String, Object> map) {
		progAuthorityMapper.modifyProgAuthority(map);
	}

	@Override
	public void deleteProgAuthority(Map<String, Object> map) {
		progAuthorityMapper.deleteProgAuthority(map);
	}
	
}
