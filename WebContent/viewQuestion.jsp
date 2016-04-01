<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="model.Question"%>
<%@page import="model.Quiz"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>View Question</title>
</head>

<body>
	<%
		Quiz quiz = (Quiz) request
				.getAttribute("quiz");
		if (quiz != null) {
	%>
	<form action="ReadQuestion" method=post>
					<button name="id" value="<%=quiz.getId()%>" type="submit">Read Questions</button>
	</form>	
	<%
		}
	%>

</body>
</html>