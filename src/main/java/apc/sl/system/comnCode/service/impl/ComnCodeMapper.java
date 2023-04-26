package apc.sl.system.comnCode.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ComnCodeMapper")
public interface ComnCodeMapper {

	int selectComnCodeListToCnt(SearchVO searchVO);

	List<?> selectComnCodeList(SearchVO searchVO);

	void registComnCode(Map<String, Object> map);

	Map<String, Object> selectComnCodeInfo(Map<String, Object> map);

	void modifyComnCode(Map<String, Object> map);

	void deleteComnCode(Map<String, Object> map);

}
