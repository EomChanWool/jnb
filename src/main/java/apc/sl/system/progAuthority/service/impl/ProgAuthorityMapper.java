package apc.sl.system.progAuthority.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ProgAuthorityMapper")
public interface ProgAuthorityMapper {

	int selectProgAuthorityListToCnt(SearchVO searchVO);

	List<?> selectProgAuthorityList(SearchVO searchVO);

	List<?> selectProgramList();

	List<?> selectSysAuthorityList();

	int selectExistsProgramIdx(Map<String, Object> map);

	int selectExistsSysAuthorityIdx(Map<String, Object> map);

	void registProgAuthority(Map<String, Object> map);

	void updateProgam(Map<String, Object> map);

	Map<String, Object> selectProgAuthorityInfo(Map<String, Object> map);

	void modifyProgAuthority(Map<String, Object> map);

	void deleteProgAuthority(Map<String, Object> map);

}
