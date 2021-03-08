<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bsr.service.NoteServiceImpl, bsr.service.NoteService, bsr.model.Note" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<%
	System.out.print("checking if user already logged in..");
	Boolean isLoggedin = (Boolean) session.getAttribute("IS_LOGGED_IN");
	if (isLoggedin == null) {
		System.out.print("user not logged in..");
		response.sendRedirect("login.jsp");
	} else {
		System.out.print("user already logged in..");
	}
	%>

	<form action="logout" method="get">
		<input style="float: right;" type="submit" value="Logout"
			style="font-size: 14px;"><br>
		<br>
	</form>

	<jsp:useBean id="user" class="bsr.model.User" scope="session"></jsp:useBean>
	<h3 style="text-align: center">
		Welcome back,
		<jsp:getProperty property="username" name="user" />!
	</h3>
	<br>
	<br>
	<%
		String username = (String) session.getAttribute("username");
		NoteService noteService = new NoteServiceImpl();
		Note note = noteService.getNote(username);
		String txt= note.getNote();
	%>

	<form action="save" method="post" style="text-align: left">
		<label for="myNote">Enter your note..</label><br>
		<br>
		<textarea id="myNote" rows="4" cols="50" name="note"><%=txt%></textarea>
		<br> <input type="submit" value="Save" style="font-size: 14px;">
	</form>

</body>
</html>