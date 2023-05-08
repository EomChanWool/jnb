package apc.sl.quality.incongruent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import apc.sl.quality.incongruent.service.IncongruentService;
import apc.util.SearchVO;

public class IncongruentServieImpl implements IncongruentService {
	
	@Resource
	private IncongruentMapper incongruentMapper;
	

	@Override
	public int selectIncongruentListToCnt(SearchVO searchVO) {
		return incongruentMapper.selectIncongruentListToCnt(searchVO);
	}

	@Override
	public List<?> selectIncongruentList(SearchVO searchVO) {
	
		return incongruentMapper.selectIncongruentList(searchVO);
	}

	
}
