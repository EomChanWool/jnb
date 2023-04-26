package apc.sl.material.outsourcing.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("OutsourcingMapper")
public interface OutsourcingMapper {

	int selectOutsourcingListToCnt(SearchVO searchVO);

	List<?> selectOutsourcingList(SearchVO searchVO);

	List<?> selectWorkOrderList();

	List<?> selectWorkOrderInfo(Map<String, Object> map);

	int selectExistsWoIdx(Map<String, Object> map);

	void updateWorkOrder(Map<String, Object> map);

	void registOutSourcing(Map<String, Object> map);

	Map<String, Object> selectOutsourcingInfo(Map<String, Object> map);

	void modifyOutsourcing(Map<String, Object> map);

	void deleteOutsourcing(Map<String, Object> map);

}
