<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Result"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Result</title>
<link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>
<center>
		<%
			Result result = (Result) request
					.getAttribute("result");
			if (result == null) {
		%>

<form action="FindResult" method=post>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							Which Result would you like to update?</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Result ID<sup>*</sup></b> <br> <input
						type="text" name="id"
						size=40 maxlength=125> 
					</td>
				</tr>

				<tr bgcolor="#c8d8f8">
					<td align=center colspan=2><input type="submit" value="Submit">
						<input type="reset" value="Reset"></td>
				</tr>
			</table>
</form>
<br> <br>
<%} 
			if (result != null) {
		%>
			<table>
		<tr>
			<th>Result ID</th>
			<th>User ID</th>
			<th>Quiz ID</th>
			<th>Grade</th>
			<th>hours</th>
			<th>Minutes</th>
			<th>seconds</th>
		</tr>
		<tr>
			<td><%=result.getId()%></td>
			<td><%=result.getUserId()%></td>
			<td><%=result.getQuizId()%></td>
			<td><%=result.getGrade()%></td>
			<td><%=result.getHrs()%></td>
            <td><%=result.getMins()%></td>
            <td><%=result.getSecs()%></td>
	</table>
	<%} 
					if (result != null) {
				%>
	<br> <br>
<form action="UpdateResult" method=post>
	<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							What would you like to change the Grade to?</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>ID</b> <br> <input
						type="text" name="id2" value='<%=result.getId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>UserID</b> <br> <input
						type="text" name="userId" value='<%=result.getUserId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>QuizID</b> <br> <input
						type="text" name="quizId" value='<%=result.getQuizId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Grade</b> <br> <input
						type="text" name="grade" value='<%=result.getGrade()%>'
						size=40 maxlength=125> 
					</td>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Hours</b> <br> <input
						type="text" name="id2" value='<%=result.getHrs()%>'
						size=40 maxlength=125> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Mins</b> <br> <input
						type="text" name="userId" value='<%=result.getMins()%>'
						size=40 maxlength=125> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Seconds</b> <br> <input
						type="text" name="userId" value='<%=result.getMins()%>'
						size=40 maxlength=125> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td align=center colspan=2><input type="submit" value="Modify">
						<input type="reset" value="Reset"></td>
				</tr>
				
			</table>
</form>
<%} %>
</center>
</body>
</html>