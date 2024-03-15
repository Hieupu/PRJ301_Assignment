/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Student;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class StudentDBContext extends DBContext<Student> {

//    public ArrayList<Student> list(String subjectName, int slotValue, int slotDay) throws SQLException {
//        ArrayList<Student> students = new ArrayList<>();
//        PreparedStatement stm;
//        String sql = "SELECT s.id, s.name "
//                + "FROM Student s "
//                + "JOIN [Group Student] gs ON gs.sid = s.id "
//                + "JOIN [Group] g ON g.id = gs.gid "
//                + "JOIN [Session] se ON se.gid = g.id "
//                + "JOIN Slot sl ON sl.id = se.slid "
//                + "JOIN Subject su ON su.id = g.suid "
//                + "WHERE su.name = ? AND sl.value = ? AND sl.day = ?";
//        stm = connection.prepareStatement(sql);
//        stm.setString(1, subjectName);
//        stm.setInt(2, slotValue);
//        stm.setInt(3, slotDay);
//        ResultSet rs = stm.executeQuery();
//        while (rs.next()) {
//            String id = rs.getString("id");
//            String name = rs.getString("name");
//            Student student = new Student(id, name);
//            students.add(student);
//        }
//        return students;
//  }
    public ArrayList<Student> show(String subjectName, int slotValue, int slotDay) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm;
        String sql = """
                     SELECT s.id,s.name,a.isattend,a.des FROM Student s
                     JOIN Attendance a ON a.sid = s.id
                     JOIN [Session] se ON se.id = a.seid
                     JOIN [Group] g ON g.id = se.gid
                     JOIN Slot sl ON sl.id = se.slid
                     JOIN Subject su ON su.id = g.suid
                     WHERE su.name = ? AND sl.value = ? AND sl.day = ?""";
        stm = connection.prepareStatement(sql);
        stm.setString(1, subjectName);
        stm.setInt(2, slotValue);
        stm.setInt(3, slotDay);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String isattend = rs.getString("isattend");
            String des = rs.getString("des");
            Student student = new Student(id, name, isattend, des);
            students.add(student);
        }
        return students;
    }
}
