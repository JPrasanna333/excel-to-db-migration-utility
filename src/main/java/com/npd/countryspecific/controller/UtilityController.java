package com.npd.countryspecific.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npd.countryspecific.service.ReadExcelService;

@RestController
public class UtilityController {
	@Value("${excel.path}")
	private String filePath;
	
	@Autowired
	private ReadExcelService readExcelService;

	@GetMapping("/test")
	public String test() {
		return "Success";
	}

	@GetMapping("/startMigrate")
	public String startMigrateData() throws IOException {
		String response = readExcelService.readAndMigrateExcelData(filePath);
		System.out.println(response);
		return response;
		

	}

}
