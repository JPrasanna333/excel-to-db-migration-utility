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
import com.npd.countryspecific.repository.ProjectClassifcationRepository;
import com.npd.countryspecific.repository.ProjectTypeRepository;
import com.npd.countryspecific.repository.RegionRepository;

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
	@Autowired
	private ProjectClassifcationRepository projectClassifcationRepository;
	@Autowired
	private ProjectTypeRepository projectTypeRepository;
	@Autowired
	private RegionRepository regionRepository;

	public void readExcel(String filePath) throws IOException {
//		List<ProjectClassification> classificationData = readExcelAndConvert(filePath);
//		projectClassifcationRepository.saveAll(classificationData);

	}

	public void readAndMigrateExcelData(String filePath) throws IOException {
		List<ProjectClassification> dataList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheetAt(0); // Assuming you want to read the first sheet

			int startColumnIndex = CellReference.convertColStringToIndex(startColumn);
			int endColumnIndex = CellReference.convertColStringToIndex(endColumn);

			for (int rowIndex = startRow - 1; rowIndex < endRow; rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				if (row != null) {
					ProjectClassification projectClassification = new ProjectClassification();
					for (int columnIndex = startColumnIndex; columnIndex <= endColumnIndex; columnIndex++) {
						Cell cellValue = row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
						if (cellValue != null) {
							System.out.println(startColumnIndex);
							System.out.println(cellValue);
							String columnName = CellReference.convertNumToColString(columnIndex);
							System.out.println(columnName);
							switch (columnName) {
							case "A":
								Region region = regionRepository.findByDisplayName(cellValue.getStringCellValue());
								System.out.println(region.getId());
								projectClassification.setRegion(region);
								break;
							case "B":
								ProjectType projectType = projectTypeRepository.findByDisplayName(cellValue.getStringCellValue());
								System.out.println(projectType.getId());
								projectClassification.setProjectType(projectType);
								break;
							case "C":
								break;
							}
						}
					}
				}
			}

			workbook.close();
		}

	}

}
