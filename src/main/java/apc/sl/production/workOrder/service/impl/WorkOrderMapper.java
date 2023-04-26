package apc.sl.production.workOrder.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("WorkOrderMapper")
public interface WorkOrderMapper {

	int selectWorkOrderListToCnt(SearchVO searchVO);

	List<?> selectWorkOrderList(SearchVO searchVO);

	List<?> selectProdPlanList();

	List<?> selectProcessGroupList();

	String selectItemName(String prodCd);

	Map<String, Object> selectWoProdPlanInfo(Map<String, Object> map);

	int selectExistsProdPlan(Map<String, Object> map);

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
