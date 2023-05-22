package apc.sl.monitoring.lineActivity.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.monitoring.lineActivity.service.LineActivityService;


@Service
public class LineActivityServieImpl implements LineActivityService {

	@Resource
	private LineActivityMapper lineActivityMapper;
	
}
