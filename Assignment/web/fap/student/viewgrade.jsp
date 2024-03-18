<%-- 
    Document   : viewgrade
    Created on : Mar 16, 2024, 2:45:11 PM
    Author     : Admin
--%>

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
            .c2{
                display: flex;
            }
        </style>
    </head>
    <body>
        <a style="color: blue; text-decoration: underline" href="/Assignment/fap/student/success.jsp?id=${param.id}">Home</a><br><br>
        <div style="margin-left: 40%;">
            <form action="/Assignment/student/grade?id=" method="GET">
                Student: <input type="text" name="id" value="${param.id}" readonly=""/><br>
                From: <input type="date" name="from" value="${requestScope.from}" />
                To: <input type="date" name="to" value="${requestScope.to}" />
                <input type="submit" value="View" />
            </form>
        </div><br>
        <div class="c2">
            <table style="width: 50%">
                <thead>
                    <tr>
                        <th>Subject</th>  
                    </tr>
                </thead>
                <tbody> 
                    <tr>
                        <c:forEach var="sub" items="${subject}">
                            <td style="display: flex; align-items: start; justify-content: center;">
                                <h4 style="color: black;">
                                    <a href="/Assignment/student/viewgrade?id=${param.id}&subname=${sub.name}">${sub.name}</a>
                                </h4>
                            </td>
                        </c:forEach>
                    </tr> 
                </tbody>
            </table>
            <table>
                <thead>
                    <tr>
                        <th>Category</th>
                        <th>Items</th>
                        <th>Weight</th>
                        <th>Grade</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="gr" items="${grade}" >
                        <tr>
                            <td>${gr.category}</td>
                            <td>${gr.item}</td>
                            <td>${gr.weight}%</td>
                            <c:if test="${isgrade eq '1'}">
                            <td>${gr.grade}</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div style="margin-left: 85%">
            <c:if test="${avg != null}">
            <h3>Total: ${avg}</h3>
            </c:if>
            <c:if test="${avg != null and avg > 5 and isgrade == '1'}">
                <h3 style="color: green">PASSED</h3>
            </c:if>
            <c:if test="${avg != null and avg < 5 and isgrade == '1'}">
                <h3 style="color: red">NOT PASSED</h3>
            </c:if>
            <c:if test="${avg != null and avg < 5 and isgrade eq '0'}">
                <h3 style="color: green">STUDYING</h3>
            </c:if>
        </div>
    </body>
</html>
