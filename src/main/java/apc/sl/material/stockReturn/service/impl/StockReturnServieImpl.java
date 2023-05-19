package apc.sl.material.stockReturn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.material.stockReturn.service.StockReturnSerivce;
import apc.util.SearchVO;

@Service
public class StockReturnServieImpl implements StockReturnSerivce {
	
	@Resource
	private StockReturnMapper stockReturnMapper; 

	@Override
	public int selectStockReturnListToCnt(SearchVO searchVO) {
		
		return stockReturnMapper.selectStockReturnListToCnt(searchVO);
	}

	@Override
	public List<?> selectStockReturnList(SearchVO searchVO) {
		return stockReturnMapper.selectStockReturnList(searchVO);
	}

	@Override
	public List<?> selectWoList() {
		return stockReturnMapper.selectWoList();
	}

	@Override
	public Map<String, Object> selectInfo(Map<String, Object> map) {
		return stockReturnMapper.selectInfo(map);
	}

	@Override
	public int selectWoIdx(Map<String, Object> map) {
		return stockReturnMapper.selectWoIdx(map);
	}

	@Override
	public void registStockReturn(Map<String, Object> map) {
		stockReturnMapper.registStockReturn(map);
		
	}

	@Override
	public void deleteStockReturn(Map<String, Object> map) {
		stockReturnMapper.deleteStockReturn(map);
		
	}

	@Override
	public List<?> selectMaterialList() {
		return stockReturnMapper.selectMaterialList();
	}

	@Override
	public List<?> selectItemInfo(Map<String, Object> map) {
		return stockReturnMapper.selectItemInfo(map);
	}

	@Override
	public void updateMaterialStock(Map<String, Object> map) {
		stockReturnMapper.updateMaterialStock(map);
	}

	@Override
	public void updateWorkOrderCnt(Map<String, Object> map) {
		stockReturnMapper.updateWorkOrderCnt(map);
	}

	@Override
	public void updateProdResult(Map<String, Object> map) {
		stockReturnMapper.updateProdResult(map);
	}

	@Override
	public void updateProcessSet(Map<String, Object> map) {
		stockReturnMapper.updateProcessSet(map);
	}

	@Override
	public Map<String, Object> selectDetailInfo(Map<String, Object> map) {
		return stockReturnMapper.selectDetailInfo(map);
	}

}
