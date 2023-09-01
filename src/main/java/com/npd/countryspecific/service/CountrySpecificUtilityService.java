package com.npd.countryspecific.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.npd.countryspecific.model.ProjectClassification;
import com.npd.countryspecific.model.ProjectClassificationTasksDetails;
import com.npd.countryspecific.model.ProjectType;
import com.npd.countryspecific.model.TaskTemplate;
import com.npd.countryspecific.model.TasksDetails;
import com.npd.countryspecific.model.TasksDetailsPredecessorTaskTemplate;
import com.npd.countryspecific.model.TasksDetailsPrimaryTaskTemplate;
import com.npd.countryspecific.repository.PredeccessorTaskDetailsRepository;
import com.npd.countryspecific.repository.PrimaryTaskDetailsRepository;
import com.npd.countryspecific.repository.ProjectClassificationRepository;
import com.npd.countryspecific.repository.ProjectClassificationTaskRepository;
import com.npd.countryspecific.repository.ProjectTypeRepository;
import com.npd.countryspecific.repository.TaskDetailsRepository;
import com.npd.countryspecific.repository.TaskTemplateRepository;

@Service
public class CountrySpecificUtilityService {
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

	@Value("${com.monster.npd.org.id}")
	private String orgId;

	@Autowired
	private Environment env;

	@Autowired
	private ProjectClassificationRepository projectClassificationRepository;

	@Autowired
	private ProjectTypeRepository projectTypeRepository;

	@Autowired
	private TaskTemplateRepository taskTemplaRepository;

	@Autowired
	private TaskDetailsRepository taskDetailsRepository;

	@Autowired
	private ProjectClassificationTaskRepository projectClassificationTaskRepository;

	@Autowired
	private PredeccessorTaskDetailsRepository predeccessorTaskDetailsRepository;

	@Autowired
	private PrimaryTaskDetailsRepository primaryTaskDetailsRepository;

	@Transactional
	public String readAndMigrateExcelData(String filePath) throws IOException {

		try (FileInputStream fis = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(fis);
//			Sheet sheet = workbook.getSheetAt(0); 
			int startColumnIndex = CellReference.convertColStringToIndex(startColumn);
			int endColumnIndex = CellReference.convertColStringToIndex(endColumn);

			int taskStartColumnIndex = CellReference.convertColStringToIndex(taskStartColumn);
//			System.out.println(taskEndColumn);
			int taskEndColumnIndex = CellReference.convertColStringToIndex(taskEndColumn);
			String templateNo = "";

			List<TaskTemplate> taskTemplateList = taskTemplaRepository.findAll();
			Integer maxTaskId = taskDetailsRepository.getMaxTaskId();
			Integer maxProjectClassificationId = projectClassificationRepository.getMaxProjectId();

			for (int rowIndex = startRow - 1; rowIndex < endRow; rowIndex++) {
				Sheet sheet = workbook.getSheetAt(0);
				maxProjectClassificationId = maxProjectClassificationId == null ? 49188 : maxProjectClassificationId;
				String regionName = "";
				ProjectClassification projectClassification = new ProjectClassification();
				TasksDetails taskDetails = new TasksDetails();
				TaskTemplate taskTemplate = new TaskTemplate();
				ProjectClassificationTasksDetails projectClassificationTasksDetails = new ProjectClassificationTasksDetails();
				TasksDetailsPredecessorTaskTemplate preTaskTemplate = new TasksDetailsPredecessorTaskTemplate();
				TasksDetailsPrimaryTaskTemplate primTaskTemplate = new TasksDetailsPrimaryTaskTemplate();
				projectClassification.setId(maxProjectClassificationId + 13);
				Row row = sheet.getRow(rowIndex);
				for (int columnIndex = startColumnIndex; columnIndex <= endColumnIndex; columnIndex++) {
					Cell cellValue = row.getCell(columnIndex);
					String columnName = CellReference.convertNumToColString(columnIndex);
					projectClassification.setRegion(1);// Always EMEA in table
					projectClassification.setItemStatusId(1);
					projectClassification.setOrganizationid(Integer.parseInt(orgId));

					if (columnName.equalsIgnoreCase("A")) {
						regionName = cellValue.getStringCellValue();// for retrieving sheet name
					} else if (columnName.equalsIgnoreCase("B")) {
						ProjectType projectType = projectTypeRepository.findByDisplayName(cellValue.getStringCellValue());
						projectClassification.setProjectType(projectType);
					} else if (columnName.equalsIgnoreCase("C")) {
						projectClassification.setProjectClassificationNumber(String.valueOf((int) cellValue.getNumericCellValue()));
					} else if (columnName.equalsIgnoreCase("E")) {
//						System.out.println(cellValue.getStringCellValue());
						projectClassification.setProjectClassificationDescription(cellValue.getStringCellValue());
					} else if (columnName.equalsIgnoreCase("F")) {
						Boolean value = cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
//						System.out.println(value);
						projectClassification.setNewFg(value);
					} else if (columnName.equalsIgnoreCase("G")) {
						Boolean value = cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
//						System.out.println(value);
						projectClassification.setNewRd(value);
					} else if (columnName.equalsIgnoreCase("H")) {
						Boolean value = cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
//						System.out.println(value);
						projectClassification.setNewHbc(value);
					} else if (columnName.equalsIgnoreCase("I")) {
						Boolean value = cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
//						System.out.println(value);
						projectClassification.setPrimaryPackaging(value);
					} else if (columnName.equalsIgnoreCase("J")) {
						Boolean value = cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
//						System.out.println(value);
						projectClassification.setSecondaryPackaging(value);
					}
//					else if (columnName.equalsIgnoreCase("K")) {
//						Boolean value = cellValue.getStringCellValue().equalsIgnoreCase("Y") ? true : false;
//					change lead Formula column name
//						projectClassification.setSecondaryPackaging(value);
//					}
				}

				// task iteration
				for (int columnIndex = taskStartColumnIndex; columnIndex <= taskEndColumnIndex; columnIndex++) {

//					System.out.println(columnIndex);
					String columnName = CellReference.convertNumToColString(columnIndex);
//					System.out.println(columnName);
					sheet = workbook.getSheetAt(0);
					String headerValue = sheet.getRow(1).getCell(columnIndex).getStringCellValue();
//					System.out.println(headerValue);
					String[] parts = headerValue.split("\\s*\\n\\s*");
					String taskName = parts[0];
					String taskDescription = parts[1];
					Boolean isManagerialTask = false;
					Boolean isRegTask = false;
					String[] formattedpreTask = {};
					String[] formattedPrimTask = {};

//					System.out.println(region);
					sheet = workbook.getSheet(regionName);
//					System.out.println(sheet.getSheetName());
					Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

					if (cell != null) {
						String cellValue = getCellValueAsString(cell);
						if (cellValue.equals("M")) {
							isManagerialTask = true;
							isRegTask = false;
						} else if (cellValue.equals("Y")) {
							isManagerialTask = false;
							isRegTask = true;
						} 

						System.out.println(taskName + " insertion started");
//						System.out.println(taskDetails.getDuration());
//						System.out.println(taskDescription);
						final Boolean finalIsManagerialTask = isManagerialTask;
						final Boolean finalIsRegTask = isRegTask;
						Optional<TaskTemplate> result = taskTemplateList.stream()
								.filter(template -> template.getTaskName().equalsIgnoreCase(taskName)
										&& template.getTaskDescription().equalsIgnoreCase(taskDescription)
										&& template.getIsManagerialTask().equals(finalIsManagerialTask)
										&& template.getIsRegistrationTask().equals(finalIsRegTask)

								).findFirst();

						if (result.isPresent()) {
							taskTemplate = result.get();
//					            System.out.println(taskTemplate.getTaskName());
							Row matchedRow = getRowByCellValue(sheet, taskName, "Task Name");
							if (matchedRow != null) {
								maxTaskId = maxTaskId == null ? 12108001 : maxTaskId;
								taskDetails.setId(++maxTaskId);
								taskDetails.setTasktemplate(taskTemplate);
								// Task 13 fg and conc duration
								if (taskTemplate.getTaskName().equalsIgnoreCase("TASK 13")) {
									if (regionName.equalsIgnoreCase("EMEA")) {
										taskDetails.setDuration(taskTemplate.getFgOrConc().equalsIgnoreCase("Fg") ? 2 : 4);
									} else {
										taskDetails.setDuration(taskTemplate.getFgOrConc().equalsIgnoreCase("Fg") ? 4 : 4);
									}
								} else {
									taskDetails.setDuration((int) matchedRow.getCell(6).getNumericCellValue());
								}
//								System.out.println("max task id");
//								System.out.println(taskDetails.getId());

								taskDetails.setTaskSequence((int) matchedRow.getCell(0).getNumericCellValue());
								if(regionName.equalsIgnoreCase("EMEA") || regionName.equalsIgnoreCase("LEAD")) {
									taskDetails.setOrderOfExecution((int) matchedRow.getCell(0).getNumericCellValue());
								}
								else if(regionName.equalsIgnoreCase("UAE")) {
									taskDetails.setOrderOfExecution((int) matchedRow.getCell(9).getNumericCellValue());
								}
//								System.out.println("sequenceCheck");
//								System.out.println(taskDetails.getTaskSequence());
								taskDetails.setItemStatusId(1);
								String taskOwnerName = matchedRow.getCell(4).getStringCellValue().replace(" ", "");
								String rmRoleName = matchedRow.getCell(5).getStringCellValue().replace(" ", "");
								taskDetails.setTaskOwnerId(Integer.parseInt(getRoleId(taskOwnerName)));
								taskDetails.setRmRoleId(Integer.parseInt(getRoleId(rmRoleName)));
								taskDetails.setOrganizationid(Integer.parseInt(orgId));
								String preTask = getCellValueAsString(matchedRow.getCell(2));
								String primTask = getCellValueAsString(matchedRow.getCell(3));
								formattedpreTask = getPreOrPrimTask(preTask);
								formattedPrimTask = getPreOrPrimTask(primTask);

							} else {
								System.out.println(taskName+ " row not found in other linked sheet...............................");
							}

						} else {
							if (!taskName.equalsIgnoreCase("TASK 08")) {
								System.out.println(taskName+ " template not found.......................................................");
							}
//								return "failed";
						}
					} else {
						System.out.println("Null cell in other sheet while linking.........................................");
//						return "failed";

					}
					ProjectClassification projectClassResponse = projectClassificationRepository.save(projectClassification);
					TasksDetails tasksDetailsResponse = taskDetailsRepository.save(taskDetails);
					projectClassificationTasksDetails.setProjectClassification(projectClassResponse);
					projectClassificationTasksDetails.setTaskDetails(tasksDetailsResponse);
					projectClassificationTasksDetails.setOrganizationid(Integer.parseInt(orgId));
					projectClassResponse.getProjectClassificationTasksDetails().add(projectClassificationTasksDetails);
					tasksDetailsResponse.getProjectClassificationTasksDetails().add(projectClassificationTasksDetails);
					projectClassificationRepository.save(projectClassResponse);
					TasksDetails finaltasksDetailsResponse = taskDetailsRepository.save(tasksDetailsResponse);
					projectClassificationTaskRepository.save(projectClassificationTasksDetails);
					System.out.println(taskName + " Predeccessor and Primary task insertion started");
					for (String preTaskName : formattedpreTask) {
						Optional<TaskTemplate> result = taskTemplateList.stream()
								.filter(template -> template.getTaskName().equalsIgnoreCase(preTaskName)).findFirst();
						if (result.isPresent()) {
							preTaskTemplate.setTaskDetail(finaltasksDetailsResponse);
							preTaskTemplate.setTaskTemplate(result.get());
							preTaskTemplate.setOrganizationid(Integer.parseInt(orgId));
							TasksDetailsPredecessorTaskTemplate preTaskResponse = predeccessorTaskDetailsRepository.save(preTaskTemplate);
							finaltasksDetailsResponse.getPretasksTemplates().add(preTaskResponse);
						}

					}

					for (String primTaskName : formattedPrimTask) {
						Optional<TaskTemplate> result = taskTemplateList.stream()
								.filter(template -> template.getTaskName().equalsIgnoreCase(primTaskName)).findFirst();
						if (result.isPresent()) {
							primTaskTemplate.setTaskDetail(finaltasksDetailsResponse);
							primTaskTemplate.setTaskTemplate(result.get());
							primTaskTemplate.setOrganizationid(Integer.parseInt(orgId));
							TasksDetailsPrimaryTaskTemplate primTaskResponse = primaryTaskDetailsRepository.save(primTaskTemplate);
							finaltasksDetailsResponse.getTaskPrimaryTaskTemplates().add(primTaskResponse);
						}
					}

					taskDetailsRepository.save(finaltasksDetailsResponse);
					System.out.println(taskName + " Predeccessor and Primary task insertion completed");
					templateNo = projectClassification.getProjectClassificationNumber();
				}
				System.out.println(templateNo+ " classification number template created succesfully.............................................");
			}
			workbook.close();
			return "Sucesss";

		}
	}

	
	private String[] getPreOrPrimTask(String tasks) {
	    String[] values = tasks.split(",");
	    String[] formattedValues = new String[values.length];

	    for (int i = 0; i < values.length; i++) {
	        String trimmedValue = values[i].trim();
	        if (!trimmedValue.isEmpty()) {
	            if (trimmedValue.toUpperCase().contentEquals("CP")) { // Check if the value contains CPs
	                try {
	                    int num = Integer.parseInt(trimmedValue);
	                    formattedValues[i] = "TASK " + String.format("%02d", num);
	                } catch (NumberFormatException e) {
	                    formattedValues[i] = "TASK " + trimmedValue;
	                }
	            } else {
	                formattedValues[i] = trimmedValue; // Treat CP1 as CP1"
	            }
	        }
	    }

	    return formattedValues;
	}






	private String getRoleId(String role) {
		switch (role.toLowerCase()) {
		case "e2epm":
			return this.env.getProperty("com.monster.role.e2e.pm");
		case "corppm":
			return this.env.getProperty("com.monster.role.corp.pm");
		case "opspm":
			return this.env.getProperty("com.monster.role.ops.pm");
		case "projectspecialist":
			return this.env.getProperty("com.monster.role.project.specialist");
		case "gfg":
			return this.env.getProperty("com.monster.role.gfg");
		case "rtm":
			return this.env.getProperty("com.monster.role.rtm");
		case "secondaryawspecialist":
			return this.env.getProperty("com.monster.role.secondary.aw.specialist");
		case "regulatoryaffairs":
			return this.env.getProperty("com.monster.role.regulatory.affairs");
		case "fpms":
			return this.env.getProperty("com.monster.role.fpms");

		default:
			throw new IllegalArgumentException("Invalid role: " + role);
		}
	}

	private Row getRowByCellValue(Sheet sheet, String cellValue, String columnName) {
		int columnIndex = getColumnIndexByName(sheet, columnName);
//		System.out.println(columnIndex);
		if (columnIndex == -1) {
			throw new IllegalArgumentException("Column '" + columnName + "' not found in the sheet.");
		}

		for (Row row : sheet) {
			Cell cell = row.getCell(columnIndex);
//			System.out.println(cell.getStringCellValue());
			if (cell != null && cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(cellValue)) {
				return row;
			}
		}
		return null;

	}

	private int getColumnIndexByName(Sheet sheet, String columnName) {
		Row headerRow = sheet.getRow(0);
		for (Cell cell : headerRow) {
			if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(columnName)) {
				return cell.getColumnIndex();
			}
		}
		return -1;
	}

	public String getCellValueAsString(Cell cell) {
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf((int) cell.getNumericCellValue());
		default:
			return "";
		}
	}
}
