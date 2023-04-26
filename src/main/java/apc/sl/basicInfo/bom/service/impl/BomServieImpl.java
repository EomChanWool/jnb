package apc.sl.basicInfo.bom.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.bom.service.BomService;
import apc.util.SearchVO;
@Service
public class BomServieImpl implements BomService {
	@Resource
	private BomMapper bomMapper;

	@Override
	public int selectBomListToCnt(SearchVO searchVO) {
		return bomMapper.selectBomListToCnt(searchVO);
	}

	@Override
	public List<?> selectBomList(SearchVO searchVO) {
		return bomMapper.selectBomList(searchVO);
	}

	@Override
	public List<?> selectProdList() {
		return bomMapper.selectProdList();
	}

	@Override
	public List<?> selectMaterialList() {
		return bomMapper.selectMaterialList();
	}

	@Override
	public List<?> selectItemInfo(Map<String, Object> map) {
		return bomMapper.selectItemInfo(map);
	}

	@Override
	public int selectExistsProdCd(Map<String, Object> map) {
		return bomMapper.selectExistsProdCd(map);
	}

	@Override
	public int selectExistsBom(Map<String, Object> map) {
		return bomMapper.selectExistsBom(map);
	}

	@Override
	public void registBom(Map<String, Object> map) {
		bomMapper.registBom(map);
	}

	@Override
	public String selectBomIdx() {
		return bomMapper.selectBomIdx();
	}

	@Override
	public void registBomInfo(Map<String, Object> map) {
		bomMapper.registBomInfo(map);
	}

	@Override
	public Map<String, Object> selectBomInfo(Map<String, Object> map) {
		return bomMapper.selectBomInfo(map);
	}

	@Override
	public void modifyBom(Map<String, Object> map) {
		bomMapper.modifyBom(map);
	}

	@Override
	public void modifyBomInfo(Map<String, Object> map) {
		bomMapper.modifyBomInfo(map);
	}

	@Override
	public void deleteBom(Map<String, Object> map) {
		bomMapper.deleteBom(map);
	}

	@Override
	public void deleteBomInfo(Map<String, Object> map) {
		bomMapper.deleteBomInfo(map);
	}

}
