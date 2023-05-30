package apc.sl.monitoring.equipEff.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface EquipEffService {

	List<Map<String, Object>> selectEquipEff();
	
	List<?> selectEquioEffCnt1(SearchVO searchVO);
	
	List<?> selectEquioEffCnt2(SearchVO searchVO);
	
}
