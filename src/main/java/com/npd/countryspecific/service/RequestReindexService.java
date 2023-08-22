package com.npd.countryspecific.service;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

@Service
public class RequestReindexService {

	public void generateReindexInsertScripts(String filePath, String tableName) throws Exception {
		try (FileInputStream fis = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet requestReindexingSheet = workbook.getSheet("RequestReindexing");

			for (int rowIndex = 1; rowIndex <= requestReindexingSheet.getLastRowNum(); rowIndex++) {
				Row row = requestReindexingSheet.getRow(rowIndex);
				long requestID = (long) row.getCell(0).getNumericCellValue();

				String reidexInsertQuery = "INSERT INTO "+tableName+" (ENTITY_NAME, ENTITY_ID, REQUEST_ID, IS_PROCESSED, IS_INDEXED, OPERATION, CREATED_DATE, LAST_MODIFIED_DATE)"
						+ " VALUES('NPD_REQUEST', "+requestID+", "+requestID+", 0, 0, 'UPDATE', '2023-08-23 14:09:00.740','2023-08-23 14:09:00.740')";
				
				System.out.println(reidexInsertQuery);

			}
			workbook.close();
		}

	}

}
