package apc.sl.monitoring.prodAggregate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.monitoring.prodAggregate.service.ProdAggregateService;
import apc.util.SearchVO;

@Service
public class ProdAggregateServieImpl implements ProdAggregateService{

	@Resource
	private ProdAggregateMapper prodAggregateMapper;

	@Override
	public List<?> selectProd(SearchVO searchVO) {
		// TODO Auto-generated method stub
		return prodAggregateMapper.selectProd(searchVO);
	}

	@Override
	public List<?> selectPrYearList(SearchVO searchVO) {
		// TODO Auto-generated method stub
		return prodAggregateMapper.selectPrYearList(searchVO);
	}
}
