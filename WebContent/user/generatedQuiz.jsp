<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Quiz"%>
<%@page import="model.Question"%>
<%@page import="model.Options"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	
	<title>Take a quiz</title> 
	<meta name="description" content="BlackTie.co - Free Handsome Bootstrap Themes" />	    
	<meta name="keywords" content="themes, bootstrap, free, templates, bootstrap 3, freebie,">
	<meta property="og:title" content="">

	<link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css">
	<link rel="stylesheet" href="styles/jquery.fancybox-v=2.1.5.css" type="text/css" media="screen">
    <link rel="stylesheet" href="styles/font-awesome.min.css" rel="stylesheet">
	
	<link rel="stylesheet" type="text/css" href="styles/style.css">	
	<link rel="stylesheet" type="text/css" href="styles/style1.css">
	<link href="http://fonts.googleapis.com/css?family=Titillium+Web:400,600,300,200&subset=latin,latin-ext" rel='stylesheet' type='text/css'>
	
	
	<link rel="prefetch" href="images/zoom.png">


</head>
<body>

<div class="navbar navbar-fixed-top" data-activeslide="1">
		<div class="container">
		
			<!-- .navbar-toggle is used as the toggle for collapsed navbar content -->
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			
			
			<div class="nav-collapse collapse navbar-responsive-collapse">
				<ul class="nav row">
					<li data-slide="1" class="col-12 col-sm-2"><a id="menu-link-1" href="index.jsp" title="Next Section"><span class="icon icon-home"></span> <span class="text">HOME</span></a></li>
					<li data-slide="2" class="col-12 col-sm-2"><a id="menu-link-2" href="index.jsp#slide-2" title="Next Section"><span class="icon icon-user"></span> <span class="text">ABOUT US</span></a></li>
					<li data-slide="3" class="col-12 col-sm-2"><a id="menu-link-3" href="index.jsp#slide-3" title="Next Section"><span class="icon icon-briefcase"></span> <span class="text">CREATE QUIZ</span></a></li>
					<li data-slide="4" class="col-12 col-sm-2"><a id="menu-link-4" href="index.jsp#slide-4" title="Next Section"><span class="icon icon-gears"></span> <span class="text">TAKE A QUIZ</span></a></li>
					
					<li class="col-12 col-sm-2"><a  href="admin/readQuiz.jsp" title="Next Section"><span class="icon icon-gears"></span> <span class="text">VIEW QUIZZES</span></a></li>
					
					<li class="col-12 col-sm-2"><a href="addUser.jsp" title="Next Section"><span class="icon icon-heart"></span> <span class="text">REGISTER</span></a></li>
					
					<%String user = (String) session.getAttribute("admin");
					if(user==null) {
					%>
					<li class="col-12 col-sm-2"><a href="login.jsp"><span class="icon icon-envelope"></span> <span class="text">LOGIN</span></a></li>
					<%}
					else {
					%>
					<li class="col-12 col-sm-2"><a href="${pageContext.request.contextPath}/logout"><span class="icon icon-envelope"></span> <span class="text">LOGOUT</span></a></li>
				<%  } %>
				</ul>
				<div class="row">
					<div class="col-sm-2 active-menu"></div>
				</div>
			</div><!-- /.nav-collapse -->
		</div><!-- /.container -->
	</div><!-- /.navbar -->
<div class="slide story" id="slide-1" data-slide="1">
		<div class="container">
			<div class="row title-row">
                <div class="col-12 font-light">Good <span class="font-semibold"> Luck</span></div>
            </div><!-- /row -->
            <div class="col-sm-1 hidden-sm">&nbsp;</div>
            <div class="col-sm-1 hidden-sm">&nbsp;</div>

<%

Quiz quiz = (Quiz) request.getAttribute("quiz");
ArrayList<Question> questionList = (ArrayList<Question>) request.getAttribute("questionList");
ArrayList<Options> optionList = (ArrayList<Options>) request.getAttribute("optionList");


if (quiz != null && questionList != null  && optionList != null) {
	
%>
<div class = "quiz-block">
	<div class = "quiztable">
<form action="GetMarked" method=post>
<table>
		<tr>
			<td> # </td>
			<td> Question</td>
			<td> Difficulty</td>
			<td> Hint</td>
			<td> Type </td>
		</tr>
		<% for (int i = 0; i < questionList.size(); i++) {%> 
		
		<tr>
			<td><%= i + 1%></td>
			<td> <%=questionList.get(i).getQuestion() %></td>
			<td>
			<%
				if(i<=2) {
					%>
					Easy
					<%
				} else if (i <= 4) {
					%>
					Medium
					<%
				} else {
					%>
					Difficult
					<%
				}
			%>
			</td>
			<td> <%=questionList.get(i).getHint() %> </td>
			<td> <%=questionList.get(i).getTypeId() %> </td>
		</tr>
		<tr> 
		<td>
			<input type="hidden" name="questionId<%=i%>" value="<%= questionList.get(i).getId()%>">
			<input type="hidden" name="typeId<%=i%>" value="<%= questionList.get(i).getTypeId()%>">
		</td>
		<td>
		<%
			if (questionList.get(i).getTypeId() == 1){
				%>
				<input type="checkbox" name="answer<%=i%>" value="<%=optionList.get(i*4).getOption() %>"> <%=optionList.get(i*4).getOption() %>
				<input type="checkbox" name="answer<%=i%>" value="<%=optionList.get(i*4 + 1).getOption() %>"> <%=optionList.get(i*4 + 1).getOption() %>
				<input type="checkbox" name="answer<%=i%>" value="<%=optionList.get(i*4 + 2).getOption() %>"> <%=optionList.get(i*4 + 2).getOption() %>
				<input type="checkbox" name="answer<%=i%>" value="<%=optionList.get(i*4 + 3).getOption() %>"> <%=optionList.get(i*4 + 3).getOption() %>
				<%
			} else if (questionList.get(i).getTypeId() == 2){
				%>
				<select name="answer<%=i%>">
				  <option value="<%=optionList.get(i*4).getOption() %>"><%=optionList.get(i*4).getOption() %></option>
				  <option value="<%=optionList.get(i*4 + 1).getOption() %>"><%=optionList.get(i*4 + 1).getOption() %></option>
				  <option value="<%=optionList.get(i*4 + 2).getOption() %>"><%=optionList.get(i*4 + 2).getOption() %></option>
				  <option value="<%=optionList.get(i*4 + 3).getOption() %>"><%=optionList.get(i*4 + 3).getOption() %></option>
				</select>
				<%
			} else if (questionList.get(i).getTypeId() == 3){
				%>
				<input type="radio" name="answer<%=i%>" value="<%=optionList.get(i*4).getOption() %>" checked> <%=optionList.get(i*4).getOption() %>
				<input type="radio" name="answer<%=i%>" value="<%=optionList.get(i*4 + 1).getOption() %>" > <%=optionList.get(i*4 + 1).getOption() %>
				<input type="radio" name="answer<%=i%>" value="<%=optionList.get(i*4 + 2).getOption() %>" > <%=optionList.get(i*4 + 2).getOption() %>
				<input type="radio" name="answer<%=i%>" value="<%=optionList.get(i*4 + 3).getOption() %>" >	<%=optionList.get(i*4 + 3).getOption() %>
				<% 
			} else {
				%>
				<input type="text" name="answer<%=i%>">
				<%
			}
		}
		%>
		</td></tr>
		<tr>
			<td>
				<input type="submit" value="Submit" id="submit">
				<input class="buttons" type="reset" value="Reset" id="reset">
			</td>
		</tr>		
</table>
</form>
</div>
</div>
</div>
</div>

<%
}
%>
</body>
</html>