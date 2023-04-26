package apc.sl.basicInfo.dept.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("DeptMapper")
public interface DeptMapper {

	int selectDeptListToCnt(SearchVO searchVO);

	List<?> selectDeptList(SearchVO searchVO);

	List<?> selectCompanyList();

	List<?> selectComnCodeList();

	int selectExistsCompany(Map<String, Object> map);

	void registDept(Map<String, Object> map);

	Map<String, Object> selectDeptInfo(Map<String, Object> map);

	void modifyDept(Map<String, Object> map);

	void deleteDept(Map<String, Object> map);

}
