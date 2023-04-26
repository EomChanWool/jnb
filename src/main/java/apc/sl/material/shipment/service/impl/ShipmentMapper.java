package apc.sl.material.shipment.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ShipmentMapper")
public interface ShipmentMapper {

	int selectShipmentListToCnt(SearchVO searchVO);

	List<?> selectShipmentList(SearchVO searchVO);

	List<?> selectOrdersList();

	Map<String, Object> selectOrdersInfo(Map<String, Object> map);

	int selectOrdersIdx(Map<String, Object> map);

	void registShipment(Map<String, Object> map);

	void updateOrders(Map<String, Object> map);

	Map<String, Object> selectShipmentInfo(Map<String, Object> map);

	void modifyShipment(Map<String, Object> map);

	void deleteShipment(Map<String, Object> map);

}
