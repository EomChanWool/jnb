package apc.sl.basicInfo.dept.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface DeptService {

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
