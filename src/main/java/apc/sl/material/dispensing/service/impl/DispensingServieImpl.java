package apc.sl.material.dispensing.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.material.dispensing.service.DispensingService;
import apc.util.SearchVO;
@Service
public class DispensingServieImpl implements DispensingService {
	@Resource
	private DispensingMapper dispensingMapper;

	@Override
	public int selectDispensingListToCnt(SearchVO searchVO) {
		return dispensingMapper.selectDispensingListToCnt(searchVO);
	}

	@Override
	public List<?> selectDispensingList(SearchVO searchVO) {
		return dispensingMapper.selectDispensingList(searchVO);
	}

	@Override
	public List<?> selectWorkOrderList() {
		return dispensingMapper.selectWorkOrderList();
	}

	@Override
	public List<?> selectMaterialList() {
		return dispensingMapper.selectMaterialList();
	}

	@Override
	public void registDispensing(Map<String, Object> map) {
		dispensingMapper.registDispensing(map);
	}

	@Override
	public void updateMaterialStock(Map<String, Object> map) {
		dispensingMapper.updateMaterialStock(map);
	}

	@Override
	public void updateWorkOrder(Map<String, Object> map) {
		dispensingMapper.updateWorkOrder(map);
	}

	@Override
	public void updateOrders(Map<String, Object> map) {
		dispensingMapper.updateOrders(map);
	}
	
	@Override
	public int selectDispensingWorkOrder(Map<String, Object> map) {
		return dispensingMapper.selectDispensingWorkOrder(map);
	}

	@Override
	public Map<String, Object> selectDispensingInfo(Map<String, Object> map) {
		return dispensingMapper.selectDispensingInfo(map);
	}

	@Override
	public void modifyDispensing(Map<String, Object> map) {
		dispensingMapper.modifyDispensing(map);
	}

	@Override
	public void deleteDispensing(Map<String, Object> map) {
		dispensingMapper.deleteDispensing(map);
	}
}
