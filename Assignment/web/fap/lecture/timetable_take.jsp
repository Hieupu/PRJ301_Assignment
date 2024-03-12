<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            a {
                color: blue;
            }
            edit {
                color: blue;
            }
        </style>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th><form action="taketimetable" method="GET">
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
                                        ${sess.subject.name}
                                        (${sess.slot.duration})<br>
                                        ${sess.group.name}
                                        <c:if test="${sess.taken eq 'false'}">
                                            <div class="edit">
                                                <h4 style="margin: 2px;">
                                                    <a href="take?subject=${sess.subject.name}&value=${sess.slot.value}&day=${sess.slot.day}" style="color: blue;">Take</a>
                                                </h4>

                                            </div>
                                        </c:if>
                                        <c:if test="${sess.taken eq 'true'}">
                                            <div class="edit" style="display: flex; justify-content: space-around;">
                                                <h4 style="color: blue; margin: 2px"> Taken
                                                    <a style="margin-left: 40px" href="take?subject=${sess.subject.name}&value=${sess.slot.value}&day=${sess.slot.day}"> Edit
                                                    </a></h4>
                                            </div>
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
