<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Question"%>
<%@page import="model.Quiz"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	
	<title>Enter the answer</title> 
	<meta name="description" content="BlackTie.co - Free Handsome Bootstrap Themes" />	    
	<meta name="keywords" content="themes, bootstrap, free, templates, bootstrap 3, freebie,">
	<meta property="og:title" content="">

	<link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css">
	<link rel="stylesheet" href="styles/jquery.fancybox-v=2.1.5.css" type="text/css" media="screen">
    <link rel="stylesheet" href="styles/font-awesome.min.css" rel="stylesheet">
	
	<link rel="stylesheet" type="text/css" href="styles/style.css">	
	<link rel="stylesheet" type="text/css" href="styles/style1.css">
	<link href="http://fonts.googleapis.com/css?family=Titillium+Web:400,600,300,200&subset=latin,latin-ext" rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	
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
                <div class="col-12 font-light">Enter the <span class="font-semibold"> answers</span></div>
            </div><!-- /row -->
            <div class="col-sm-1 hidden-sm">&nbsp;</div>
            <div class="col-sm-1 hidden-sm">&nbsp;</div>
<%

Question question = (Question) request.getAttribute("question");
Quiz quiz = (Quiz) request.getAttribute("quiz");

if(question != null){
	out.println("<p style='font-size:18px; padding: 20px;'><b> Question: " + question.getQuestion());
}

if (question == null) {
	
%>

<b> ERROR: QUESTION HAS NOT BEEN SELECTED.  PLEASE EITHER SELECT AN EXISTING QUESTION OR CREATE A NEW QUESTION</b>

<%	

} else if (question.getTypeId() <= 3){
	
%>

<div class="form-block">
	<form action="CreateOption" method=post>
		<center>
			<table>
				<tr>
					<td valign=top colspan=2> 
						<input type="hidden" name="questionId" value="<%= question.getId()%>"> 
					</td>
					<td>
						<input type="hidden" name="quizId" value="<%= quiz.getId()%>">
					</td>
					<td>
						<input type="hidden" name="numQuestions" value="<%= quiz.getNumQuestions()%>">
					</td>
				</tr>
			</table>
		
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th colspan=2><font size=5>
						Create 4 Options</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				<tr>
					<td valign=top colspan=2><br>Option 1<sup>*</sup> <br> <input
						type="text" name="option1"
						size=40 maxlength=200> 
					</td>
				</tr>
				<tr>
					<td valign=top colspan=2>Answer 1<sup>*</sup> <br>
					<div data-role="main" class="ui-content"> 
					<fieldset data-role="controlgroup" data-type="horizontal">
					<input type="radio" name="answer1" value="True" id="t1" checked>
					<label for="t1">True</label>
  					<input type="radio" name="answer1" value="False" id="f1">
  					<label for="f1">False</label>
  					</fieldset>
  					</div>
					</td>
				</tr>
				<tr>
					<td valign=top colspan=2><br>Option 2<sup>*</sup> <br> <input
						type="text" name="option2"
						size=40 maxlength=200> 
					</td>
				</tr>
				<tr>
					<td valign=top colspan=2>Answer 2<sup>*</sup> <br>
					<div data-role="main" class="ui-content"> 
					<fieldset data-role="controlgroup" data-type="horizontal">
					<input type="radio" name="answer2" value="True" id="t2" checked>
					<label for="t2">True</label>
  					<input type="radio" name="answer2" value="False" id="f2">
  					<label for="f2">False</label>
  					</fieldset>
  					</div>
					</td>
				</tr>
				<tr>
					<td valign=top colspan=2><br>Option 3<sup>*</sup> <br> <input
						type="text" name="option3"
						size=40 maxlength=200> 
					</td>
				</tr>
				<tr>
					<td valign=top colspan=2>Answer 3<sup>*</sup> <br> 
					<div data-role="main" class="ui-content">
					<fieldset data-role="controlgroup" data-type="horizontal">
					<input type="radio" name="answer3" value="True" id="t3" checked>
					<label for="t3">True</label>
  					<input type="radio" name="answer3" value="False" id="f3">
  					<label for="f3">False</label>
  					</fieldset>
  					</div>
					</td>
				</tr>
				<tr>
					<td valign=top colspan=2><br>Option 4<sup>*</sup> <br> <input
						type="text" name="option4"
						size=40 maxlength=200> 
					</td>
				</tr>
				<tr>
					<td valign=top colspan=2>Answer 4<sup>*</sup> <br> 
					<div data-role="main" class="ui-content">
					<fieldset data-role="controlgroup" data-type="horizontal">
					<input type="radio" name="answer4" value="True" id="t4" checked>
					<label for="t4">True</label>
  					<input type="radio" name="answer4" value="False" id="f4">
  					<label for="f4">False</label>
  					</fieldset>
  					</div>
					</td>
				</tr>
				<tr>
					<td align=center colspan=2><input type="submit" value="Submit"><input type="reset" value="Reset"></td>
				</tr>
				
			</table>
		</center>
	</form>
	</div>
	
<%	

} else if (question.getTypeId() == 4){
	
%>
<div class="form-block">
	<form action="CreateNumericalInput" method=post>
		<center>
			<table>
				<tr>
					<td valign=top colspan=2> 
						<input type="hidden" name="questionId" value="<%= question.getId()%>"> 
					</td>
					<td>
						<input type="hidden" name="quizId" value="<%= quiz.getId()%>">
					</td>
					<td>
						<input type="hidden" name="numQuestions" value="<%= quiz.getNumQuestions()%>">
					</td>
				</tr>
			</table>
		
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th colspan=2><font size=5>
						NUMERICALINPUT ANSWER</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>

				<tr>
					<td valign=top colspan=2><b>Answer<sup>*</sup></b> <br> <input
						type="text" name="answer"
						size=40 maxlength=125> 
					</td>
				</tr>

				<tr>
					<td align=center colspan=2><input type="submit" value="Submit">
						<input type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>
	</form>
	</div>

<%	

} else {
	
%>
<div class="form-block">
	<form action="CreateTextInput" method=post>
		<center>
			<table>
				<tr>
					<td valign=top colspan=2> 
						<input type="hidden" name="questionId" value="<%= question.getId()%>"> 
					</td>
					<td>
						<input type="hidden" name="quizId" value="<%= quiz.getId()%>">
					</td>
					<td>
						<input type="hidden" name="numQuestions" value="<%= quiz.getNumQuestions()%>">
					</td>
				</tr>
			</table>
		
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th colspan=2><font size=5>
						TEXTINPUT ANSWER</font> <br> <font size=1><sup>*</sup> Required Fields</font></th>
				</tr>
				<tr>
					<td valign=top colspan=2><b>Answer<sup>*</sup></b> <br> <input
						type="text" name="answer"
						size=40 maxlength=125> 
					</td>
				</tr>

				<tr>
					<td align=center colspan=2><input type="submit" value="Submit">
						<input type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>
	</form>
	</div>
</div>
</div>
<%	
} 
%>

</body>
</html>