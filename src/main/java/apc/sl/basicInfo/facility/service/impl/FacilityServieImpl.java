package apc.sl.basicInfo.facility.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.facility.service.FacilityService;
import apc.util.SearchVO;

@Service
public class FacilityServieImpl implements FacilityService {

	@Resource
	private FacilityMapper facilityMapper;

	@Override
	public int selectFacilityListToCnt(SearchVO searchVO) {
		return facilityMapper.selectFacilityListToCnt(searchVO);
	}
	
	@Override
	public List<?> selectFacilityList(SearchVO searchVO) {
		return facilityMapper.selectFacilityList(searchVO);
	}
	
	@Override
	public List<?> selectFactoryList() {
		return facilityMapper.selectFactoryList();
	}

	@Override
	public Map<String, Object> selectFacility(Map<String, Object> map) {
		
		return facilityMapper.selectFacility(map);
	}

	@Override
	public int selectExistsFacility(Map<String, Object> map) {
		
		return facilityMapper.selectExistsFacility(map);
	}

	@Override
	public void modifyFacility(Map<String, Object> map) {
		facilityMapper.modifyFacility(map);
		
	}

	@Override
	public void registFacility(Map<String, Object> map) {
		facilityMapper.registFacility(map);
		
	}

	@Override
	public void deleteFacility(Map<String, Object> map) {
		facilityMapper.deleteFacility(map);
		
	}

	@Override
	public void registNonOpSt(Map<String, Object> map) {
		facilityMapper.registNonOpSt(map);
		
	}


	@Override
	public void modifyNonOp(Map<String, Object> map) {
		facilityMapper.modifyNonOp(map);
		
	}

}
