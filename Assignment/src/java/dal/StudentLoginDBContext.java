/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Student;
import entity.StudentAccount;
import java.sql.*;

/**
 * s
 *
 * @author Admin
 */
public class StudentLoginDBContext extends DBContext<Student> {

    PreparedStatement stm = null;
    ResultSet rs = null;
    StudentAccount acc = new StudentAccount();

    public StudentAccount checkLogin(String user, String password) throws Exception {
        String sql1 = "SELECT [user], pass, sid FROM StudentAccount WHERE [user] = ? AND [pass] = ?";
        stm = connection.prepareStatement(sql1);
        stm.setString(1, user);
        stm.setString(2, password);
        rs = stm.executeQuery();
        while (rs.next()) {
            acc.setUser(user);
            acc.setPass(password);
            acc.setId(rs.getString("sid"));
            return acc;
        }
        return null;
    }

}
