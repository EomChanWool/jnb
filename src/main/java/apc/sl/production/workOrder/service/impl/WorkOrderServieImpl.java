package apc.sl.production.workOrder.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.production.workOrder.service.WorkOrderService;
import apc.util.SearchVO;
@Service
public class WorkOrderServieImpl implements WorkOrderService {
	@Resource
	private WorkOrderMapper workOrderMapper;

	@Override
	public int selectWorkOrderListToCnt(SearchVO searchVO) {
		return workOrderMapper.selectWorkOrderListToCnt(searchVO);
	}

	@Override
	public List<?> selectWorkOrderList(SearchVO searchVO) {
		return workOrderMapper.selectWorkOrderList(searchVO);
	}

	@Override
	public List<?> selectProdPlanList() {
		return workOrderMapper.selectProdPlanList();
	}

	@Override
	public String selectItemName(String prodCd) {
		return workOrderMapper.selectItemName(prodCd);
	}

	@Override
	public Map<String, Object> selectWoProdPlanInfo(Map<String, Object> map) {
		return workOrderMapper.selectWoProdPlanInfo(map);
	}

	@Override
	public int selectExistsProdPlan(Map<String, Object> map) {
		return workOrderMapper.selectExistsProdPlan(map);
	}

	@Override
	public List<?> selectProcessGroupList() {
		return workOrderMapper.selectProcessGroupList();
	}

	@Override
	public int selectItemCode(Map<String, Object> map) {
		return workOrderMapper.selectItemCode(map);
	}

	@Override
	public void registWorkOrder(Map<String, Object> map) {
		workOrderMapper.registWorkOrder(map);
	}

	@Override
	public void updateProdPlan(Map<String, Object> map) {
		workOrderMapper.updateProdPlan(map);
	}

	@Override
	public List<?> selectProcessList(Map<String, Object> map) {
		return workOrderMapper.selectProcessList(map);
	}

	@Override
	public void registProcess(Map<String, Object> map) {
		workOrderMapper.registProcess(map);
	}

	@Override
	public void updateOrder(Map<String, Object> map) {
		workOrderMapper.updateOrder(map);
	}

	@Override
	public Map<String, Object> selectWorkOrderInfo(Map<String, Object> map) {
		return workOrderMapper.selectWorkOrderInfo(map);
	}

	@Override
	public void modifyWorkOrder(Map<String, Object> map) {
		workOrderMapper.modifyWorkOrder(map);
	}

	@Override
	public void deleteWorkOrder(Map<String, Object> map) {
		workOrderMapper.deleteWorkOrder(map);
	}

}
