package com.npd.countryspecific.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.npd.countryspecific.model.ProjectClassification;
import com.npd.countryspecific.model.ProjectType;
import com.npd.countryspecific.model.Region;
import com.npd.countryspecific.model.TaskTemplate;
import com.npd.countryspecific.model.TasksDetails;
import com.npd.countryspecific.repository.ProjectClassifcationRepository;
import com.npd.countryspecific.repository.ProjectTypeRepository;
import com.npd.countryspecific.repository.RegionRepository;
import com.npd.countryspecific.repository.TaskTemplateRepository;

@Service
public class ReadExcelService {
	@Value("${excel.startRow}")
	private int startRow;

	@Value("${excel.endRow}")
	private int endRow;

	@Value("${excel.startColumn}")
	private String startColumn;

	@Value("${excel.endColumn}")
	private String endColumn;
	
	@Value("${excel.task.startColumn}")
	private String taskStartColumn;

	@Value("${excel.task.endColumn}")
	private String taskEndColumn;
	
	@Autowired
	private ProjectClassifcationRepository projectClassifcationRepository;
	
	@Autowired
	private ProjectTypeRepository projectTypeRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private TaskTemplateRepository taskTemplaRepository;

//	public void readExcel(String filePath) throws IOException {
//		List<ProjectClassification> classificationData = readExcelAndConvert(filePath);
//		projectClassifcationRepository.saveAll(classificationData);

//	}

	public void readAndMigrateExcelData(String filePath) throws IOException {
		List<ProjectClassification> dataList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheetAt(0); // Assuming you want to read the first sheet
			int startColumnIndex = CellReference.convertColStringToIndex(startColumn);
			int endColumnIndex = CellReference.convertColStringToIndex(endColumn);
			
			int taskStartColumnIndex = CellReference.convertColStringToIndex(taskStartColumn);
			int taskEndColumnIndex = CellReference.convertColStringToIndex(taskEndColumn);
			List<TaskTemplate> taskTemplateList  = taskTemplaRepository.findAll();
			for (int rowIndex = startRow - 1; rowIndex < endRow; rowIndex++) {
				
				    Row row = sheet.getRow(rowIndex);
					ProjectClassification projectClassification = new ProjectClassification();
					TasksDetails taskDetails = new TasksDetails();
					for (int columnIndex = startColumnIndex; columnIndex <= endColumnIndex; columnIndex++) {
						Cell cellValue = row.getCell(columnIndex);
							String columnName = CellReference.convertNumToColString(columnIndex);
							
							if(columnName.equalsIgnoreCase("A")) {
								Region region = regionRepository.findByDisplayName(cellValue.getStringCellValue());
								projectClassification.setRegion(region);
							}
							else if(columnName.equalsIgnoreCase("D")) {
								ProjectType projectType = projectTypeRepository.findByDisplayName(cellValue.getStringCellValue());
								projectClassification.setProjectType(projectType);
							}
							else if(columnName.equalsIgnoreCase("B")) {
								projectClassification.setProjectClassificationNumber(String.valueOf((int) cellValue.getNumericCellValue()));
							}
							else if(columnName.equalsIgnoreCase("H")) {
								System.out.println(cellValue.getStringCellValue());
								projectClassification.setProjectClassificationDescription(cellValue.getStringCellValue());
							}
							else if(columnName.equalsIgnoreCase("I")) {
							    Boolean value =	cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
								System.out.println(value);
								projectClassification.setNewFg(value);
							}
							else if(columnName.equalsIgnoreCase("J")) {
							    Boolean value =	cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
								System.out.println(value);
								projectClassification.setNewRd(value);
							}
							else if(columnName.equalsIgnoreCase("K")) {
							    Boolean value =	cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
								System.out.println(value);
								projectClassification.setNewHbc(value);
							}
							else if(columnName.equalsIgnoreCase("L")) {
							    Boolean value =	cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
								System.out.println(value);
								projectClassification.setPrimaryPackaging(value);
							}
							else if(columnName.equalsIgnoreCase("M")) {
							    Boolean value =	cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
								System.out.println(value);
								projectClassification.setSecondaryPackaging(value);
							}
					}
					//task
					for (int columnIndex = taskStartColumnIndex; columnIndex <= taskEndColumnIndex; columnIndex++) {
						String headerValue = sheet.getRow(1).getCell(columnIndex).getStringCellValue();
//						System.out.println(headerValue);
				        String[] parts = headerValue.split("\\s*\\n\\s*");
				        String taskName = parts[0];
				        String taskDescription = parts[1];
				        Boolean isManagerialTask = false;
				        Boolean isRegTask = false;
//				        System.out.println(taskName);
//				        System.out.println(taskDescription);
				      
                        System.out.println(taskTemplateList);
					}
				
			}
			
			
			workbook.close();
		}

	}

	}
