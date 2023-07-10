package apc.sl.material.stockState.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.material.stockState.service.StockStateService;
import apc.util.SearchVO;
@Service
public class StockStateServieImpl implements StockStateService {
	@Resource
	private StockStateMapper stockStateMapper;

	@Override
	public int selectStockStateListToCnt(SearchVO searchVO) {
		return stockStateMapper.selectStockStateListToCnt(searchVO);
	}

	@Override
	public List<?> selectStockStateList(SearchVO searchVO) {
		return stockStateMapper.selectStockStateList(searchVO);
	}

	@Override
	public List<?> selectMoStockStateList(SearchVO searchVO) {
		// TODO Auto-generated method stub
		return stockStateMapper.selectMoStockStateList(searchVO);
	}

	@Override
	public Map<String, Object> selectMoStoDetail(Map<String, Object> map) {
		return stockStateMapper.selectMoStoDetail(map);
	}

	@Override
	public void updateStockPro(Map<String, Object> map) {
		stockStateMapper.updateStockPro(map);
	}

	
}
