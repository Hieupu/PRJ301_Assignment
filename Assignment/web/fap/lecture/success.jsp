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
            .b {
                padding: 5px;
                margin: 5px;
                background-color: lightcyan;
            }
        </style>
    </head>
    <body>
        <form action="/Assignment/lecture/taketimetable?id=">
            <input type="hidden" name="id" id="idValue" value="${param.id}">
            <input class="b" type="submit" value="Take Attend"/>
        </form>
        <form action="/Assignment/lecture/viewtimetable?id=">
            <input type="hidden" name="id" id="idValue" value="${param.id}">
            <input class="b" type="submit" value="Views Attend"/>
        </form>
        <form action="/Assignment/lecture/takesubject?id=">
            <input type="hidden" name="id" id="idValue" value="${param.id}">
            <input class="b" type="submit" value="Grade Student"/>
        </form>
        <form action="/Assignment/lecture/viewsubject?id=">
            <input type="hidden" name="id" id="idValue" value="${param.id}">
            <input class="b" type="submit" value="View Grade"/>
        </form>
        <form action="/Assignment/logout">
            <input class="b" type="submit" value="Log Out"/>
        </form>
    </body>
</html>
