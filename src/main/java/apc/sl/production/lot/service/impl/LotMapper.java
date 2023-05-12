package apc.sl.production.lot.service.impl;

import java.util.List;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("LotMapper")
public interface LotMapper {
	
	int selectLotListToCnt(SearchVO searchVO);
	
	List<?> selectLotList(SearchVO searchVO);

}
