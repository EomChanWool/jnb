package apc.sl.basicInfo.facility.service;

import java.util.List;

import apc.util.SearchVO;

public interface FacilityService {

	int selectFacilityListToCnt(SearchVO searchVO);
	
	List<?> selectFacilityList(SearchVO searchVO);
	
	List<?> selectFactoryList();
	
	
}
