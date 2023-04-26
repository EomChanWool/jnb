package apc.sl.basicInfo.badItem.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BadItemMapper")
public interface BadItemMapper {

	int selectBadItemListToCnt(SearchVO searchVO);

	List<?> selectBadItemList(SearchVO searchVO);

	List<?> selectBadCateList();

	void registBadItem(Map<String, Object> map);

	Map<String, Object> selectBadItemInfo(Map<String, Object> map);

	void modifyBadItem(Map<String, Object> map);

	void deleteBadItem(Map<String, Object> map);

}
