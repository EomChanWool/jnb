package apc.sl.sales.state.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.sales.state.service.StateService;
import apc.util.SearchVO;

@Service
public class StateServieImpl implements StateService {
	
	@Resource
	private StateMapper stateMapper;

	@Override
	public int selectStateListToCnt(SearchVO searchVO) {
		return stateMapper.selectStateListToCnt(searchVO);
	}

	@Override
	public List<?> selectStateList(SearchVO searchVO) {
		
		return stateMapper.selectStateList(searchVO);
	}

	@Override
	public int selectNoPayListToCnt(SearchVO searchVO) {
		
		return stateMapper.selectNoPayListToCnt(searchVO);
	}

	@Override
	public List<?> selectNoPayList(SearchVO searchVO) {
		
		return stateMapper.selectNoPayList(searchVO);
	}

	@Override
	public int selectStateMonthListToCnt(SearchVO searchVO) {
		
		return stateMapper.selectStateMonthListToCnt(searchVO);
	}

	@Override
	public List<?> selectStateMonthList(SearchVO searchVO) {
		
		return stateMapper.selectStateMonthList(searchVO);
	}

	@Override
	public List<?> selectOrYearList(SearchVO searchVO) {
		
		return stateMapper.selectOrYearList(searchVO);
	}

	@Override
	public List<?> selectDeliveryStateList(SearchVO searchVO) {
		
		return stateMapper.selectDeliveryStateList(searchVO);
	}

	@Override
	public List<?> selectDeliveryStateTotalList(SearchVO searchVO) {
		
		return stateMapper.selectDeliveryStateTotalList(searchVO);
	}

	@Override
	public List<?> selectDeYearList(SearchVO searchVO) {
		
		return stateMapper.selectDeYearList(searchVO);
	}
	
}
