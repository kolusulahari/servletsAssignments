package com.cg.servlets.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cg.servlets.dto.Dto;


public class DaoImpl implements Dao {
	Dto bean = new Dto();
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	@Override
	public Dto getEmployeeDetailById(int id) {

		
		try { 
		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb?useSSL=false","user","rootpassword");
				
			stmt = conn.prepareStatement("select * from emp_details where id = ?");
			
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();

			if(rs.next()) {
			bean.setId(rs.getInt("id"));
			bean.setName(rs.getString("name"));
			bean.setAge(rs.getInt("age"));
			bean.setSalary(rs.getInt("salary"));
			bean.setDesignation(rs.getString("designation"));
			bean.setPassword(rs.getString("password"));
			
			} else {
				System.out.println("Employee details not found");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//close connection
		finally {
			try {
				if(conn != null) {
				conn.close();
				} 
				if(stmt != null) {
				stmt.close();
				}
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		
		}
		return bean;
	}

	@Override
	public boolean deleteEmployeeInfo(int id) {

		try { 
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?useSSL=false","root","root");
			
			stmt = conn.prepareStatement("delete from employee where id = ?");
			
			
			stmt.setInt(1,id);
			
			
			int status= stmt.executeUpdate();
			
			if(status != 0) {
				return true;
			
			} else {
				return false;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(conn != null) {
				conn.close();
				} 
				if(stmt != null) {
				stmt.close();
				}
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		
		}
		return false;
	}

	@Override
	public boolean updateEmployeeInfo(String name) {

		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			
 			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?useSSL=false","root","root");
			
			
			stmt = conn.prepareStatement("update employee set name = ? where id = ?");
			
			
			stmt.setString(1, bean.getName());
			stmt.setInt(2,bean.getId());
			
						
			int rowsaffected = stmt.executeUpdate();
							
			if(rowsaffected != 0) {
				return true;
			} else {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(conn != null) {
				conn.close();
				} 
				if(stmt != null) {
				stmt.close();
				}
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return false;
	}

	@Override
	public boolean createEmployeeInfo(Dto bean) {

		try { 
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//get db connection via Driver
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?useSSL=false","root","root");
			
			//issue the sql query via connection
			stmt = conn.prepareStatement("insert into employee(id,name,age,salary,designation,password) values (?,?,?,?,?,?)");
			
			//set parameters
			stmt.setInt(1,bean.getId());
			stmt.setString(2,bean.getName());
			stmt.setInt(3,bean.getAge());
			stmt.setInt(4,bean.getSalary());
			stmt.setString(5,bean.getDesignation());
			stmt.setString(6, bean.getPassword());
			
			//process the result "returned by sql queries"
			int rowsaffected= stmt.executeUpdate();
			if(rowsaffected != 0) {
				return true;
			} else {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//close connection
		finally {
			try {
				if(conn != null) {
				conn.close();
				} 
				if(stmt != null) {
				stmt.close();
				}
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return false;
	}

	@Override
	public List<Dto> getAllEmployeeDetail() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try { 
			//load the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//get db connection via Driver
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?useSSL=false","root","root");
			
			//issue the sql query via connection
			stmt = conn.createStatement();
			
			//process the result "returned by sql queries"
			rs = stmt.executeQuery("select * from employee");
			List<Dto> list = new ArrayList<Dto>();
			while(rs.next()) {
				Dto beans = new Dto();
				beans.setId(rs.getInt("id"));
				beans.setName(rs.getString("name"));
				beans.setAge(rs.getInt("age"));
				beans.setSalary(rs.getInt("salary"));
				beans.setDesignation(rs.getString("designation"));
				beans.setPassword(rs.getString("password"));
				list.add(beans);
			}
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//close connection
		finally {
			try {
				if(conn != null) {
				conn.close();
				} 
				if(stmt != null) {
				stmt.close();
				}
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return null;
	}

	@Override
	public Dto authenticate(int empId, String password) {
		Dto bean = getEmployeeDetailById(empId);
		if(!(bean !=null && bean.getPassword().equals(password))) {
			bean = null;
		}
		return null;
	}	


}
