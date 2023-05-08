package apc.sl.quality.incongruent.service;

import java.util.List;

import apc.util.SearchVO;

public interface IncongruentService {
	
	int selectIncongruentListToCnt(SearchVO searchVO);
	
	List<?> selectIncongruentList(SearchVO searchVO);

}
