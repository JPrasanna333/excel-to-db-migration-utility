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
import com.npd.countryspecific.model.RequestBodyData;

@Service
public class ProjectReindexService {

	public void generateProjectReindexScripts(String filePath, String projectItemId,RequestBodyData projectData) throws Exception {
		try (FileInputStream fis = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet requestReindexingSheet = workbook.getSheet("ProjectReindexing");
			boolean isFirstJson = true;
			for (int rowIndex = projectData.getstartValue(); rowIndex < projectData.getendValue(); rowIndex++) {
				Row row = requestReindexingSheet.getRow(rowIndex);
				String itemId = row.getCell(0) != null
						? projectItemId + String.valueOf((long) row.getCell(0).getNumericCellValue())
						: null;
//				String earlyClassificationNo = row.getCell(1) != null
//						? String.valueOf((long) row.getCell(1).getNumericCellValue())
//						: null;
				String currentFinalClassificationNo = row.getCell(2) != null
						? String.valueOf((long) row.getCell(2).getNumericCellValue())
						: null;
				String projectFinalClassificationNo = row.getCell(3) != null
						? String.valueOf((long) row.getCell(3).getNumericCellValue())
						: null;
//				
//				String regRequirement = String.valueOf((long) row.getCell(5).getNumericCellValue())
//						.equalsIgnoreCase("1") ? "true" : "false";
//				String regClassificationAvailable = String.valueOf((long) row.getCell(4).getNumericCellValue())
//						.equalsIgnoreCase("1") ? "true" : "false";
				

//				if (earlyClassificationNo != null) {
					JsonObject jsonObject = new JsonObject();
					jsonObject.addProperty("ITEM_ID", itemId);
					jsonObject.addProperty("CONTENT_TYPE", "MPM_PROJECT");
//					jsonObject.add("PR_EARLY_PROJECT_CLASSIFICATION", createSetValueJsonObject(earlyClassificationNo));
//					jsonObject.add("PR_EARLY_PROJECT_CLASSIFICATION_facet", createSetValueJsonObject(earlyClassificationNo));

					if (currentFinalClassificationNo != null) {
						jsonObject.add("NPD_PROJECT_CURRENT_FINAL_CLASSIFICATION",createSetValueJsonObject(currentFinalClassificationNo));
					}
					
					if (projectFinalClassificationNo != null) {
						jsonObject.add("NPD_PROJECT_PROJECT_FINAL_PROJECT_CLASSIFICATION",createSetValueJsonObject(projectFinalClassificationNo));
						jsonObject.add("NPD_PROJECT_PROJECT_FINAL_PROJECT_CLASSIFICATION_facet",createSetValueJsonObject(projectFinalClassificationNo));
					}
					
//					jsonObject.add("NPD_PROJECT_REGISTRATION_CLASSIFICATION",createSetValueJsonObject(regClassificationAvailable));
//					jsonObject.add("NPD_PROJECT_REGISTRATION_REQUIREMENT", createSetValueJsonObject(regRequirement));

					Gson gson = new GsonBuilder().setPrettyPrinting().create();
				    String updateSchema = gson.toJson(jsonObject);

//					System.out.println(updateSchema);
					if (isFirstJson) {
						System.out.println(updateSchema);
						isFirstJson = false;
					} else {
						System.out.println("," + updateSchema);
					}
//				}

			}
			workbook.close();
		}

	}

	private static JsonObject createSetValueJsonObject(String value) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("set", value);
		return jsonObject;
	}

}
