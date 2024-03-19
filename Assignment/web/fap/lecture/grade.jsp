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
            Lecture: <input type="text" name="id" value="${param.id}" readonly/><br>
            From: <input type="date" name="from" value="${requestScope.from}" />
            To: <input type="date" name="to" value="${requestScope.to}" />
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
                                    <a href="/Assignment/lecture/takegroup?id=${param.id}&subname=${sub.name}">${sub.name}</a>
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
                                    <a href="/Assignment/lecture/takestudent?id=${param.id}&subname=${g.sub.name}&groupname=${g.name}">${g.name}</a>
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
                        <c:if test="${s.isgrade eq '0'}">
                            <tr>
                                <td>${s.id}</td>
                                <td> </td>
                                <td>${s.name}</td>
                                <td>
                                    <a href="/Assignment/lecture/gradelist?id=${param.id}&subname=${s.group.sub.name}&groupname=${s.group.name}&sid=${s.id}" style="border: 1px solid black; background-color: lightgray " onclick="toggleGrade(this)">Grade</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${s.isgrade eq '1'}">
                            <tr>
                                <td style="background-color: lightgray;">${s.id}</td>
                                <td> </td>
                                <td style="background-color: lightgray;">${s.name}</td>
                                <td style="background-color: lightgray;"> </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
            <form action="/Assignment/lecture/grade" method="post">
                <table style="width: 900px">
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
                                <td><input type="text" name="${gr.category}_${gr.item}_${gr.weight}" /></td>
                        <input type="hidden" name="subname" value="${gr.stu.group.sub.name}" />
                        <input type="hidden" name="groupname" value="${gr.stu.group.name}" />
                        <input type="hidden" name="sid" value="${gr.stu.id}" />
                        <input type="hidden" name="isgrade" value="1" />
                        <input type="hidden" name="id" value="${param.id}" />
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${grade != null}">
                    <input style="margin-left: 40% " type="submit" value="Submit">
                </c:if>
            </form>
        </div>
    </body>
</html>
