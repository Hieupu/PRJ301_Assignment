<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grade Student</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
                margin: 8px;
            }
            th, td {
                border: none;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: lightblue;
            }
            a {
                text-decoration: none;
                color: black
            }
            .c1{
                width: 200px;
            }
            .c2{
                display: flex;
            }
        </style>
    </head>
    <body>
        <a style="color: blue; text-decoration: underline" href="/Assignment/fap/lecture/success.jsp?id=${param.id}">Home</a><br><br>
        <div style="margin-left: 40%;">
            <form action="/lecture/viewsubject?id=" method="GET">
                Lecture: <input type="text" name="id" value="${param.id}" readonly=""/><br>
                From: <input type="date" name="from" value="${requestScope.from}" />
                To: <input type="date" name="to" value="${requestScope.to}" />
                <input type="submit" value="View" />
            </form>
        </div><br>
        <div class="c2">
            <table class="c1">
                <thead>
                    <tr>
                        <th>Subject</th>  
                    </tr>
                </thead>
                <tbody> 
                    <tr>
                        <c:forEach var="sub" items="${subs}">
                            <td style="display: flex; align-items: start;justify-content: center;">
                                <h4 style="color: black;">
                                    <a href="/Assignment/lecture/viewgroup?id=${param.id}&subname=${sub.name}">${sub.name}</a>
                                </h4>
                            </td>
                        </c:forEach>
                    </tr> 
                </tbody>
            </table>
            <table class="c1">
                <thead>
                    <tr>
                        <th>Group</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <c:forEach var="g" items="${group}">
                            <td style="display: flex; align-items: start;justify-content: center;">
                                <h4 style="color: black;">
                                    <a href="/Assignment/lecture/viewstudent?id=${param.id}&subname=${g.sub.name}&groupname=${g.name}">${g.name}</a>
                                </h4>
                            </td>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
            <table style="width: 440px">
                <thead>
                    <tr>
                        <th>Student ID</th>
                        <th style="background-color: white; width: 0px; padding: 0;"></th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Student Name</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${student}">
                        <tr>
                            <td>${s.id}</td>
                            <td> </td>
                            <td>${s.name}</td>
                            <td>
                                <a href="/Assignment/lecture/viewgrade?id=${param.id}&subname=${s.group.sub.name}&groupname=${s.group.name}&sid=${s.id}" style="border: 1px solid black; background-color: lightgray " onclick="toggleGrade(this)">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <table style="width: 1000px">
                <thead>
                    <tr>
                        <th>Category</th>
                        <th>Items</th>
                        <th>Weight</th>
                        <th>Grade</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="gr" items="${grade}">
                        <tr>
                            <td>${gr.category}</td>
                            <td>${gr.item}</td>
                            <td>${gr.weight}%</td>
                            <td>${gr.grade}</td>
                        </tr>
                    </c:forEach>
            </table>
        </div>
    </body>
</html>
