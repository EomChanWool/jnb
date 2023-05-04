package apc.sl.material.dispensing.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface DispensingService {

	int selectDispensingListToCnt(SearchVO searchVO);

	List<?> selectDispensingList(SearchVO searchVO);

	List<?> selectWorkOrderList();

	List<?> selectMaterialList();

	void registDispensing(Map<String, Object> map);

	void updateMaterialStock(Map<String, Object> map);

	void updateWorkOrder(Map<String, Object> map);

	void updateOrders(Map<String, Object> map);

	int selectDispensingWorkOrder(Map<String, Object> map);

	Map<String, Object> selectDispensingInfo(Map<String, Object> map);

	void modifyDispensing(Map<String, Object> map);

	void deleteDispensing(Map<String, Object> map);
}
