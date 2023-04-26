package apc.sl.sales.delivery.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface DeliveryService {

	int selectDeliveryListToCnt(SearchVO searchVO);

	List<?> selectDeliveryList(SearchVO searchVO);

	List<?> selectOrdersList();

	Map<String, Object> selectOrdersInfo(Map<String, Object> map);

	int selectOrdersIdx(Map<String, Object> map);

	void registDelivery(Map<String, Object> map);

	void updateOrders(Map<String, Object> map);

	Map<String, Object> selectDeliveryInfo(Map<String, Object> map);

	void modifyDelivery(Map<String, Object> map);

	void deleteDelivery(Map<String, Object> map);

}
