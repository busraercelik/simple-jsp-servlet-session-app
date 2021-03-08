<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
<body>

	<% 
		// if the user already logged in, login.jsp page should redirect to welcome.jsp 
		Boolean isLoggedin = (Boolean) session.getAttribute("IS_LOGGED_IN"); 
		System.out.println("I logged in before : "+isLoggedin);
		
		if (isLoggedin != null && isLoggedin == true) {
			response.sendRedirect("welcome.jsp");
		}
	%>
	
	<form action="login" method="post" style="display: table; text-align: center;">
	<p style="display: table-row;">
		<label for="name" style="display: table-cell;">User name:</label> 
		<input id="name" style="display: table-cell;" type="text" name="username" />
	</p>
	<p style="display: table-row;">	
		<label for="pass" style="display: table-cell;">Password: </label>
		<input id="pass" style="display: table-cell;" type="password" name="password" />
	</p><br>	
		<input type="submit" value="login" style="display: inline-block;"/> 	
	</form>
</body>
</html>