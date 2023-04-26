package apc.sl.system.userAuthority.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("UserAuthorityMapper")
public interface UserAuthorityMapper {

	int selectUserAuthorityListToCnt(SearchVO searchVO);

	List<?> selectUserAuthorityList(SearchVO searchVO);

	List<?> selectMemberList();

	int selectExistsMemberId(Map<String, Object> map);

	int selectExistsUserAuthority(Map<String, Object> map);

	void registUserAuthority(Map<String, Object> map);

	Map<String, Object> selectUserAuthorityInfo(Map<String, Object> map);

	void modifyUserAuthority(Map<String, Object> map);

	void deleteUserAuthority(Map<String, Object> map);

}
