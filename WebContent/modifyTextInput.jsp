<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.TextInput"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Text Input</title>
<link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>
<center>
		<%
			TextInput textInput = (TextInput) request
					.getAttribute("textInput");
			if (textInput == null) {
		%>

<form action="FindTextInput" method=post>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							Which TextInput would you like to update?</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>TextInput ID<sup>*</sup></b> <br> <input
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
			if (textInput != null) {
		%>
			<table>
		<tr>
			<th>TextInput ID</th>
			<th>Question ID</th>
			<th>Answer</th>
		</tr>
		<tr>
			<td><%=textInput.getId()%></td>
			<td><%=textInput.getQuestionId()%></td>
			<td><%=textInput.getAnswer()%></td>
		</tr>
	</table>
	<%} 
					if (textInput != null) {
				%>
	<br> <br>
<form action="UpdateTextInput" method=post>
	<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							What would you like to change the answer to?</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>ID</b> <br> <input
						type="text" name="id2" value='<%=textInput.getId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>QuestionID</b> <br> <input
						type="text" name="questionId" value='<%=textInput.getQuestionId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Answer<sup>*</sup></b> <br> <input
						type="text" name="answer" value='<%=textInput.getAnswer()%>'
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