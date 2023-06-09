package apc.sl.production.lot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.production.lot.service.LotService;
import apc.util.SearchVO;

@Service
public class LotServieImpl implements LotService{

	@Resource LotMapper lotMapper;

	@Override
	public int selectLotListToCnt(SearchVO searchVO) {
		
		return lotMapper.selectLotListToCnt(searchVO);
	}

	@Override
	public List<?> selectLotList(SearchVO searchVO) {
		
		return lotMapper.selectLotList(searchVO);
	}
}
