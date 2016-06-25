package com.uiyllong.ssh.service;

import java.util.List;

import com.uiyllong.ssh.dao.EmployeeDao;
import com.uiyllong.ssh.entities.Employee;

public class EmployeeService {

	private EmployeeDao employeeDao;
	
	/**
	 * 通过 id 来得到修改的回显信息
	 * @param id
	 * @return
	 */
	public Employee get(Integer id) {
		return employeeDao.getEmployeeById(id);
	}
	
	/**
	 * 输入 lastName 是否可用服务
	 * @param lastName
	 * @return
	 */
	public boolean lastNameIsValid(String lastName) {
		return employeeDao.getEmployeeBylastName(lastName) == null;
	}
	
	/**
	 * 添加员工信息服务
	 * @param employee
	 */
	public void saveOrUpdate(Employee employee) {
		employeeDao.saveOrUpdate(employee);
	}
	
	/**
	 * 删除服务
	 * @param id
	 */
	public void delete(Integer id) {
		employeeDao.delete(id);
	}
	
	/**
	 * 显示所有员工信息服务
	 * @return
	 */
	public List<Employee> getAll() {
		List<Employee> employees = employeeDao.getAll();
//		employees.clear();
		return employees;
	}
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

}
