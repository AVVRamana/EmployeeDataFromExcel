package com.example.demo.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {
	
	private int id;
    private String name;
    private String city;
    private String state;
    private String category;
    private Integer managerId;
    private int salary;
    private LocalDate doj;
    private List<Employee> subordinates;
    
    
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String name, String city, String state, String category, Integer managerId, int salary,
			LocalDate doj) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
		this.category = category;
		this.managerId = managerId;
		this.salary = salary;
		this.doj = doj;
	}

	public Employee(int id, String name, String city, String state, String category, Integer managerId, int salary,
			LocalDate doj, List<Employee> subordinates) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
		this.category = category;
		this.managerId = managerId;
		this.salary = salary;
		this.doj = doj;
		this.subordinates = subordinates;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Integer getManagerId() {
		return managerId;
	}


	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public LocalDate getDoj() {
		return doj;
	}


	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}


	public List<Employee> getSubordinates() {
		return subordinates;
	}


	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}
	
	public void addSubordinates(Employee sub) {
		if(subordinates == null) {
			subordinates = new ArrayList<>();
		}
		subordinates.add(sub);
	}


	@Override
	public int hashCode() {
		return Objects.hash(category, city, doj, id, managerId, name, salary, state, subordinates);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(category, other.category) && Objects.equals(city, other.city)
				&& Objects.equals(doj, other.doj) && id == other.id && Objects.equals(managerId, other.managerId)
				&& Objects.equals(name, other.name) && salary == other.salary && Objects.equals(state, other.state)
				&& Objects.equals(subordinates, other.subordinates);
	}


	@Override
	public String toString() {
		return "\"Employee\" : {\"id\":" + id + ", \"name\":\"" + name + "\", \"city\":\"" + city + "\", \"state\":\"" + state + "\", \"category\":\""
				+ category + "\", \"managerId\":" + managerId + ", \"salary\":" + salary + ", \"doj\":\"" + doj + "\", \"subordinates\":["
				+ subordinates + "]}";
	}
	
	
    
    

}
