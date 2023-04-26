package apc.sl.kpi.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("KpiMapper")
public interface KpiMapper {

	int selectKpiListToCnt(SearchVO searchVO);

	List<?> selectKpiList(SearchVO searchVO);

	List<?> selectKpiGraphList(SearchVO searchVO);
	
	List<?> selectOutput(SearchVO searchVO);

	List<?> selectSales(SearchVO searchVO);

	int selectExistsKpi(Map<String, Object> map);

	void registKpi(Map<String, Object> map);

	Map<String, Object> selectKpiInfo(Map<String, Object> map);

	void modifyKpi(Map<String, Object> map);

	void deleteKpi(Map<String, Object> map);

}
