<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Options"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Option</title>
<link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>
<center>
		<%
			Options option = (Options) request
					.getAttribute("option");
			if (option == null) {
		%>

<form action="FindOption" method=post>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							Which Option would you like to update?</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Option ID<sup>*</sup></b> <br> <input
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
			if (option != null) {
		%>
			<table>
		<tr>
			<th>Option ID</th>
			<th>Question ID</th>
			<th>Option</th>
			<th>Answer</th>
			
		</tr>
		<tr>
			<td><%=option.getId()%></td>
			<td><%=option.getQuestionId()%></td>
			<td><%=option.getOption()%></td>
			<td><%=option.getAnswer()%></td>
			
		</tr>
	</table>
	<%} 
					if (option != null) {
				%>
	<br> <br>
<form action="UpdateOption" method=post>
	<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							What would you like to change the answer/option? </font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>ID</b> <br> <input
						type="text" name="id2" value='<%=option.getId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>QuestionID</b> <br> <input
						type="text" name="questionId" value='<%=option.getQuestionId()%>'
						size=40 maxlength=125 readonly> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Option<sup>*</sup></b> <br> <input
						type="text" name="option" value='<%=option.getOption()%>'
						size=40 maxlength=125> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Answer<sup>*</sup></b> <br> <input
						type="text" name="answer" value='<%=option.getAnswer()%>'
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