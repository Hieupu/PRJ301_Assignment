<%-- 
    Document   : timetable_view
    Created on : Mar 8, 2024, 1:34:58 AM
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
            h6.xanh {
                margin: 2px;
                color: green;
            }
            h6.do {
                margin: 2px;
                color : red;
            }
            h6.xam {
                margin: 2px;
                color: gray;
            }
        </style>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th><form action="view" method="GET">
                            <input type="hidden" name="id" value="${param.id}" readonly/><br>
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
                                        <a href="view?subject=${sess.subject.name}&value=${sess.slot.value}&day=${sess.slot.day}">
                                        ${sess.subject.name}
                                        </a>
                                        ${sess.slot.duration}<br>
                                        ${sess.lecture.name}<br>
                                        ${sess.group.name}<br>
                                        ${sess.room.code}<br> 
                                        <c:if test="${sess.stu.attent eq '1'}">
                                            <h6 class="xanh">Present</h6>
                                        </c:if>
                                        <c:if test="${sess.stu.attent eq '0'}">
                                            <h6 class="do">Absent</h6>
                                        </c:if>
                                        <c:if test="${not (sess.stu.attent eq '1' or sess.stu.attent eq '0')}">
                                            <h6 class="xam">Not yet</h6>
                                        </c:if>

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