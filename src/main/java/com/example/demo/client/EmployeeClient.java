package com.example.demo.client;

import java.util.List;

import com.example.demo.helper.EmployeeHelper;
import com.example.demo.pojo.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class EmployeeClient {

	public static void main(String[] args) {
		EmployeeClient client = new EmployeeClient();
		client.execute();
	}
	
	public void execute() {
		EmployeeHelper helper = new EmployeeHelper();
		helper.initializeEmployee();
		
		System.out.println("***********************");
		System.out.println("Gratuity eligible employee List:");
		helper.getGratuityEligibleList().stream().forEach(System.out::println);
		
		System.out.println("****************************");
		System.out.println("Employees who are earning more than their managers");
		helper.getEmployeeWithHigherSalaryThanMgr().stream().forEach(System.out::println);
		
		System.out.println("**************************");
		System.out.println("Employee Heirarchy");
		//System.out.println();
		List<Employee> empList = helper.getEmployeeHeirarchy();//.stream().forEach(System.out::println);
		//Gson gson = new Gson();
		//String jsonString = gson.toJson(empList);
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules(); 
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(empList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonString);
	}

}
