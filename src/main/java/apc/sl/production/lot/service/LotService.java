package apc.sl.production.lot.service;

import java.util.List;

import apc.util.SearchVO;

public interface LotService {
	
	int selectLotListToCnt(SearchVO searchVO);
	
	List<?> selectLotList(SearchVO searchVO);
	
}
