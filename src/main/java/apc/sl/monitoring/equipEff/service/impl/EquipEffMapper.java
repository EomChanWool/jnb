package apc.sl.monitoring.equipEff.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("EquipEffMapper")
public interface EquipEffMapper {
	
	List<Map<String, Object>> selectEquipEff();
	
	List<?> selectPlcYearList(SearchVO searchVO);
	
	List<?> selectEquioEffCnt1(SearchVO searchVO);
	
	List<?> selectEquioEffCnt2(SearchVO searchVO);

}
