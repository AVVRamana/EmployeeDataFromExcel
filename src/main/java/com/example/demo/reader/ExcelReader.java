package com.example.demo.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.pojo.Employee;

public class ExcelReader {
	
	public List<Employee> readExcel(String fileName){
		List<Employee> employees = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        ClassPathResource resource = new ClassPathResource(fileName); // Assuming file is directly in resources
        // For files in subfolders, use: new ClassPathResource("subfolder/my_file.txt");
        //InputStream inputStream = resource.getInputStream();
        Workbook workBook = null;
        try(InputStream is = resource.getInputStream()){
        	
        	if(fileName.endsWith("xlsx")) {
        		workBook = new XSSFWorkbook(is);
        	}else {
        		workBook = new HSSFWorkbook(is);
        	}
        	
        	Sheet sheet = workBook.getSheetAt(0);
        	boolean skipHeader = true;
        	
        	for(Row row : sheet) {
        		if(skipHeader) {
        			skipHeader = false;
        			continue;
        		}
        		
        		int id = (int) getCellNumericValue(row.getCell(0));
                String name = getCellStringValue(row.getCell(1));
                String city = getCellStringValue(row.getCell(2));
                String state = getCellStringValue(row.getCell(3));
                String category = getCellStringValue(row.getCell(4));

                // Handle manager_id (can be null)
                Integer managerId = null;
                Cell managerIdCell = row.getCell(5);
                if (managerIdCell != null && managerIdCell.getCellType() == CellType.NUMERIC) {
                    managerId = (int) managerIdCell.getNumericCellValue();
                } else if (managerIdCell != null && managerIdCell.getCellType() == CellType.STRING && !managerIdCell.getStringCellValue().trim().isEmpty()) {
                    try {
                        managerId = Integer.parseInt(managerIdCell.getStringCellValue().trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid manager_id format for employee ID " + id);
                        
                    }
                }

                int salary = (int) getCellNumericValue(row.getCell(6));

                // Handle DOJ (try as date first, then as string)
                LocalDate doj = null;
                Cell dojCell = row.getCell(7);
                if (dojCell != null && dojCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dojCell)) {
                    doj = dojCell.getLocalDateTimeCellValue().toLocalDate();
                } else if (dojCell != null && dojCell.getCellType() == CellType.STRING) {
                    try {
                        doj = LocalDate.parse(dojCell.getStringCellValue().trim(), formatter);
                    } catch (Exception e) {
                        System.err.println("Invalid date format for employee ID " + id + ": " + dojCell.getStringCellValue());
                    }
                }

                employees.add(new Employee(id, name, city, state, category, managerId, salary, doj));
        	}
        	
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if( null != workBook) {
				try {
					workBook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return employees;
		
	}

	private String getCellStringValue(Cell cell) {
		if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
        if (cell.getCellType() == CellType.NUMERIC) return String.valueOf(cell.getNumericCellValue());
        return cell.toString();
	}

	private double getCellNumericValue(Cell cell) {
		if (cell == null) return 0;
        if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue();
        if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
	}

}
