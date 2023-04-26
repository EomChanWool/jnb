package apc.sl.material.invest.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface InvestService {

	int selectInvestListToCnt(SearchVO searchVO);

	List<?> selectInvestList(SearchVO searchVO);

	List<?> selectWorkOrderList();

	List<?> selectMaterialList();

	void registInvest(Map<String, Object> map);

	void updateMaterialStock(Map<String, Object> map);

	void updateWorkOrder(Map<String, Object> map);

	void updateOrders(Map<String, Object> map);

	int selectInvestWorkOrder(Map<String, Object> map);

	Map<String, Object> selectInvestInfo(Map<String, Object> map);

	void modifyInvest(Map<String, Object> map);

	void deleteInvest(Map<String, Object> map);
}
