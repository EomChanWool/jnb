package apc.sl.production.nonOperation.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.production.nonOperation.service.NonOperService;
import apc.util.SearchVO;
@Service
public class NonOperServieImpl implements NonOperService {
	@Resource
	private NonOperMapper nonOperMapper;

	@Override
	public int selectNonOperListToCnt(SearchVO searchVO) {
		return nonOperMapper.selectNonOperListToCnt(searchVO);
	}

	@Override
	public List<?> selectNonOperList(SearchVO searchVO) {
		return nonOperMapper.selectNonOperList(searchVO);
	}

	@Override
	public List<?> selectCompanyList() {
		return nonOperMapper.selectCompanyList();
	}

	@Override
	public List<?> selectFactoryList(Map<String, Object> map) {
		return nonOperMapper.selectFactoryList(map);
	}

	@Override
	public List<?> selectFacilityList(Map<String, Object> map) {
		return nonOperMapper.selectFacilityList(map);
	}

	@Override
	public int selectCompanyIdx(Map<String, Object> map) {
		return nonOperMapper.selectCompanyIdx(map);
	}

	@Override
	public int selectFactoryCode(Map<String, Object> map) {
		return nonOperMapper.selectFactoryCode(map);
	}

	@Override
	public int selectFacilityIdx(Map<String, Object> map) {
		return nonOperMapper.selectFacilityIdx(map);
	}

	@Override
	public void registNonOper(Map<String, Object> map) {
		nonOperMapper.registNonOper(map);
	}

	@Override
	public Map<String, Object> selectNonOperInfo(Map<String, Object> map) {
		return nonOperMapper.selectNonOperInfo(map);
	}

	@Override
	public void modifyNonOper(Map<String, Object> map) {
		nonOperMapper.modifyNonOper(map);
	}

	@Override
	public void deleteNonOper(Map<String, Object> map) {
		nonOperMapper.deleteNonOper(map);
	}

}
