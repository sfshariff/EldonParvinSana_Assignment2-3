<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="model.NumericalInput"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	
	<title>View Numerical Input</title> 
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
					
					<%String admin = (String) session.getAttribute("admin");
					String user = (String) session.getAttribute("user");
					if(user==null && admin==null) {
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
                <div class="col-12 font-light">Numerical Input <span class="font-semibold"> View</span></div>
            </div><!-- /row -->
            <div class="col-sm-1 hidden-sm">&nbsp;</div>
            <div class="col-sm-1 hidden-sm">&nbsp;</div>
            
    <div class = "quiz-block">
	<p>Here is the information that you retrieved from database:</p>

	<table>
		<tr>
			<th style="text-align: center; font-size: 17px; padding:10px;">NumericalInput ID</th>
			<th style="text-align: center; font-size: 17px; padding:10px;">Question ID</th>
			<th style="text-align: center; font-size: 17px; padding:10px;">Answer</th>
		</tr>
		<%
			@SuppressWarnings("unchecked")
			ArrayList<NumericalInput> NumericalInputList = (ArrayList<NumericalInput>) request
					.getAttribute("numericalInputList");
			if (NumericalInputList != null) {
				for (NumericalInput numericalInput : NumericalInputList) {
		%>
		<tr>
			<td><%=numericalInput.getId()%></td>
			<td><%=numericalInput.getQuestionId()%></td>
			<td><%=numericalInput.getAnswer()%></td>
			<td>
				<form action="FindNumericalInput" method=post>
					<button class="btn" name="id" value="<%=numericalInput.getId()%>" type="submit">Modify Numerical Input</button>
				</form>
			</td>
		</tr>
		<% } } %>
	</table>

<!--  
	<p>To return the login page, click on the Back button in your
		browser or the Return button shown below.</p>
		-->

</div>
</div>
</div>
</body>
</html>