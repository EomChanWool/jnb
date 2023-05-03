package apc.sl.basicInfo.facility.service.impl;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

}
