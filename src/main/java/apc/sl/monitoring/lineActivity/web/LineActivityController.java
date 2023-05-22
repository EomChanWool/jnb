package apc.sl.monitoring.lineActivity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import apc.sl.monitoring.lineActivity.service.LineActivityService;

@Controller
public class LineActivityController {
	
	@Autowired
	private LineActivityService lineActivityService;

}
