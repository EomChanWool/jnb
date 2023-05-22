package apc.sl.monitoring.lineActivity.service;

import java.util.List;

import apc.util.SearchVO;

public interface LineActivityService {
	
	int selectLineActivityToCnt(SearchVO searchVO);
	
	List<?> selectLineActivity(SearchVO searchVO);

}
