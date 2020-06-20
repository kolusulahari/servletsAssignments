package com.cg.servlets.dao;

import java.util.List;

import com.cg.servlets.dto.Dto;

public interface Dao {
public Dto authenticate(int empId,String password);
	
	public Dto getEmployeeDetailById(int id);
	public boolean deleteEmployeeInfo(int id);
	public boolean updateEmployeeInfo(String name);
	public boolean createEmployeeInfo(Dto bean);
	public List<Dto> getAllEmployeeDetail();
}
