package apc.sl.basicInfo.storage.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("StorageMapper")
public interface StorageMapper {

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
