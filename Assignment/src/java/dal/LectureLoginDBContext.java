/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Lecture;
import entity.LectureAccount;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class LectureLoginDBContext extends DBContext<Lecture> {

    PreparedStatement stm = null;
    ResultSet rs = null;
    LectureAccount acc = new LectureAccount();

    public LectureAccount checkLogin(String user, String password) throws Exception {
         String sql1 = "SELECT [user], pass, lid FROM LectureAccount WHERE [user] = ? AND [pass] = ?";
        stm = connection.prepareStatement(sql1);
        stm.setString(1, user);
        stm.setString(2, password);
        rs = stm.executeQuery();
        while (rs.next()) {
            acc.setUser(user);
            acc.setPass(password);
            acc.setId(rs.getString("lid"));
            return acc;
        }
        return null;
    }
}
