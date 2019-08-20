package com.uiFramework.companyName.projectName.helper.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * to get the data for your automation script.
 * Here Employee(idemployee, salary,age,name,address) is the table in person database/schema
 * you will write all the method here then use them in the testscript
 */

public class ApplicationDBQuery {
	
	public int getEmpSalary(int empId) throws NumberFormatException, SQLException {
		int salary=0;
		String dbQuery= "SELECT salary from person.Employee where idemployee="+empId;
		ResultSet result = DatabaseHelper.getResultSet(dbQuery);
		while(result.next()) {
			salary=Integer.parseInt(result.getString("Salary"));
		}
		return salary;
	}
	
	/*
	 * to hold all the data of Employee table, we make the POJO class
	 */
	public List<Employee> getAllEmployee() throws SQLException {
		String dbQuery= "SELECT * from person.Employee";
		List<Employee> data= new ArrayList<Employee>();
		ResultSet result = DatabaseHelper.getResultSet(dbQuery);
		while(result.next()) {
			Employee emp=new Employee();
			emp.setEmpID(Integer.parseInt(result.getString("idemployee")));
			emp.setSalary(Integer.parseInt(result.getString("salary")));
			emp.setAddress(result.getString("address"));
			emp.setName(result.getString("name"));
			data.add(emp);			
		}
		return data;
	}

	
	/*
	 * to test, use main method
	 */
	public static void main(String[] args) throws NumberFormatException, SQLException {
		ApplicationDBQuery applicationDBQuery = new ApplicationDBQuery();
		int salary= applicationDBQuery.getEmpSalary(2);
		
		System.out.println(salary);
		
		List<Employee> data = applicationDBQuery.getAllEmployee();
		for (Employee emp:data) {
			System.out.println("empID is: "+emp.getEmpID()+" salary is: "+emp.getSalary()+" name is: "+emp.getName()+" address is: "+emp.getAddress());
		}
	}
}
