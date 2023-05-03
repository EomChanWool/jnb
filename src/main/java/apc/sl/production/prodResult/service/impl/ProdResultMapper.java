package apc.sl.production.prodResult.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ProdResultMapper")
public interface ProdResultMapper {

	int selectProdResultListToCnt(SearchVO searchVO);

	List<?> selectProdResultList(SearchVO searchVO);

	List<?> selectWorkOrderList();

	Map<String, Object> selectWorkOrderInfo(Map<String, Object> map);

	int selectExistsWorkOrderIdx(Map<String, Object> map);

	int selectExistsProdResult(Map<String, Object> map);

	int selectExistsItemBom(Map<String, Object> map);

	void registProdResult(Map<String, Object> map);

	Map<String, Object> selectProcessSeqInfo(Map<String, Object> map);

	void updateProcess(Map<String, Object> map);

	Map<String, Object> selectProdResultInfo(Map<String, Object> map);

	Map<String, Object> selectFaultyCnt(Map<String, Object> map);

	void modifyProdResult(Map<String, Object> map);

	String selectLastProcessNm(Map<String, Object> map);

	void updateWorkOrder(Map<String, Object> map);

	void deleteProdResult(Map<String, Object> map);

	void updateOrders(Map<String, Object> map);

	List<?> selectProdPercentList(Map<String, Object> map);

	Map<String, Object> selectEnoughStock(Map<String, Object> map);

	void updateItem(Map<String, Object> map);
	
	void registDispensing(Map<String, Object> map);

}
