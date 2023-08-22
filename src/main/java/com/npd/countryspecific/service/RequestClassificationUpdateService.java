package com.npd.countryspecific.service;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

@Service
public class RequestClassificationUpdateService {

	public void generateSqlUpdateStatements(String filePath, String tableName) throws Exception {
		long oldClassificationId;
		long newClassificationId;
		try (FileInputStream fis = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet newClassificationSheet = workbook.getSheet("NewClassification");
			Sheet oldClassificationSheet = workbook.getSheet("OldClassification");
			Map<Integer, Long> classificationToIdMap = new HashMap<>();
			// Read and store data from the first sheet as classification number to ID
			// mapping
			for (int rowIndex = 1; rowIndex <= newClassificationSheet.getLastRowNum(); rowIndex++) {
				Row row = newClassificationSheet.getRow(rowIndex);
				long id = (long) row.getCell(0).getNumericCellValue();
				String classificationNos = row.getCell(1).getStringCellValue();

				String[] classificationArray = classificationNos.split(",\\s*");
				for (int i = 0; i < classificationArray.length; i++) {
					int classificationNo = Integer.parseInt(classificationArray[i].trim());
					classificationToIdMap.put(classificationNo, id);
				}
			}

			// Read data from the second sheet and retrieve corresponding IDs based on
			// classification numbers
			for (int rowIndex = 1; rowIndex <= oldClassificationSheet.getLastRowNum(); rowIndex++) {
				Row row = oldClassificationSheet.getRow(rowIndex);
				oldClassificationId = (long) row.getCell(0).getNumericCellValue();
				int classificationNo = (int) row.getCell(1).getNumericCellValue();

				// Find the corresponding ID based on classification number
				newClassificationId = classificationToIdMap.get(classificationNo);
//				 System.out.println(classificationNo);
//				 System.out.println(oldClassificationId);
//				 System.out.println(newClassificationId);
//				 break;
				String query1 = "UPDATE "+tableName
						+ " SET R_PO_PROJECT_CLASSIFICATION_Id = " + newClassificationId + " WHERE R_PO_PROJECT_CLASSIFICATION_Id = "
						+ oldClassificationId + " AND R_PO_PROJECT_CLASSIFICATION_Id IS NOT NULL;";

				String query2 = "UPDATE "+tableName
						+ " SET R_PO_FINAL_PROJECT_CLASSIFICATION_Id = " + newClassificationId
			            + " WHERE R_PO_FINAL_PROJECT_CLASSIFICATION_Id = " + oldClassificationId+ " AND R_PO_FINAL_PROJECT_CLASSIFICATION_Id IS NOT NULL;";
				System.out.println(query1);
				System.out.println(query2);
				

			}
			workbook.close();
		}

	}

}
