package apc.sl.system.authority.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("AuthorityMapper")
public interface AuthorityMapper {

	int selectProgramAccessLev(String str);

	int selectAccess(String str);

}
