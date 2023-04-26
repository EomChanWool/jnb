package apc.sl.material.shipment.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.material.shipment.service.ShipmentService;
import apc.util.SearchVO;
@Service
public class ShipmentServieImpl implements ShipmentService {
	@Resource
	private ShipmentMapper shipmentMapper;

	@Override
	public int selectShipmentListToCnt(SearchVO searchVO) {
		return shipmentMapper.selectShipmentListToCnt(searchVO);
	}

	@Override
	public List<?> selectShipmentList(SearchVO searchVO) {
		return shipmentMapper.selectShipmentList(searchVO);
	}

	@Override
	public List<?> selectOrdersList() {
		return shipmentMapper.selectOrdersList();
	}

	@Override
	public Map<String, Object> selectOrdersInfo(Map<String, Object> map) {
		return shipmentMapper.selectOrdersInfo(map);
	}

	@Override
	public int selectOrdersIdx(Map<String, Object> map) {
		return shipmentMapper.selectOrdersIdx(map);
	}

	@Override
	public void registShipment(Map<String, Object> map) {
		shipmentMapper.registShipment(map);
	}

	@Override
	public void updateOrders(Map<String, Object> map) {
		shipmentMapper.updateOrders(map);
	}

	@Override
	public Map<String, Object> selectShipmentInfo(Map<String, Object> map) {
		return shipmentMapper.selectShipmentInfo(map);
	}

	@Override
	public void modifyShipment(Map<String, Object> map) {
		shipmentMapper.modifyShipment(map);
	}

	@Override
	public void deleteShipment(Map<String, Object> map) {
		shipmentMapper.deleteShipment(map);
	}

}
