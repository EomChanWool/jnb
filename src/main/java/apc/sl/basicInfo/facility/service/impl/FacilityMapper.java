package apc.sl.basicInfo.facility.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("FacilityMapper")
public interface FacilityMapper {

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
