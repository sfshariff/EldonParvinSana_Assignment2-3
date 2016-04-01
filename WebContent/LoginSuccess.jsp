<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="ca.myseneca.model.User"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>SuccessPage</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>    
</head>

<body>
    <h1>You have successfully signed in</h1>
    <p>Here is the information that you entered:</p>

    <% User user = (User) request.getAttribute("user"); %>
    
    <label>Email:</label>
    <span><% out.print(user.getEmail()); %></span><br>
    <label>Password:</label>
    <span><%= user.getPassword() %></span><br>

    <p>To return the login page, click on the Back 
    button in your browser or the Return button shown 
    below.</p>

    <form action="" method="get">
        <input type="submit" value="Return">
    </form>

</body>
</html>