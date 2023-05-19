package apc.sl.production.prodResult.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.production.prodResult.service.ProdResultService;
import apc.util.SearchVO;
@Service
public class ProdResultServieImpl implements ProdResultService {
	@Resource
	private ProdResultMapper prodResultMapper;

	@Override
	public int selectProdResultListToCnt(SearchVO searchVO) {
		return prodResultMapper.selectProdResultListToCnt(searchVO);
	}

	@Override
	public List<?> selectProdResultList(SearchVO searchVO) {
		return prodResultMapper.selectProdResultList(searchVO);
	}

	@Override
	public List<?> selectWorkOrderList() {
		return prodResultMapper.selectWorkOrderList();
	}

	@Override
	public Map<String, Object> selectWorkOrderInfo(Map<String, Object> map) {
		return prodResultMapper.selectWorkOrderInfo(map);
	}

	@Override
	public int selectExistsWorkOrderIdx(Map<String, Object> map) {
		return prodResultMapper.selectExistsWorkOrderIdx(map);
	}

	@Override
	public int selectExistsProdResult(Map<String, Object> map) {
		return prodResultMapper.selectExistsProdResult(map);
	}

	@Override
	public int selectExistsItemBom(Map<String, Object> map) {
		return prodResultMapper.selectExistsItemBom(map);
	}

	@Override
	public void registProdResult(Map<String, Object> map) {
		prodResultMapper.registProdResult(map);
	}

	@Override
	public Map<String, Object> selectProcessSeqInfo(Map<String, Object> map) {
		return prodResultMapper.selectProcessSeqInfo(map);
	}

	@Override
	public void updateProcess(Map<String, Object> map) {
		prodResultMapper.updateProcess(map);
	}

	@Override
	public Map<String, Object> selectProdResultInfo(Map<String, Object> map) {
		return prodResultMapper.selectProdResultInfo(map);
	}

	@Override
	public Map<String, Object> selectFaultyCnt(Map<String, Object> map) {
		return prodResultMapper.selectFaultyCnt(map);
	}

	@Override
	public void modifyProdResult(Map<String, Object> map) {
		prodResultMapper.modifyProdResult(map);
	}

	@Override
	public String selectLastProcessNm(Map<String, Object> map) {
		return prodResultMapper.selectLastProcessNm(map);
	}

	@Override
	public void updateWorkOrder(Map<String, Object> map) {
		prodResultMapper.updateWorkOrder(map);
	}

	@Override
	public void deleteProdResult(Map<String, Object> map) {
		prodResultMapper.deleteProdResult(map);
	}

	@Override
	public void updateOrders(Map<String, Object> map) {
		prodResultMapper.updateOrders(map);
	}

	@Override
	public List<?> selectProdPercentList(Map<String, Object> map) {
		return prodResultMapper.selectProdPercentList(map);
	}

	@Override
	public Map<String, Object> selectEnoughStock(Map<String, Object> map) {
		return prodResultMapper.selectEnoughStock(map);
	}

	@Override
	public void updateItem(Map<String, Object> map) {
		prodResultMapper.updateItem(map);
	}

	@Override
	public void registDispensing(Map<String, Object> map) {
		prodResultMapper.registDispensing(map);
		
	}

	@Override
	public void updateProduct(Map<String, Object> map) {
		prodResultMapper.updateProduct(map);
		
	}

	@Override
	public Map<String, Object> selectPrReReSt(Map<String, Object> map) {
		return prodResultMapper.selectPrReReSt(map);
	}

	@Override
	public void updatePrReReSt(Map<String, Object> map) {
		prodResultMapper.updatePrReReSt(map);
	}

}
