package apc.sl.basicInfo.factory.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.factory.service.FactoryService;
import apc.util.SearchVO;
@Service
public class FactoryServieImpl implements FactoryService {
	@Resource
	private FactoryMapper factoryMapper;

	@Override
	public int selectFactoryListToCnt(SearchVO searchVO) {
		return factoryMapper.selectFactoryListToCnt(searchVO);
	}

	@Override
	public List<?> selectFactoryList(SearchVO searchVO) {
		return factoryMapper.selectFactoryList(searchVO);
	}

	@Override
	public List<?> selectCompanyList() {
		return factoryMapper.selectCompanyList();
	}

	@Override
	public List<?> selectComnCodeList() {
		return factoryMapper.selectComnCodeList();
	}

	@Override
	public int selectExistsCompany(Map<String, Object> map) {
		return factoryMapper.selectExistsCompany(map);
	}

	@Override
	public void registFactory(Map<String, Object> map) {
		factoryMapper.registFactory(map);
	}

	@Override
	public Map<String, Object> selectFactory(Map<String, Object> map) {
		return factoryMapper.selectFactory(map);
	}

	@Override
	public void modifyFactory(Map<String, Object> map) {
		factoryMapper.modifyFactory(map);
	}

	@Override
	public void deleteFactory(Map<String, Object> map) {
		factoryMapper.deleteFactory(map);
	}

}
