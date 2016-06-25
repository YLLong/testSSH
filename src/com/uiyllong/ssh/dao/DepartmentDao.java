package com.uiyllong.ssh.dao;

import java.util.List;

import com.uiyllong.ssh.entities.Department;

public class DepartmentDao extends BaseDao {
	
	/**
	 * 得到所有部门信息
	 * @return
	 */
	public List<Department> getAll() {
		String hql = "from Department";
		return getSession().createQuery(hql).list();
	}

}
