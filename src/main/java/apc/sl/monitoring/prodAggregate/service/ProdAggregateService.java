package apc.sl.monitoring.prodAggregate.service;

import java.util.List;

import apc.util.SearchVO;

public interface ProdAggregateService {
	
	List<?> selectProd(SearchVO searchVO);
	
	List<?> selectPrYearList(SearchVO searchVO);
	
	List<?> selectOrders(SearchVO searchVO);
}
