<%-- 
    Document   : success
    Created on : Feb 29, 2024, 2:13:13 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>FAP</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh; 
                margin: 0; 
            }

            a {
                margin: 10px; 
                color: blue;
            }
        </style>
    </head>
    
    <a href="/Assignment/lecture/taketimetable?id=${param.id}">Take Attend</a><br>
    <a href="/Assignment/lecture/viewtimetable?id=${param.id}">View Attend</a><br>
    <a href="/Assignment/logout">Log Out</a>
</html>
