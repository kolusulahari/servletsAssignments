package com.cg.sevlets.services;

import java.util.List;

import com.cg.servlets.dao.Dao;
import com.cg.servlets.dao.DaoImpl;
import com.cg.servlets.dto.Dto;

public class ServiceImpl implements Service {
	Dao dao = new DaoImpl();

	@Override
	public Dto getEmployeeDetailById(int id) {
		if(id!= 0) {
			return dao.getEmployeeDetailById(id);
		}
		return null;
	}

	@Override
	public boolean deleteEmployeeInfo(int id) {
		if(id!=0) {
			return dao.deleteEmployeeInfo(id);
		}
		return false;
	}

	@Override
	public boolean updateEmployeeInfo(String name) {
		if(name!=null) {
			return dao.updateEmployeeInfo(name);
		}
		return false;
		
	}

	@Override
	public boolean createEmployeeInfo(Dto bean) {
		if(bean!=null) {
			return dao.createEmployeeInfo(bean);
		}
		return false;
	}

	@Override
	public List<Dto> getAllEmployeeDetail() {
		return dao.getAllEmployeeDetail();
	}

	@Override
	public Dto authenticate(int empId, String password) {
		if(empId < 0 || password == null || password.trim().isEmpty()) {
			return null;
		}
		return dao.authenticate(empId,password);
	}

}
