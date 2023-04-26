package apc.sl.material.outsourcing.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.material.outsourcing.service.OutsourcingService;
import apc.util.SearchVO;
@Service
public class OutsourcingServieImpl implements OutsourcingService {
	@Resource
	private OutsourcingMapper outsourcingMapper;

	@Override
	public int selectOutsourcingListToCnt(SearchVO searchVO) {
		return outsourcingMapper.selectOutsourcingListToCnt(searchVO);
	}

	@Override
	public List<?> selectOutsourcingList(SearchVO searchVO) {
		return outsourcingMapper.selectOutsourcingList(searchVO);
	}

	@Override
	public List<?> selectWorkOrderList() {
		return outsourcingMapper.selectWorkOrderList();
	}

	@Override
	public List<?> selectWorkOrderInfo(Map<String, Object> map) {
		return outsourcingMapper.selectWorkOrderInfo(map);
	}

	@Override
	public int selectExistsWoIdx(Map<String, Object> map) {
		return outsourcingMapper.selectExistsWoIdx(map);
	}

	@Override
	public void updateWorkOrder(Map<String, Object> map) {
		outsourcingMapper.updateWorkOrder(map);
	}

	@Override
	public void registOutSourcing(Map<String, Object> map) {
		outsourcingMapper.registOutSourcing(map);
	}

	@Override
	public Map<String, Object> selectOutsourcingInfo(Map<String, Object> map) {
		return outsourcingMapper.selectOutsourcingInfo(map);
	}

	@Override
	public void modifyOutsourcing(Map<String, Object> map) {
		outsourcingMapper.modifyOutsourcing(map);
	}

	@Override
	public void deleteOutsourcing(Map<String, Object> map) {
		outsourcingMapper.deleteOutsourcing(map);
	}

}
