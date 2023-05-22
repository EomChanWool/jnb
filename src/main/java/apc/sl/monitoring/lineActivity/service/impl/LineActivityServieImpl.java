package apc.sl.monitoring.lineActivity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.monitoring.lineActivity.service.LineActivityService;
import apc.util.SearchVO;


@Service
public class LineActivityServieImpl implements LineActivityService {

	@Resource
	private LineActivityMapper lineActivityMapper;

	@Override
	public int selectLineActivityToCnt(SearchVO searchVO) {
		return lineActivityMapper.selectLineActivityToCnt(searchVO);
	}

	@Override
	public List<?> selectLineActivity(SearchVO searchVO) {
		return lineActivityMapper.selectLineActivity(searchVO);
	}
	
}
