package com.npd.countryspecific.service;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

@Service
public class ReadExcelService {

	public void readExcel(String filePath) throws IOException {
		try (FileInputStream fis = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheetAt(2);

			for (Row row : sheet) {
				for (Cell cell : row) {
					System.out.print(cell + "\t");
				}
				System.out.println();
			}

			workbook.close();
		}
	}

}
