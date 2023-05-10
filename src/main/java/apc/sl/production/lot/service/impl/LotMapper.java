package apc.sl.production.lot.service.impl;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("LotMapper")
public interface LotMapper {
	
	int selectLotListToCnt(SearchVO searchVO);

}
