package apc.sl.production.prodPlan.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface ProdPlanService {

	int selectProdPlanListToCnt(SearchVO searchVO);

	List<?> selectProdPlanList(SearchVO searchVO);

	List<?> selectOrdersList();

	Map<String, Object> selectEstimateItemList(Map<String, Object> map);

	String selectItemName(String prodCd);

	int selectExistsOrdersIdx(Map<String, Object> map);

	int selectExistsItemCode(Map<String, Object> map);

	void registProdPlan(Map<String, Object> map);

	Map<String, Object> selectProdPlanInfo(Map<String, Object> map);

	void modifyProdPlan(Map<String, Object> map);

	void deleteProdPlan(Map<String, Object> map);

}
