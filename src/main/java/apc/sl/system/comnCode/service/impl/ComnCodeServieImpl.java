package apc.sl.system.comnCode.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.system.comnCode.service.ComnCodeService;
import apc.util.SearchVO;
@Service
public class ComnCodeServieImpl implements ComnCodeService {
	@Resource
	private ComnCodeMapper comnCodeMapper;

	@Override
	public int selectComnCodeListToCnt(SearchVO searchVO) {
		return comnCodeMapper.selectComnCodeListToCnt(searchVO);
	}

	@Override
	public List<?> selectComnCodeList(SearchVO searchVO) {
		return comnCodeMapper.selectComnCodeList(searchVO);
	}

	@Override
	public void registComnCode(Map<String, Object> map) {
		comnCodeMapper.registComnCode(map);
	}

	@Override
	public Map<String, Object> selectComnCodeInfo(Map<String, Object> map) {
		return comnCodeMapper.selectComnCodeInfo(map);
	}

	@Override
	public void modifyComnCode(Map<String, Object> map) {
		comnCodeMapper.modifyComnCode(map);
	}

	@Override
	public void deleteComnCode(Map<String, Object> map) {
		comnCodeMapper.deleteComnCode(map);
	}

}
