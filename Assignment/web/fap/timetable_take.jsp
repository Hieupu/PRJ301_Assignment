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
            }
            th {
                background-color: lightblue;
            }
        </style>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>Time/Day</th>
                        <c:forEach var="day" begin="2" end="7">
                        <th>Thứ ${day}</th>
                        </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="hour" begin="1" end="6">
                    <tr>
                        <td>Slot ${hour}</td>
                        <c:forEach var="day" begin="2" end="7">
                            <td>
                                <%-- Lặp qua danh sách phiên để tìm các phiên có sl.day và sl.value tương ứng --%>
                                <%-- Sau đó tổng hợp thông tin của các phiên và hiển thị trong ô --%>
                                <c:forEach var="session" items="${session}">
                                    <c:if test="${session.slot.value == hour && session.slot.day == day}">
                                        <a href="take?subject=${session.subject.name}&value=${session.slot.value}&day=${session.slot.day}">
                                            ${session.subject.name}</a>
                                        ${session.slot.duration}<br>
                                        Lecture: ${session.lecture.name}<br>
                                        Group: ${session.group.name}<br>
                                        Room: ${session.room.code}
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

