package apc.sl.monitoring.equipEff.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.monitoring.equipEff.service.EquipEffService;

@Service
public class EquipEffServieImple implements EquipEffService {

	@Resource
	private EquipEffMapper equipEffMapper;

	@Override
	public List<Map<String, Object>> selectEquipEff() {
		return equipEffMapper.selectEquipEff();
	}
	
}
