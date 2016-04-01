<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="model.Question" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Question</title>
</head>
<body>
<form action="CreateQuestion" method=post>
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th bgcolor="#CCCCFF" colspan=2><font size=5>
							Question</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>

				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>TypeId<sup>*</sup></b> <br> <input
						type="text" name="typeId" 
						size=40 maxlength=125> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>QuizId<sup>*</sup></b> <br> <input
						type="text" name="quizId" 
						size=40 maxlength=125> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Question<sup>*</sup></b> <br> <input
						type="text" name="question"
						size=40 maxlength=125> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Difficulty<sup>*</sup></b> <br> <input
						type="text" name="difficulty"
						size=40 maxlength=125> 
					</td>
				</tr>
                  <tr bgcolor="#c8d8f8">
					<td valign=top colspan=2><b>Hint<sup>*</sup></b> <br> <input
						type="text" name="hint"
						size=40 maxlength=125> 
					</td>
				</tr>
				<tr bgcolor="#c8d8f8">
					<td align=center colspan=2><input type="submit" value="Submit">
						<input type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>