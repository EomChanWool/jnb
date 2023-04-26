package apc.sl.sales.orders.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.sales.orders.service.OrdersService;
import apc.util.SearchVO;
@Service
public class OrdersServieImpl implements OrdersService {
	@Resource
	private OrdersMapper ordersMapper;

	@Override
	public int selectOrdersListToCnt(SearchVO searchVO) {
		return ordersMapper.selectOrdersListToCnt(searchVO);
	}

	@Override
	public List<?> selectOrdersList(SearchVO searchVO) {
		return ordersMapper.selectOrdersList(searchVO);
	}

	@Override
	public List<?>  selectEstimateList(Map<String, Object> map) {
		return ordersMapper.selectEstimateList(map);
	}

	@Override
	public List<?> selectEstimateInfo(Map<String, Object> map) {
		return ordersMapper.selectEstimateInfo(map);
	}

	@Override
	public void registOrders(Map<String, Object> map) {
		ordersMapper.registOrders(map);
	}

	@Override
	public void updateEstimate(Map<String, Object> map) {
		ordersMapper.updateEstimate(map);
	}

	@Override
	public Map<String, Object> selectOrdersInfo(Map<String, Object> map) {
		return ordersMapper.selectOrdersInfo(map);
	}

	@Override
	public void modifyOrders(Map<String, Object> map) {
		ordersMapper.modifyOrders(map);
	}

	@Override
	public void deleteOrders(Map<String, Object> map) {
		ordersMapper.deleteOrders(map);
	}

}
