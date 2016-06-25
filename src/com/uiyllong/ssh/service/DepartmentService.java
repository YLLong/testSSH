package com.uiyllong.ssh.service;

import java.util.List;

import com.uiyllong.ssh.dao.DepartmentDao;
import com.uiyllong.ssh.entities.Department;

public class DepartmentService {

	private DepartmentDao departmentDao;
	
	/**
	 * 得到部门信息
	 * @return
	 */
	public List<Department> getAll() {
		return departmentDao.getAll();
	}
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
}
