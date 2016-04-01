<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Quiz"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Quiz</title>
<link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>
<center>
		<%
			Quiz quiz = (Quiz) request
					.getAttribute("quiz");
			if (quiz == null) {
		%>

<form action="FindQuiz" method=post>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							Which Quiz would you like to update?</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Quiz ID<sup>*</sup></b> <br> <input
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
			if (quiz != null) {
		%>
			<table>
		<tr>
			<th>Quiz ID</th>
			<th>QuizName</th>
			<th>QuizDesc</th>
		</tr>
		<tr>
			<td><%=quiz.getId()%></td>
			<td><%=quiz.getQuizName()%></td>
			<td><%=quiz.getQuizDesc()%></td>
		</tr>
	</table>
	<%} 
					if (quiz != null) {
				%>
	<br> <br>
<form action="UpdateQuiz" method=post>
	<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							What would you like to change the quizDesc to?</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>ID</b> <br> <input
						type="text" name="id2" value='<%=quiz.getId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>QuizName</b> <br> <input
						type="text" name="quizName" value='<%=quiz.getQuizName()%>'
						size=40 maxlength=125> 
					</td>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>QuizDesc<sup>*</sup></b> <br> <input
						type="text" name="quizDesc" value='<%=quiz.getQuizDesc()%>'
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