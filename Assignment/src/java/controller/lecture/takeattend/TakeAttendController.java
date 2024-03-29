package controller.lecture.takeattend;

import dal.StudentDBContext;
import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import controller.auth.BaseRequiredAuthenticationController;
import entity.Account;

public class TakeAttendController extends BaseRequiredAuthenticationController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Kiểm tra xem request có chứa các tham số cần thiết không
            String subjectName = request.getParameter("subject");
            String valueParam = request.getParameter("value");
            String dayParam = request.getParameter("day");

            // Nếu một trong các tham số cần thiết bị thiếu, hiển thị thông báo lỗi
            if (subjectName == null || valueParam == null || dayParam == null) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<h2>Xảy ra lỗi khi xử lý yêu cầu:</h2>");
                out.println("<p>Thiếu tham số cần thiết từ form.</p>");
                return;
            }

            // Chuyển đổi giá trị value và day sang số nguyên
            int slotValue = Integer.parseInt(valueParam);
            int slotDay = Integer.parseInt(dayParam);

            StudentDBContext stuDB = new StudentDBContext();
            ArrayList<Student> students = stuDB.show(subjectName, slotValue, slotDay);

            request.setAttribute("students", students);
            request.setAttribute("subjectName", subjectName);
            request.setAttribute("slotValue", slotValue);
            request.setAttribute("slotDay", slotDay);

            request.getRequestDispatcher("../fap/lecture/takeattend.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException | SQLException ex) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Xảy ra lỗi khi xử lý yêu cầu:</h2>");
            out.println("<p>" + ex.getMessage() + "</p>");
            ex.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
