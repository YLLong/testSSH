<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Employees List Page</title>
    
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">

  </head>
  
  <body>
  
  	<h4>Employees List Page</h4>
  	<s:if test="#request.employees == null || #request.employees.size() == 0">
  		没有员工信息！
  	</s:if>
  	<s:else>
  		<table border="1" cellpadding="10" cellspacing="0">
  			<tr>
				<td>ID</td>
				<td>LASTNAME</td>
				<td>EMAIL</td>
				<td>BIRTH</td>
				<td>CREATETIME</td>
				<td>DEPT</td>
				<td>DELETE</td>
				<td>EDIT</td>
			</tr>
			<s:iterator value="#request.employees">
				<tr>
					<td>${id }</td>
					<td>${lastName }</td>
					<td>${email }</td>
					<td>
						<s:date name="birth" format="yyyy-MM-dd"/>
					</td>
					<td>
						<s:date name="createTime"/>
					</td>
					<td>${department.departmentName }</td>
					<td>
						<a href="emp-delete?id=${id}" class="delete">Delete</a>
						<input type="hidden" value="${lastName }"/>
					</td>
					<td>
						<a href="emp-input?id=${id}">Edit</a>
					</td>
				</tr>
			</s:iterator>
  		</table>
  	</s:else>
  
  <script type="text/javascript" src="scripts/jquery-2.2.4.min.js"></script>
  
  <script type="text/javascript">
  
  	/* ajax 删除表单信息 */
  	$(function(){
  		/* 1. 点击 delete 时, 弹出 确定是要删除 xx 的信息吗 ? 若确定, 执行删除, 若不确定, 则取消  */
  		$('.delete').click(function() {
  			var lastName = $(this).next(":hidden").val();
  			var flag = confirm("你确定要删除" + lastName + "的信息吗？");
  			if(flag) {
  				var $tr = $(this).parent().parent();
  				/* 用 ajax的方式删除 */
  				var url = this.href;
  				var args = {"time": new Date()};
  				$.post(url, args, function(data) {
  					/* 若 data 的返回值为 1, 则提示 删除成功, 且把当前行删除  */
  					if(data == "1") {
  						alert("删除成功！");
  						$tr.remove();
  					} else {
  						/* 若不是1，则提示失败 */
  						alert("删除失败！");
  					}
  				});
  			}
  			/* 取消默认的点击事件 */
  			return false;
  		});
  	})
  	
  </script>
  
  </body>
</html>
