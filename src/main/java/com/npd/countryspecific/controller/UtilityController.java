package com.npd.countryspecific.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npd.countryspecific.service.ReadExcelService;

@RestController
public class UtilityController {
	
	@Autowired
	private ReadExcelService readExcelService;
	
	@Autowired
	private Environment environment;

	@GetMapping("/test")
	public String test() {
		return "Success";
	}

	@GetMapping("/startMigrate")
	public void startMigrateData() throws IOException {
		readExcelService.readExcel(this.environment.getProperty("excel.path"));

	}

}
