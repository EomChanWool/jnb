package apc.sl.monitoring.lineActivity.service.impl;

import java.util.List;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("LineActivityMapper")
public interface LineActivityMapper {

	int selectLineActivityToCnt(SearchVO searchVO);
	
	List<?> selectLineActivity(SearchVO searchVO);
	
}
