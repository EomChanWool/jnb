package apc.sl.basicInfo.bom.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BomMapper")
public interface BomMapper {

	int selectBomListToCnt(SearchVO searchVO);

	List<?> selectBomList(SearchVO searchVO);

	List<?> selectProdList();

	List<?> selectMaterialList();

	List<?> selectItemInfo(Map<String, Object> map);

	int selectExistsProdCd(Map<String, Object> map);

	int selectExistsBom(Map<String, Object> map);

	void registBom(Map<String, Object> map);

	String selectBomIdx();

	void registBomInfo(Map<String, Object> map);

	Map<String, Object> selectBomInfo(Map<String, Object> map);

	void modifyBom(Map<String, Object> map);

	void modifyBomInfo(Map<String, Object> map);

	void deleteBom(Map<String, Object> map);

	void deleteBomInfo(Map<String, Object> map);

}
