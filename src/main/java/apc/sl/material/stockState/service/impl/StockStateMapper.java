package apc.sl.material.stockState.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("StockStateMapper")
public interface StockStateMapper {

	int selectStockStateListToCnt(SearchVO searchVO);

	List<?> selectStockStateList(SearchVO searchVO);
	
	List<?> selectMoStockStateList(SearchVO searchVO);
	
	Map<String, Object> selectMoStoDetail(Map<String, Object> map);
	
	void updateStockPro(Map<String, Object> map);

}
