package apc.sl.sales.delivery.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.sales.delivery.service.DeliveryService;
import apc.util.SearchVO;
@Service
public class DeliveryServieImpl implements DeliveryService {
	@Resource
	private DeliveryMapper deliveryMapper;

	@Override
	public int selectDeliveryListToCnt(SearchVO searchVO) {
		return deliveryMapper.selectDeliveryListToCnt(searchVO);
	}

	@Override
	public List<?> selectDeliveryList(SearchVO searchVO) {
		return deliveryMapper.selectDeliveryList(searchVO);
	}

	@Override
	public List<?> selectOrdersList() {
		return deliveryMapper.selectOrdersList();
	}

	@Override
	public Map<String, Object> selectOrdersInfo(Map<String, Object> map) {
		return deliveryMapper.selectOrdersInfo(map);
	}

	@Override
	public int selectOrdersIdx(Map<String, Object> map) {
		return deliveryMapper.selectOrdersIdx(map);
	}

	@Override
	public void registDelivery(Map<String, Object> map) {
		deliveryMapper.registDelivery(map);
	}

	@Override
	public void updateOrders(Map<String, Object> map) {
		deliveryMapper.updateOrders(map);
	}

	@Override
	public Map<String, Object> selectDeliveryInfo(Map<String, Object> map) {
		return deliveryMapper.selectDeliveryInfo(map);
	}

	@Override
	public void modifyDelivery(Map<String, Object> map) {
		deliveryMapper.modifyDelivery(map);
	}

	@Override
	public void deleteDelivery(Map<String, Object> map) {
		deliveryMapper.deleteDelivery(map);
	}

	@Override
	public void updateProduct(Map<String, Object> map) {
		deliveryMapper.updateProduct(map);
		
	}

	@Override
	public void updateProduct2(Map<String, Object> map) {
		deliveryMapper.updateProduct2(map);
	}

}
