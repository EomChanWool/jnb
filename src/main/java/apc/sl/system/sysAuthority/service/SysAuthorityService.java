package apc.sl.system.sysAuthority.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface SysAuthorityService {

	int selectSysAuthorityListToCnt(SearchVO searchVO);

	List<?> selectSysAuthorityList(SearchVO searchVO);

	int selectExistsSysAuthority(Map<String, Object> map);

	void registSysAuthority(Map<String, Object> map);

	Map<String, Object> selectSysAuthorityInfo(Map<String, Object> map);

	void modifySysAuthority(Map<String, Object> map);

	void deleteSysAuthority(Map<String, Object> map);

}
