package apc.sl.system.sysLog.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface SysLogService {

	int selectSysLogListToCnt(SearchVO searchVO);

	List<?> selectSysLogList(SearchVO searchVO);

}
