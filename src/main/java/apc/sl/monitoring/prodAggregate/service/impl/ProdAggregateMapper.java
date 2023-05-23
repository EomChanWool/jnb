package apc.sl.monitoring.prodAggregate.service.impl;

import java.util.List;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ProdAggregateMapper")
public interface ProdAggregateMapper {
	
	List<?> selectProd(SearchVO searchVO);
	
	List<?> selectPrYearList(SearchVO searchVO);
	
	List<?> selectOrders(SearchVO searchVO);

}
