<%-- 
    Document   : timetable
    Created on : Feb 15, 2024, 6:05:57 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thời khóa biểu</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: center;
                text-decoration: none;
            }
            th {
                background-color: lightblue;
            }
            a {
                color: blue;
            }
        </style>
    </head>
    <body>
        <a href="/Assignment/fap/lecture/success.jsp?id=${param.id}">Home</a><br><br>
        <table>
            <thead>
                <tr>
                    <th><form action="viewtimetable" method="GET">
                            Lecture: <input type="text" name="id" value="${param.id}"/><br>
                            From: <input type="date" name="from" value="${requestScope.from}" />
                            To: <input type="date" name="to" value="${requestScope.to}" />
                            <input type="submit" value="View" />
                        </form></th>
                        <c:forEach var="date" items="${dates}">
                        <th>${date} </th>
                        </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="hour" begin="1" end="6">
                    <tr>
                        <td>Slot ${hour}</td>
                        <c:forEach var="date" items="${dates}">
                            <td>
                                <c:forEach var="sess" items="${sessions}">
                                    <c:if test="${sess.slot.value eq hour and sess.date eq date}">
                                        <a href="view?subject=${sess.subject.name}&value=${sess.slot.value}&day=${sess.slot.day}&id=${param.id}">
                                            ${sess.subject.name}
                                        </a>
                                        (${sess.slot.duration})<br>
                                        ${sess.group.name}<br>
                                        ${sess.room.code}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

