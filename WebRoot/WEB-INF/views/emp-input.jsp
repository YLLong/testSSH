<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'emp-input.jsp' starting page</title>
</head>
<body>
	<h4>Employee Input Page</h4>
	
	<s:form action="emp-save" method="post">
		<s:if test="id != null">
			<s:textfield name="lastName" label="LastName" disabled="true"></s:textfield>
			<s:hidden name="id"></s:hidden>
		</s:if>
		<s:else>
			<s:textfield name="lastName" label="LastName"></s:textfield>
		</s:else>
		<s:textfield name="email" label="Email"></s:textfield>
		<s:textfield name="birth" label="Birth"></s:textfield>
		<s:select list="#request.departments" listKey="id"
			listValue="departmentName" name="department.id" label="Department"></s:select>
		<s:submit />
	</s:form>
	<script type="text/javascript" src="scripts/jquery-2.2.4.min.js"></script>
	<script type="text/javascript">
	
		$(function() {
			$(":input[name=lastName]").change(function() {
				var val = $(this).val();
				val = $.trim(val);
				var $this = $(this);
				if (val != "") {
					//把当前节点后面的所有 font 兄弟节点删除
					$this.nextAll("font").remove();
	
					var url = "emp-validateLastName";
					var args = {
						"lastName" : val,
						"time" : new Date()
					};
					$.post(url, args, function(data) {
						//表示可用
						if (data == "1") {
							$this.after("<font color='green'>LastName可用!</font>");
						}
						//不可用
						else if (data == "0") {
							$this.after("<font color='red'>LastName不可用!</font>");
						}
						//服务器错误
						else {
							alert("服务器错误!");
						}
					});
				}
				else {
					alert("lastName 不能为空");
					$(this).val("");
					$this.focus();
				}
			});
		})
	</script>
</body>
</html>
