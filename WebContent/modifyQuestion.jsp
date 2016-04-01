<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Question"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Question</title>
<link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>
<center>
		<%
			Question question = (Question) request
					.getAttribute("question");
			if (question == null) {
		%>

<form action="FindQuestion" method=post>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							Which Question would you like to update?</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Question ID<sup>*</sup></b> <br> <input
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
			if (question != null) {
		%>
			<table>
		<tr>
			<th>Question ID</th>
			<th>Type ID</th>
			<th>Quiz ID</th>
			<th>Question</th>
			<th>Difficulty</th>
			<th>Hint</th>
		</tr>
		<tr>
			<td><%=question.getId()%></td>
			<td><%=question.getTypeId()%></td>
			<td><%=question.getQuizId()%></td>
			<td><%=question.getQuestion()%></td>
			<td><%=question.getHint()%></td>
		</tr>
	</table>
	<%} 
					if (question != null) {
				%>
	<br> <br>
<form action="UpdateQuestion" method=post>
	<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							What would you like to change these data?</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>ID</b> <br> <input
						type="text" name="id2" value='<%=question.getId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>TypeID</b> <br> <input
						type="text" name="typeId" value='<%=question.getTypeId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>QuizID</b> <br> <input
						type="text" name="quizId" value='<%=question.getQuizId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>difficulty<sup>*</sup></b> <br> <input
						type="text" name="difficulty" value='<%=question.getDifficulty()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>question<sup>*</sup></b> <br> <input
						type="text" name="question" value='<%=question.getQuestion()%>'
						size=40 maxlength=125> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Hint<sup>*</sup></b> <br> <input
						type="text" name="hint" value='<%=question.getHint()%>'
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