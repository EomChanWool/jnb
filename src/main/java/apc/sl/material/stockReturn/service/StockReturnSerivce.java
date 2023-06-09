package apc.sl.material.stockReturn.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface StockReturnSerivce {
	
	int selectStockReturnListToCnt(SearchVO searchVO);
	
	List<?>selectStockReturnList(SearchVO searchVO);
	
	List<?>selectWoList();
	
	List<?> selectMaterialList();
	
	List<?> selectItemInfo(Map<String, Object> map);
	
	Map<String, Object> selectInfo(Map<String, Object> map);
	
	Map<String, Object> selectDetailInfo(Map<String, Object> map);
		
	int selectWoIdx(Map<String, Object> map);
	
	void modifyStockReturn(Map<String, Object> map);
	
	void updateMaterialStock(Map<String, Object> map);
	
	void updateWorkOrderCnt(Map<String, Object> map);
	
	void updateProdResult(Map<String, Object> map);
	
	void updateProdResult2(Map<String, Object> map);
	
	void updateProcessSet(Map<String, Object> map);
	
	void updateProcessSet2(Map<String, Object> map);
	
	void registStockReturn(Map<String, Object> map);
	
	void deleteStockReturn(Map<String, Object> map);
	
	
}
