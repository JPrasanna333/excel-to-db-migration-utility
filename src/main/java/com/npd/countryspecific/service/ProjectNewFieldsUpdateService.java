package com.npd.countryspecific.service;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

@Service
public class ProjectNewFieldsUpdateService {

	public void generateProjectNewFieldsUpdateScripts(String filePath, String tableName) throws Exception {
		try (FileInputStream fis = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet projectNewFieldsupdateSheet = workbook.getSheet("ProjectNewFieldsUpdate(DB)");

			for (int rowIndex = 1; rowIndex <= projectNewFieldsupdateSheet.getLastRowNum(); rowIndex++) {
				Row row = projectNewFieldsupdateSheet.getRow(rowIndex);
				long projectId = (long) row.getCell(0).getNumericCellValue();
				long newRegClassificationAvailable = (long) row.getCell(1).getNumericCellValue();
				long newRegRequirement = (long) row.getCell(2).getNumericCellValue();

				String updateNewProjectFieldsQuery = "UPDATE "+tableName
						+ " SET New_Registration_Classification_Available = " + newRegClassificationAvailable 
						+ ",New_REGISTRATION_Requirement = "+ newRegRequirement
						+" WHERE Id = "+projectId;
				
				System.out.println(updateNewProjectFieldsQuery);

			}
			workbook.close();
		}

	}

}
