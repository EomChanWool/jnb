package apc.sl.production.prodPlan.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.production.prodPlan.service.ProdPlanService;
import apc.util.SearchVO;
@Service
public class ProdPlanServieImpl implements ProdPlanService {
	@Resource
	private ProdPlanMapper prodPlanMapper;

	@Override
	public int selectProdPlanListToCnt(SearchVO searchVO) {
		return prodPlanMapper.selectProdPlanListToCnt(searchVO);
	}

	@Override
	public List<?> selectProdPlanList(SearchVO searchVO) {
		return prodPlanMapper.selectProdPlanList(searchVO);
	}

	@Override
	public List<?> selectOrdersList() {
		return prodPlanMapper.selectOrdersList();
	}

	@Override
	public Map<String, Object> selectEstimateItemList(Map<String, Object> map) {
		return prodPlanMapper.selectEstimateItemList(map);
	}

	@Override
	public String selectItemName(String prodCd) {
		return prodPlanMapper.selectItemName(prodCd);
	}


	@Override
	public int selectExistsOrdersIdx(Map<String, Object> map) {
		return prodPlanMapper.selectExistsOrdersIdx(map);
	}

	@Override
	public int selectExistsItemCode(Map<String, Object> map) {
		return prodPlanMapper.selectExistsItemCode(map);
	}

	@Override
	public void registProdPlan(Map<String, Object> map) {
		prodPlanMapper.registProdPlan(map);
	}

	@Override
	public Map<String, Object> selectProdPlanInfo(Map<String, Object> map) {
		return prodPlanMapper.selectProdPlanInfo(map);
	}

	@Override
	public void modifyProdPlan(Map<String, Object> map) {
		prodPlanMapper.modifyProdPlan(map);
	}

	@Override
	public void deleteProdPlan(Map<String, Object> map) {
		prodPlanMapper.deleteProdPlan(map);
	}

}
