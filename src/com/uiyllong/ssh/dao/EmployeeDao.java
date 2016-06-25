package com.uiyllong.ssh.dao;

import java.util.List;

import org.hibernate.Query;

import com.uiyllong.ssh.entities.Employee;

public class EmployeeDao extends BaseDao {
	
	/**
	 * 通过 id 来查出员工信息（这种hibernate的get（）方法，后面参数必须是主键才行）
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(Integer id) {
		return getSession().get(Employee.class, id);
	}
	
	/**
	 * 根据输入的 lastName 来判断输入名
	 * @param lastName
	 * @return
	 */
	public Employee getEmployeeBylastName(String lastName) {
		String hql = "from Employee e where e.lastName = ?";
		Query query = getSession().createQuery(hql).setString(0, lastName);
		return (Employee) query.uniqueResult();
	}
	
	/**
	 * 员工添加
	 * @param employee
	 */
	public void saveOrUpdate(Employee employee) {
		getSession().saveOrUpdate(employee);
	}
	
	/*
	 * 员工删除
	 */
	public void delete(Integer id) {
		String hql = "delete from Employee e where e.id = ?";
		getSession().createQuery(hql).setInteger(0, id).executeUpdate();
	}
	
	/*
	 * 所有员工查询
	 */
	public List<Employee> getAll() {
		String hql = "from Employee e left outer join fetch e.department";
		return getSession().createQuery(hql).list();
	}

}
