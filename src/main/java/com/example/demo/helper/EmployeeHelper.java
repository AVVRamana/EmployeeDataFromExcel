package com.example.demo.helper;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.pojo.Employee;
import com.example.demo.reader.ExcelReader;

public class EmployeeHelper {
	
	private List<Employee> employeeList;
	
	private Map<Integer, Employee> employeeMap;

	public EmployeeHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void initializeEmployee() {
		ExcelReader reader = new ExcelReader();
		employeeList = reader.readExcel("Book1.xlsx");
		populateEmployeeMap();
	}
	
	private void populateEmployeeMap() {
		employeeMap = new HashMap<>();
		employeeList.forEach(emp -> employeeMap.put(emp.getId(), emp));
	}

	public List<Employee> getGratuityEligibleList(){
		LocalDate currentDate = LocalDate.now();
		return employeeList.stream()
                .filter(emp -> emp.getDoj() != null && 
                        Period.between(emp.getDoj(), currentDate).getYears()
                        >= 5)
                .collect(Collectors.toList());
	}
	
	public List<Employee> getEmployeeWithHigherSalaryThanMgr(){
		
		return employeeList.stream().filter( e -> e.getSalary() > employeeMap.getOrDefault(e.getManagerId(),e).getSalary()).
				collect(Collectors.toList());
	}
	
	public List<Employee> getEmployeeHeirarchy() {
		List<Employee> rootEmployees = new ArrayList<>();
		for(Employee e : employeeList) {
			if(e.getManagerId() == null) {
				rootEmployees.add(e);
			}else {
				employeeMap.get(e.getManagerId()).addSubordinates(e);
			}
		}
		
		return rootEmployees;
	}

}
