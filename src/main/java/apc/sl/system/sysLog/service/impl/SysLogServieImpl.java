package apc.sl.system.sysLog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.system.sysLog.service.SysLogService;
import apc.util.SearchVO;
@Service
public class SysLogServieImpl implements SysLogService {
	@Resource
	private SysLogMapper sysLogMapper;

	@Override
	public int selectSysLogListToCnt(SearchVO searchVO) {
		return sysLogMapper.selectSysLogListToCnt(searchVO);
	}

	@Override
	public List<?> selectSysLogList(SearchVO searchVO) {
		return sysLogMapper.selectSysLogList(searchVO);
	}

}
