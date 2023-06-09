package apc.sl.material.stockState.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface StockStateService {

	int selectStockStateListToCnt(SearchVO searchVO);

	List<?> selectStockStateList(SearchVO searchVO);
	
	List<?> selectMoStockStateList(SearchVO searchVO);
	
	Map<String, Object> selectMoStoDetail(Map<String, Object> map);
	
	void updateStockPro(Map<String, Object> map);

}
