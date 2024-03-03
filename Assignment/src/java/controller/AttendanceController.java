package controller;

import dal.AttendDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@WebServlet(name = "AttendanceServlet", urlPatterns = {"/attend"})
public class AttendanceController extends HttpServlet {

    AttendDBContext attendDBContext = new AttendDBContext();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nhận các giá trị từ form
        String subjectName = request.getParameter("subject");
        int slotValue = Integer.parseInt(request.getParameter("value"));
        int slotDay = Integer.parseInt(request.getParameter("day"));

        // Iterate over parameters to extract attendance and note values for each student
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("attendance_")) {
                String studentID = paramName.substring("attendance_".length());
                String attendance = request.getParameter(paramName);
                String note = request.getParameter("des_" + studentID);
                int attendanceValue = "1".equals(attendance) ? 1 : 0;
                // Gọi phương thức insertAttend từ lớp AttendDBContext
                attendDBContext.insertAttend(subjectName, slotValue, slotDay, studentID, note, attendanceValue == 1 ? Boolean.TRUE : Boolean.FALSE);
            }
        }

        response.sendRedirect("./fap/success.jsp");
    }
}
