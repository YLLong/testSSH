package com.uiyllong.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.uiyllong.ssh.entities.Employee;
import com.uiyllong.ssh.service.DepartmentService;
import com.uiyllong.ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware, ModelDriven<Employee>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeService employeeService;
	private DepartmentService departmentService;
	private Integer id;
	// ajax请求的输入流
	private InputStream inputStream;
	private Employee model;
	private String lastName;

	/**
	 * 验证输入的 lastName 是否可用
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String validateLastName() throws UnsupportedEncodingException {
		if (employeeService.lastNameIsValid(lastName)) {
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} else {
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajax-success";
	}

	/**
	 * 添加员工
	 * 
	 * @return
	 */
	public String save() {
		if (id == null) {
			model.setCreateTime(new Date());
		}
		employeeService.saveOrUpdate(model);
		return SUCCESS;
	}

	/**
	 * 对模型的支持，如果没有这个方法，则 model 模型是 null
	 */
	public void prepareSave() {
		if (id == null) {
			model = new Employee();
		} else {
			model = employeeService.get(id);
		}
	}

	/**
	 * 查询出所有部门信息，以下拉列表显示在页面上供选择
	 * 
	 * @return
	 */
	public String input() {
		request.put("departments", departmentService.getAll());
		return INPUT;
	}
	
	public void prepareInput() {
		if (id != null) {
			model = employeeService.get(id);
		}
	}

	/**
	 * ajax删除员工信息
	 * 
	 * @return
	 */
	public String delete() {
		employeeService.delete(id);
		try {
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}

	/**
	 * 查询所有员工信息
	 * 
	 * @return
	 */
	public String list() {
		request.put("employees", employeeService.getAll());
		return "list";
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public Employee getModel() {
		return model;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
