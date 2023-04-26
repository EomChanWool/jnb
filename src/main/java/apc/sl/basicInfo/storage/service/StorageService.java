package apc.sl.basicInfo.storage.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface StorageService {

	int selectStorageListToCnt(SearchVO searchVO);

	List<?> selectStorageList(SearchVO searchVO);

	List<?> selectCompanyList();

	List<?> selectComnCodeList();

	int selectExistsCompany(Map<String, Object> map);

	void registStorage(Map<String, Object> map);

	Map<String, Object> selectStorageInfo(Map<String, Object> map);

	void modifyStorage(Map<String, Object> map);

	void deleteStorage(Map<String, Object> map);

}
