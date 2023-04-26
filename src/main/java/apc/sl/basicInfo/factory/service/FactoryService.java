package apc.sl.basicInfo.factory.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface FactoryService {

	int selectFactoryListToCnt(SearchVO searchVO);

	List<?> selectFactoryList(SearchVO searchVO);

	List<?> selectCompanyList();

	List<?> selectComnCodeList();

	int selectExistsCompany(Map<String, Object> map);

	void registFactory(Map<String, Object> map);

	Map<String, Object> selectFactory(Map<String, Object> map);

	void modifyFactory(Map<String, Object> map);

	void deleteFactory(Map<String, Object> map);

}
