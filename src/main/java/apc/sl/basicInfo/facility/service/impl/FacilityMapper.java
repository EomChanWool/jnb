package apc.sl.basicInfo.facility.service.impl;

import java.util.List;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("FacilityMapper")
public interface FacilityMapper {

	int selectFacilityListToCnt(SearchVO searchVO);
	
	List<?> selectFacilityList(SearchVO searchVO);
	
	
}
