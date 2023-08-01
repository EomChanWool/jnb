package apc.sl.basicInfo.item.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.item.service.ItemService;
import apc.util.SearchVO;
@Service
public class ItemServieImpl implements ItemService {
	@Resource
	private ItemMapper itemMapper;

	@Override
	public int selectItemListToCnt(SearchVO searchVO) {
		return itemMapper.selectItemListToCnt(searchVO);
	}

	@Override
	public List<?> selectItemList(SearchVO searchVO) {
		return itemMapper.selectItemList(searchVO);
	}

	@Override
	public List<?> selectCompanyList() {
		return itemMapper.selectCompanyList();
	}

	@Override
	public List<?> selectComnCodeList() {
		return itemMapper.selectComnCodeList();
	}

	@Override
	public List<?> selectAccountList() {
		return itemMapper.selectAccountList();
	}

	@Override
	public int selectExistsCompany(Map<String, Object> map) {
		return itemMapper.selectExistsCompany(map);
	}

	@Override
	public void registItem(Map<String, Object> map) {
		itemMapper.registItem(map);
	}

	@Override
	public Map<String, Object> selectItemInfo(Map<String, Object> map) {
		return itemMapper.selectItemInfo(map);
	}

	@Override
	public void modifyItem(Map<String, Object> map) {
		itemMapper.modifyItem(map);
	}

	@Override
	public void deleteItem(Map<String, Object> map) {
		itemMapper.deleteItem(map);
	}

	@Override
	public List<?> selectTankList() {
		return itemMapper.selectTankList();
	}

}
