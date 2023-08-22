package com.npd.countryspecific.service;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskClassificationUpdateService {
	
	    public void generateProjectTaskClassUpdateStatements(String filePath,String tableName) throws Exception {
			try (FileInputStream fis = new FileInputStream(filePath)) {
			Workbook workbook = WorkbookFactory.create(fis);

	        Sheet updateClassificationSheet = workbook.getSheet("ProjectTaskClassUpdate(db)");

	        Iterator<Row> rowIterator = updateClassificationSheet.iterator();
	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            int classificationValue = (int) row.getCell(0).getNumericCellValue(); 
	            String classificationRange = row.getCell(1).toString(); 
	            if(tableName.toLowerCase().contains("project")) {
	            	String currentClassificationUpdate = "UPDATE " + tableName +
	                        " SET CurrentClassification = " + classificationValue 
	                        + " WHERE CurrentClassification IN(" +classificationRange+")";
	                System.out.println(currentClassificationUpdate); // Print the update script
	                
	                String projectFinalClassificationUpdate = "UPDATE " + tableName +
	                        " SET ProjectFinalClassification = " + classificationValue 
	                        + " WHERE ProjectFinalClassification IN(" +classificationRange +")";

	                System.out.println(projectFinalClassificationUpdate); 
	            }
	            else if(tableName.toLowerCase().contains("task")) {
	            	String finalClassificationUpdate = "UPDATE " + tableName +
	                        " SET FinalClassification = " + classificationValue 
	                        + " WHERE FinalClassification IN(" +classificationRange+")";
	                System.out.println(finalClassificationUpdate); 
	                
	                String draftClassificationUpdate = "UPDATE " + tableName +
	                        " SET DraftClassification = " + classificationValue 
	                        + " WHERE DraftClassification IN(" +classificationRange +")";
	                System.out.println(draftClassificationUpdate); 


	            }
	                
	        }
	        workbook.close();

		}

	    }

	   
	}
