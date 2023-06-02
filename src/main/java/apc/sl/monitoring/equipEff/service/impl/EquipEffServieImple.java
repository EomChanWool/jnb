package apc.sl.monitoring.equipEff.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.monitoring.equipEff.service.EquipEffService;
import apc.util.SearchVO;

@Service
public class EquipEffServieImple implements EquipEffService {

	@Resource
	private EquipEffMapper equipEffMapper;

	@Override
	public List<Map<String, Object>> selectEquipEff() {
		return equipEffMapper.selectEquipEff();
	}

	@Override
	public List<Map<String, Object>> selectEquioEffCnt1(SearchVO searchVO) {
		return (List<Map<String, Object>>) equipEffMapper.selectEquioEffCnt1(searchVO);
	}

	@Override
	public List<?> selectEquioEffCnt2(SearchVO searchVO) {
		return equipEffMapper.selectEquioEffCnt2(searchVO);
	}

	@Override
	public List<?> selectPlcYearList(SearchVO searchVO) {
		return equipEffMapper.selectPlcYearList(searchVO);
	}
	
}
