package apc.sl.basicInfo.factory.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("FactoryMapper")
public interface FactoryMapper {

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
