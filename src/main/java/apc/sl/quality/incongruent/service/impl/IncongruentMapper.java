package apc.sl.quality.incongruent.service.impl;

import java.util.List;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("IncongruentMapper")
public interface IncongruentMapper {
	
	
	int selectIncongruentListToCnt(SearchVO searchVO);
	
	List<?>selectIncongruentList(SearchVO searchVO);
	
}
