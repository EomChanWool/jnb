package apc.sl.basicInfo.badItem.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.badItem.service.BadItemService;
import apc.util.SearchVO;
@Service
public class BadItemServieImpl implements BadItemService {
	@Resource
	private BadItemMapper badItemMapper;

	@Override
	public int selectBadItemListToCnt(SearchVO searchVO) {
		return badItemMapper.selectBadItemListToCnt(searchVO);
	}

	@Override
	public List<?> selectBadItemList(SearchVO searchVO) {
		return badItemMapper.selectBadItemList(searchVO);
	}

	@Override
	public List<?> selectBadCateList() {
		return badItemMapper.selectBadCateList();
	}

	@Override
	public void registBadItem(Map<String, Object> map) {
		badItemMapper.registBadItem(map);
	}

	@Override
	public Map<String, Object> selectBadItemInfo(Map<String, Object> map) {
		return badItemMapper.selectBadItemInfo(map);
	}

	@Override
	public void modifyBadItem(Map<String, Object> map) {
		badItemMapper.modifyBadItem(map);
	}

	@Override
	public void deleteBadItem(Map<String, Object> map) {
		badItemMapper.deleteBadItem(map);
	}

}
