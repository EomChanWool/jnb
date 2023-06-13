package apc.sl.sales.state.service;

import java.util.List;

import apc.util.SearchVO;

public interface StateService {
	
	int selectStateListToCnt(SearchVO searchVO);
	
	List<?> selectStateList(SearchVO searchVO);
	
	int selectNoPayListToCnt(SearchVO searchVO);
	
	List<?> selectNoPayList(SearchVO searchVO);
	
	int selectStateMonthListToCnt(SearchVO searchVO);
	
	List<?> selectStateMonthList(SearchVO searchVO);
	
	List<?> selectOrYearList(SearchVO searchVO);
	
	List<?> selectDeliveryStateList(SearchVO searchVO);
	
	List<?> selectDeYearList(SearchVO searchVO);
	
}
