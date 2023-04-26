package apc.sl.basicInfo.dept.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.dept.service.DeptService;
import apc.util.SearchVO;
@Service
public class DeptServieImpl implements DeptService {
	@Resource
	private DeptMapper deptMapper;

	@Override
	public int selectDeptListToCnt(SearchVO searchVO) {
		return deptMapper.selectDeptListToCnt(searchVO);
	}

	@Override
	public List<?> selectDeptList(SearchVO searchVO) {
		return deptMapper.selectDeptList(searchVO);
	}

	@Override
	public List<?> selectCompanyList() {
		return deptMapper.selectCompanyList();
	}

	@Override
	public List<?> selectComnCodeList() {
		return deptMapper.selectComnCodeList();
	}
	
	@Override
	public int selectExistsCompany(Map<String, Object> map) {
		return deptMapper.selectExistsCompany(map);
	}

	@Override
	public void registDept(Map<String, Object> map) {
		deptMapper.registDept(map);
	}

	@Override
	public Map<String, Object> selectDeptInfo(Map<String, Object> map) {
		return deptMapper.selectDeptInfo(map);
	}

	@Override
	public void modifyDept(Map<String, Object> map) {
		deptMapper.modifyDept(map);
	}

	@Override
	public void deleteDept(Map<String, Object> map) {
		deptMapper.deleteDept(map);
	}

}
