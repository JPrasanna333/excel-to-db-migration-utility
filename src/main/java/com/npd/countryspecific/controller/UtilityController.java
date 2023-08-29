package com.npd.countryspecific.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.npd.countryspecific.model.RequestBodyData;
import com.npd.countryspecific.service.CountrySpecificUtilityService;
import com.npd.countryspecific.service.ProjectNewFieldsUpdateService;
import com.npd.countryspecific.service.ProjectReindexService;
import com.npd.countryspecific.service.ProjectTaskClassificationUpdateService;
import com.npd.countryspecific.service.RequestClassificationUpdateService;
import com.npd.countryspecific.service.RequestReindexService;
import com.npd.countryspecific.service.TaskReindexService;

@RestController
public class UtilityController {
	@Value("${excel.path}")
	private String filePath;

	@Value("${qa.submissionproject.table.name}")
	private String submissionProjectTableName;

	@Value("${qa.submissionrequest.table.name}")
	private String submissionRequestTableName;

	@Value("${qa.requestreindexlog.table.name}")
	private String requestReindexLogTableName;

	@Value("${qa.submissiontask.table.name}")
	private String submissionTaskTableName;

	@Value("${qa.project.itemid}")
	private String projectItemId;

	@Value("${qa.task.itemid}")
	private String taskItemId;

	private CountrySpecificUtilityService countrySpecificUtilityService;
	private ProjectTaskClassificationUpdateService projectTaskClassificationUpdateService;
	private RequestClassificationUpdateService requestClassificationUpdateService;
	private RequestReindexService requestReindexService;
	private ProjectReindexService projectReindexService;
	private ProjectNewFieldsUpdateService projectNewFieldsUpdateService;
	private TaskReindexService taskReindexService;

	@Autowired
	public UtilityController(CountrySpecificUtilityService countrySpecificUtilityService,
			ProjectTaskClassificationUpdateService projectTaskClassificationUpdateService,
			RequestClassificationUpdateService requestClassificationUpdateService,
			RequestReindexService requestReindexService, ProjectNewFieldsUpdateService projectNewFieldsUpdateService,
			ProjectReindexService projectReindexService, TaskReindexService taskReindexService) {
		super();
		this.countrySpecificUtilityService = countrySpecificUtilityService;
		this.projectTaskClassificationUpdateService = projectTaskClassificationUpdateService;
		this.requestClassificationUpdateService = requestClassificationUpdateService;
		this.requestReindexService = requestReindexService;
		this.projectNewFieldsUpdateService = projectNewFieldsUpdateService;
		this.projectReindexService = projectReindexService;
		this.taskReindexService = taskReindexService;

	}

	@GetMapping("/test")
	public String test() {
		return "Success";
	}

	@GetMapping("/startMigrate")
	public String startMigrateData() throws IOException {
		String response = countrySpecificUtilityService.readAndMigrateExcelData(filePath);
		System.out.println(response);
		return response;
	}

	@GetMapping("/generateScripts")
	public void generateScripts() throws Exception {
//		projectTaskClassificationUpdateService.generateProjectTaskClassUpdateStatements(filePath,submissionProjectTableName);
//		projectTaskClassificationUpdateService.generateProjectTaskClassUpdateStatements(filePath,submissionTaskTableName);
//		requestClassificationUpdateService.generateSqlUpdateStatements(filePath, submissionRequestTableName);
		requestReindexService.generateReindexInsertScripts(filePath,requestReindexLogTableName);
//		projectNewFieldsUpdateService.generateProjectNewFieldsUpdateScripts(filePath, submissionProjectTableName);

	}

	@GetMapping("/generateProjectReindexScripts")
	public void generateProjectReindexScripts(@RequestBody(required = true)RequestBodyData projectData) throws Exception {
		projectReindexService.generateProjectReindexScripts(filePath, projectItemId,projectData);
	}

	@GetMapping("/generateTaskReindexScripts")
	public void generateTaskReindexScripts(@RequestBody(required = true)RequestBodyData taskData) throws Exception {
		taskReindexService.generateTaskReindexScripts(filePath, taskItemId,taskData);
	}

}
