package apc.sl.basicInfo.storage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.storage.service.StorageService;
import apc.util.SearchVO;
@Service
public class StorageServieImpl implements StorageService {
	@Resource
	private StorageMapper storageMapper;

	@Override
	public int selectStorageListToCnt(SearchVO searchVO) {
		return storageMapper.selectStorageListToCnt(searchVO);
	}

	@Override
	public List<?> selectStorageList(SearchVO searchVO) {
		return storageMapper.selectStorageList(searchVO);
	}

	@Override
	public List<?> selectCompanyList() {
		return storageMapper.selectCompanyList();
	}

	@Override
	public List<?> selectComnCodeList() {
		return storageMapper.selectComnCodeList();
	}

	@Override
	public int selectExistsCompany(Map<String, Object> map) {
		return storageMapper.selectExistsCompany(map);
	}

	@Override
	public void registStorage(Map<String, Object> map) {
		storageMapper.registStorage(map);
	}

	@Override
	public Map<String, Object> selectStorageInfo(Map<String, Object> map) {
		return storageMapper.selectStorageInfo(map);
	}

	@Override
	public void modifyStorage(Map<String, Object> map) {
		storageMapper.modifyStorage(map);
	}

	@Override
	public void deleteStorage(Map<String, Object> map) {
		storageMapper.deleteStorage(map);
	}

}
