# EmployeeDataFromExcel

The Application is developed as a standalone java application.
Any one can import this as a maven project and once downloaded maven install will install all the required dependencies
Run the application as a SpringBoot application and it will print the output in console.

## Problem statement

•	Input: An Excel file containing employee data with columns such as Employee ID, Name, Department, Date of Joining, and Salary.
The input file is extended with the help of GenAI and additional rows were attached. 
The file is under resources/Book1.xlsx.

Solve below problems
----------------------------
1.	Use the above table and create similar records [approximately:50 employees] and write the same into a excel file.
    -- using "Grok" generated some more data and updated the excel sheet with 50 rows.
2.	Use a library to fetch the data from excel file and do the following tasks.
    -- used Apache POI library to read the excel file. The class com.example.demo.reade.ExcelReader is implemented to read the excel file.
3.	Identify who all employees are eligible for gratuity [assuming gratuity eligibility is for employee who served more than 60 months] based on DOJ column
    -- EmployeeHelper.getGratuityEligibleList() is implemented to to find all the employees who are eligible for gratuity.
4.	Write a method to calculate the employee whose salary is greater than his manager's salary
    -- EmployeeHelper.getEmployeeWithHigherSalaryThanMgr() is implemented to find the employee list whose salary is greater than their manager's salary.
5.	Build employee hierarchy tree (Org. structure)based on manager_id [output should be written into a JSON file] 
{
	"id": 789,
"name": "Rama",
	"role": "Director"
	"reportees": [
	{
		"id": 456,
"name": "Shivam",
		"role": "Manager",
		"reportees": [
{
			"id": 123,
"name": "Ravi",
	"role": "Employee"
}
]
	}
	]
}
 -- EmployeeHelper.getEmployeeHeirarchy() will return a json string of employee heirarchy.
7.	Write an SQL to return the row of an employee whose salary is nth highest in descending order

   WITH RankedEmployees AS (
    SELECT id, name, city, state, category, manager_id, salary, doj,
           ROW_NUMBER() OVER (ORDER BY salary DESC) AS rn
    FROM employee
)
SELECT id, name, city, state, category, manager_id, salary, doj
FROM RankedEmployees
WHERE rn = n;

here we can replace n with the nth highest number.



