package com.uiFramework.companyName.projectName.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;


public class DatabaseHelper {

	private static Logger log= LoggerHelper.getLogger(DatabaseHelper.class);
	
	/*
	 * person is the database in the mysql which have multiple tables
	 */
	private static String url="jdbc:mysql://localhost/person";
	private static String driverName="com.mysql.jdbc.Driver";
	private static String userName="root";
	private static String password="password";
	private static DatabaseHelper instance=null;
	
	/*
	 * connection (session) with a specific database. SQL statements are executed and results are returned within 
	 * the context of a connection. 
	 */
	private static Connection connection;
	
	/*
	 * to create single instance of connection
	 */
	private Connection getSingleConnectionInstance() {
		try {
			Class.forName(driverName);
			try {
				/*
				 * Attempts to establish a connection to the given database URL. The DriverManager attempts to select an appropriate driver from the set of registered JDBC drivers.  
				 */
				connection=DriverManager.getConnection(url, userName, password);
				if(connection!=null)
					log.info("connection is created");
			}
			catch(SQLException e) {
				log.error("failed to create database connection:"+e);
			}
		}
		catch(ClassNotFoundException e) {

			log.info("driver not found: "+e);
		}
		return connection;
		
	}
	
	private DatabaseHelper() {
		connection=getSingleConnectionInstance();
	}
	
	public static DatabaseHelper getInstance() {
		
		if(instance==null) {
			instance=new DatabaseHelper();
		}
		return instance;
	}
	
	/*
	 *get the data from the db by executing query 
	 */
	public static ResultSet getResultSet(String dbQuery)  {
		instance=DatabaseHelper.getInstance();
		connection=instance.getConnection();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(dbQuery);
			log.info("query is executed: "+dbQuery);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	private Connection getConnection() {
		// TODO Auto-generated method stub
		return connection;
	}

}
