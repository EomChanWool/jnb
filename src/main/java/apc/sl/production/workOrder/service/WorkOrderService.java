package apc.sl.production.workOrder.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface WorkOrderService {

	int selectWorkOrderListToCnt(SearchVO searchVO);

	List<?> selectWorkOrderList(SearchVO searchVO);

	List<?> selectProdPlanList();

	String selectItemName(String prodCd);

	Map<String, Object> selectWoProdPlanInfo(Map<String, Object> map);

	int selectExistsProdPlan(Map<String, Object> map);

	List<?> selectProcessGroupList();

	int selectItemCode(Map<String, Object> map);

	void registWorkOrder(Map<String, Object> map);

	void updateProdPlan(Map<String, Object> map);

	List<?> selectProcessList(Map<String, Object> map);

	void registProcess(Map<String, Object> map);

	void updateOrder(Map<String, Object> map);

	Map<String, Object> selectWorkOrderInfo(Map<String, Object> map);

	void modifyWorkOrder(Map<String, Object> map);

	void deleteWorkOrder(Map<String, Object> map);

}
