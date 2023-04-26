package apc.sl.basicInfo.badItem.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface BadItemService {

	int selectBadItemListToCnt(SearchVO searchVO);

	List<?> selectBadItemList(SearchVO searchVO);

	List<?> selectBadCateList();

	void registBadItem(Map<String, Object> map);

	Map<String, Object> selectBadItemInfo(Map<String, Object> map);

	void modifyBadItem(Map<String, Object> map);

	void deleteBadItem(Map<String, Object> map);

}
