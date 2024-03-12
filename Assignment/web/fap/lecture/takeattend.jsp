<%-- 
    Document   : studentlist
    Created on : Feb 20, 2024, 9:24:23 AM
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
        <h2>${subjectName}</h2>
        <form action="attend" method="post">
            <input type="hidden" name="subject" value="${subjectName}">
            <input type="hidden" name="value" value="${slotValue}">
            <input type="hidden" name="day" value="${slotDay}">
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
                                    <c:when test="${student.attent eq '1'}">
                                        <input type="radio" name="attendance_${student.id}" value="0" > Absent
                                        <input type="radio" name="attendance_${student.id}" value="1" checked> Present
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="attendance_${student.id}" value="0" checked> Absent
                                        <input type="radio" name="attendance_${student.id}" value="1"> Present
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <input type="text" name="des_${student.id}" value="${student.des}">
                                <input type="hidden" name="studentID" value="${student.id}">
                                <input type="hidden" name="taken" value="1">
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="button">
                <input type="submit" value="Add Attendance">
            </div>
        </form>
    </body>
</html>
