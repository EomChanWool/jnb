package apc.sl.sales.orders.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface OrdersService {

	int selectOrdersListToCnt(SearchVO searchVO);

	List<?> selectOrdersList(SearchVO searchVO);

	List<?> selectEstimateList(Map<String, Object> map);

	List<?> selectEstimateInfo(Map<String, Object> map);

	void registOrders(Map<String, Object> map);

	void updateEstimate(Map<String, Object> map);

	Map<String, Object> selectOrdersInfo(Map<String, Object> map);

	void modifyOrders(Map<String, Object> map);

	void deleteOrders(Map<String, Object> map);

}
