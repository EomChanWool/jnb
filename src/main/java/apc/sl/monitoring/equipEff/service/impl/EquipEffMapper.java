package apc.sl.monitoring.equipEff.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("EquipEffMapper")
public interface EquipEffMapper {
	
	List<Map<String, Object>> selectEquipEff();

}
