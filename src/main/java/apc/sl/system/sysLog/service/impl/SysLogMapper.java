package apc.sl.system.sysLog.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("SysLogMapper")
public interface SysLogMapper {

	int selectSysLogListToCnt(SearchVO searchVO);

	List<?> selectSysLogList(SearchVO searchVO);

}
