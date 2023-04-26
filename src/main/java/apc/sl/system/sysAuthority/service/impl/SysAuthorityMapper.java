package apc.sl.system.sysAuthority.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("SysAuthorityMapper")
public interface SysAuthorityMapper {

	int selectSysAuthorityListToCnt(SearchVO searchVO);

	List<?> selectSysAuthorityList(SearchVO searchVO);

	int selectExistsSysAuthority(Map<String, Object> map);

	void registSysAuthority(Map<String, Object> map);

	Map<String, Object> selectSysAuthorityInfo(Map<String, Object> map);

	void modifySysAuthority(Map<String, Object> map);

	void deleteSysAuthority(Map<String, Object> map);

}
