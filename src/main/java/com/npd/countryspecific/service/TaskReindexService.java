package com.npd.countryspecific.service;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service
public class TaskReindexService {

	public void generateTaskReindexScripts(String filePath, String taskItemId) throws Exception {
		try (FileInputStream fis = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet taskReindexingSheet = workbook.getSheet("TaskReindexing");
			boolean isFirstJson = true;
			int totalCount = 0;
			for (int rowIndex = 1; rowIndex <= taskReindexingSheet.getLastRowNum(); rowIndex++) {
				Row row = taskReindexingSheet.getRow(rowIndex);
				String itemId = row.getCell(0) != null
						? taskItemId + String.valueOf((long) row.getCell(0).getNumericCellValue())
						: null;
				String earlyClassificationNo = row.getCell(1) != null
						? String.valueOf((long) row.getCell(1).getNumericCellValue())
						: null;
				String currentFinalClassificationNo = row.getCell(2) != null
						? String.valueOf((long) row.getCell(2).getNumericCellValue())
						: null;
//				long currentFinalClassificationNo = row.getCell(2).getNumericCellValue();
				String projectFinalClassificationNo = row.getCell(3) != null
						? String.valueOf((long) row.getCell(3).getNumericCellValue())
						: null;
				String taskFinalClassification = row.getCell(4) != null
						? String.valueOf((long) row.getCell(4).getNumericCellValue())
						: null;
				String taskDraftClassification = row.getCell(5) != null
						? String.valueOf((long) row.getCell(5).getNumericCellValue())
						: null;				

				if (earlyClassificationNo != null) {
					JsonObject jsonObject = new JsonObject();
					jsonObject.addProperty("ITEM_ID", itemId);
					jsonObject.addProperty("CONTENT_TYPE", "MPM_TASK");
					jsonObject.add("PR_EARLY_PROJECT_CLASSIFICATION", createSetValueJsonObject(earlyClassificationNo));
					jsonObject.add("PR_EARLY_PROJECT_CLASSIFICATION_facet", createSetValueJsonObject(earlyClassificationNo));

					if (currentFinalClassificationNo != null) {
						jsonObject.add("NPD_PROJECT_CURRENT_FINAL_CLASSIFICATION",createSetValueJsonObject(currentFinalClassificationNo));
					}
					if (projectFinalClassificationNo != null) {
						jsonObject.add("NPD_PROJECT_PROJECT_FINAL_PROJECT_CLASSIFICATION",createSetValueJsonObject(projectFinalClassificationNo));
						jsonObject.add("NPD_PROJECT_PROJECT_FINAL_PROJECT_CLASSIFICATION_facet",createSetValueJsonObject(projectFinalClassificationNo));
					}
					if (taskFinalClassification != null) {
						jsonObject.add("NPD_TASK_FINAL_CLASSIFICATION",createSetValueJsonObject(taskFinalClassification));
					}
					if (taskDraftClassification != null) {
						jsonObject.add("NPD_TASK_DRAFT_CLASSIFICATION", createSetValueJsonObject(taskDraftClassification));
					}

					Gson gson = new GsonBuilder().setPrettyPrinting().create();
				    String updateSchema = gson.toJson(jsonObject);

					if (isFirstJson) {
						System.out.print(updateSchema);
						isFirstJson = false;
					} else {
						System.out.print("," + updateSchema);
					}
					++totalCount;
				}

			}
//			System.out.println("TotalCount "+totalCount);
			workbook.close();
		}

	}

	private static JsonObject createSetValueJsonObject(String value) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("set", value);
		return jsonObject;
	}

}
