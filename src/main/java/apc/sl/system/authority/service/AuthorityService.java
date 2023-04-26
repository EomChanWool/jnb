package apc.sl.system.authority.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface AuthorityService {

	int selectProgramAccessLev(String str);

	int selectAccess(String str);

}
