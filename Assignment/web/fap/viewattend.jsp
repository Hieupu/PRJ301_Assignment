<%-- 
    Document   : showlist
    Created on : Feb 22, 2024, 7:55:41 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Danh sách Sinh viên</title>
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
            .button {
                display: flex;
                justify-content: center;
            }
        </style>
    </head>
    <body>
        <%String subjectName = (String)request.getAttribute("subjectName");%>
        <h2>${subjectName}</h2>
        <form action="attendanceController" method="post">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên Sinh viên</th>
                        <th>Điểm danh</th>
                        <th>Note</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.id}</td>
                            <td>${student.name}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${student.attent}">
                                        Present
                                    </c:when>
                                    <c:otherwise>
                                        Absent
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>
