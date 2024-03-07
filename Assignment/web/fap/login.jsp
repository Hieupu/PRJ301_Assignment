<%-- 
    Document   : login
    Created on : Feb 29, 2024, 2:11:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="login" method="POST">
            <label for="userID"> Username </label> 
            <input type="text" id="userID" name="username"><br>
            <label for="pass"> Password </label>
            <input type="password" id="pass" name="password"> <br>
            <input type ="submit" value ="Login" name ="submit"> <br>
        </form>
    </body>
</html>

