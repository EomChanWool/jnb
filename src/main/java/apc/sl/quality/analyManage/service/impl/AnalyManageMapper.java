package apc.sl.quality.analyManage.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("AnalyManageMapper")
public interface AnalyManageMapper {

	int selectAnalyManageListToCnt(SearchVO searchVO);

	List<?> selectAnalyManageList(SearchVO searchVO);

	List<?> selectWorkOrderList(String str);

	List<?> selectDocumentList(String str);

	void registAnalysisData(Map<String, Object> map);

	Map<String, Object> selectAzIdx();

	int selectExistsAzIdx(Map<String, Object> map);

	int selectExistsDocumentIdx(Map<String, Object> map);

	void registAnalyManage(Map<String, Object> map);

	int selectExistsProdResult(Map<String, Object> map);

	Map<String, Object> selectAnalyManageInfo(Map<String, Object> map);

	void modifyAnalysisData(Map<String, Object> map);

	void modifyAnalyManage(Map<String, Object> map);

	void deleteAnalyManage(Map<String, Object> map);

	Map<String, Object> selectAnalysisCnt();

	void updateDocumnetState(Map<String, Object> map);

}
