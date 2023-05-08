package apc.sl.basicInfo.facility.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface FacilityService {

	int selectFacilityListToCnt(SearchVO searchVO);
	
	List<?> selectFacilityList(SearchVO searchVO);
	
	List<?> selectFactoryList();
	
	Map<String, Object> selectFacility(Map<String, Object> map);
	
	
	int selectExistsFacility(Map<String, Object> map);
		
	void modifyFacility(Map<String, Object> map);
	
	void modifyNonOp(Map<String, Object> map);
	
	void registFacility(Map<String, Object> map);
	
	void deleteFacility(Map<String, Object> map);
	
	void registNonOpSt(Map<String, Object> map);
	
	
	
}
