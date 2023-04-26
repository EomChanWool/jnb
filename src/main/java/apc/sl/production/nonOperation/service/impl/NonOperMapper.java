package apc.sl.production.nonOperation.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("NonOperMapper")
public interface NonOperMapper {

	int selectNonOperListToCnt(SearchVO searchVO);

	List<?> selectNonOperList(SearchVO searchVO);

	List<?> selectCompanyList();

	List<?> selectFactoryList(Map<String, Object> map);

	List<?> selectFacilityList(Map<String, Object> map);

	int selectCompanyIdx(Map<String, Object> map);

	int selectFactoryCode(Map<String, Object> map);

	int selectFacilityIdx(Map<String, Object> map);

	void registNonOper(Map<String, Object> map);

	Map<String, Object> selectNonOperInfo(Map<String, Object> map);

	void modifyNonOper(Map<String, Object> map);

	void deleteNonOper(Map<String, Object> map);

}
