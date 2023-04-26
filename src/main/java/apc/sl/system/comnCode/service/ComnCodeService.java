package apc.sl.system.comnCode.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface ComnCodeService {

	int selectComnCodeListToCnt(SearchVO searchVO);

	List<?> selectComnCodeList(SearchVO searchVO);

	void registComnCode(Map<String, Object> map);

	Map<String, Object> selectComnCodeInfo(Map<String, Object> map);

	void modifyComnCode(Map<String, Object> map);

	void deleteComnCode(Map<String, Object> map);

}
