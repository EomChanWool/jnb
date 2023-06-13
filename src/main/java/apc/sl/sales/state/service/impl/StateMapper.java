package apc.sl.sales.state.service.impl;

import java.util.List;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("StateMapper")
public interface StateMapper {
	
	int selectStateListToCnt(SearchVO searchVO);
	
	List<?> selectStateList(SearchVO searchVO);
	
	int selectNoPayListToCnt(SearchVO searchVO);
	
	List<?> selectNoPayList(SearchVO searchVO);
	
	int selectStateMonthListToCnt(SearchVO searchVO);
	
	List<?> selectStateMonthList(SearchVO searchVO);
	
	List<?> selectOrYearList(SearchVO searchVO);
	
	List<?> selectDeliveryStateList(SearchVO searchVO);

	List<?> selectDeliveryStateTotalList(SearchVO searchVO);

	List<?> selectDeYearList(SearchVO searchVO);
}
