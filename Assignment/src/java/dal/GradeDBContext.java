/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Grade;
import entity.Group;
import entity.Student;
import entity.Subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class GradeDBContext extends DBContext<Grade> {

    public ArrayList<Subject> listSublect(String lid, Date from, Date to) {
        ArrayList<Subject> list = new ArrayList<>();

        try {
            PreparedStatement stm;
            String sql = """
                    SELECT su.name FROM Lecture l
                    JOIN [Group] g ON g.lid = l.id
                    JOIN Subject su ON su.id = g.suid
                    WHERE l.id=? AND su.[from] <=? AND su.[to] >=?""";

            stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject sub = new Subject();
                sub.setName(rs.getString("name"));
                list.add(sub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Group> listGroup(String lid, String subname) {
        ArrayList<Group> list = new ArrayList<>();
        try {
            PreparedStatement stm2;
            String sql2 = "SELECT g.name FROM Lecture l "
                    + "JOIN [Group] g ON g.lid = l.id "
                    + "JOIN Subject su ON su.id = g.suid "
                    + "WHERE l.id = ? AND su.name = ?";
            stm2 = connection.prepareStatement(sql2);
            stm2.setString(1, lid);
            stm2.setString(2, subname);
            ResultSet rs = stm2.executeQuery();
            while (rs.next()) {
                Subject sub = new Subject();
                sub.setName(subname);
                Group g = new Group();
                g.setName(rs.getString("name"));
                g.setSub(sub);
                list.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Student> listStudent(String subname, String groupname) {
        ArrayList<Student> list = new ArrayList<>();
        try {
            PreparedStatement stm2;
            String sql2 = """
                             SELECT s.id, s.name, gs.isgrade  FROM [Group] g
                             JOIN Subject su ON su.id = g.suid
                             JOIN [Group Student] gs ON gs.gid = g.id
                             JOIN Student s ON gs.sid = s.id
                             WHERE su.name = ? AND g.name = ?""";
            stm2 = connection.prepareStatement(sql2);
            stm2.setString(1, subname);
            stm2.setString(2, groupname);
            ResultSet rs = stm2.executeQuery();
            while (rs.next()) {
                Subject sub = new Subject();
                sub.setName(subname);

                Group g = new Group();
                g.setSub(sub);
                g.setName(groupname);

                Student s = new Student();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setGroup(g);
                s.setIsgrade(rs.getShort("isgrade"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Grade> listGrade(String subname, String sid, String groupname) {
        ArrayList<Grade> list = new ArrayList<>();
        try {
            PreparedStatement stm;
            String sql = """
                                 SELECT  g.category, g.[grade item], g.weight, g.grade FROM Grade g
                                 JOIN Student s ON s.id = g.sid
                                 JOIN Subject su ON su.id = g.suid
                                 JOIN [Group Student] gs ON s.id = gs.sid
                                 JOIN [Group] gr ON gr.id = gs.gid
                                 WHERE su.name = ? AND gr.name = ?  AND s.id = ?""";
            stm = connection.prepareStatement(sql);
            stm.setString(1, subname);
            stm.setString(3, sid);
            stm.setString(2, groupname);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject sub = new Subject();
                sub.setName(subname);

                Group gr = new Group();
                gr.setName(groupname);
                gr.setSub(sub);

                Student s = new Student();
                s.setId(sid);
                s.setGroup(gr);

                Grade g = new Grade();
                g.setCategory(rs.getString("category"));
                g.setItem(rs.getString("grade item"));
                g.setWeight(rs.getInt("weight"));
                g.setGrade(rs.getString("grade"));
                g.setStu(s);
                list.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void gradeStudent(String grade, String category, String item, String sid, String subname) throws SQLException {
        PreparedStatement stm1;
        String sql1 = """
                     SELECT s.id FROM [Subject] s WHERE s.name = ?""";
        stm1 = connection.prepareStatement(sql1);
        stm1.setString(1, subname);
        ResultSet rs = stm1.executeQuery();
        while (rs.next()) {
            String subID = rs.getString("id");
            PreparedStatement stm;
            String sql = """
                     UPDATE [dbo].[Grade] 
                     SET [grade] = ?
                     
                      WHERE category = ? AND [grade item] = ? AND sid = ? AND suid = ?""";
            stm = connection.prepareStatement(sql);
            stm.setString(1, grade);
            stm.setString(2, category);
            stm.setString(3, item);
            stm.setString(4, sid);
            stm.setString(5, subID);
            stm.executeUpdate();
        }
    }

    public void graded(String istaken, String sid, String groupname) throws SQLException {
        PreparedStatement stm1;
        String sql1 = """
                     SELECT g.id FROM [Group] g WHERE g.name = ?""";
        stm1 = connection.prepareStatement(sql1);
        stm1.setString(1, groupname);
        ResultSet rs = stm1.executeQuery();
        while (rs.next()) {
            String groupID = rs.getString("id");
            PreparedStatement stm;
            String sql = """
                     UPDATE [dbo].[Group Student] 
                     SET [isgrade] = ?
                     
                      WHERE sid = ? AND [gid] = ?""";
            stm = connection.prepareStatement(sql);
            stm.setString(1, istaken);
            stm.setString(2, sid);
            stm.setString(3, groupID);
            stm.executeUpdate();
        }
    }

    public ArrayList<Subject> studentSubject(String sid, Date from, Date to) {
        ArrayList<Subject> list = new ArrayList<>();

        try {
            PreparedStatement stm;
            String sql = """
                    SELECT su.name FROM Student s
                    JOIN [Group Student] gs ON gs.sid = s.id
                    JOIN [Group] g ON gs.gid = g.id
                    JOIN Subject su ON su.id = g.suid
                    WHERE s.id=? AND su.[from] <=? AND su.[to] >=?""";

            stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject sub = new Subject();
                sub.setName(rs.getString("name"));
                list.add(sub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Grade> viewgrade(String sid, String subname) throws SQLException {
        ArrayList<Grade> viewgrade = new ArrayList<>();
        PreparedStatement stm1;
        String sql1 = """
                     SELECT s.id FROM [Subject] s WHERE s.name = ?""";
        stm1 = connection.prepareStatement(sql1);
        stm1.setString(1, subname);
        ResultSet rs = stm1.executeQuery();
        while (rs.next()) {
            String subID = rs.getString("id");
            PreparedStatement stm;
            String sql = """
                     SELECT category,grade,[grade item],weight FROM [dbo].[Grade]                   
                      WHERE sid = ? AND suid = ?""";
            stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setString(2, subID);
            ResultSet rs1 = stm.executeQuery();
            while (rs1.next()) {
                Grade g = new Grade();
                g.setCategory(rs1.getString("category"));
                g.setItem(rs1.getString("grade item"));
                g.setWeight(rs1.getInt("weight"));
                String grade = rs1.getString("grade");
                if (grade == null) {
                    g.setGrade("0");
                } else {
                    g.setGrade(grade);
                }
                viewgrade.add(g);
            }
        }
        return viewgrade;
    }

    public int checkIsgrade(String sid, String subname, Date from, Date to) throws SQLException {
        int isgrade = 0;
        PreparedStatement stm1;
        String sql1 = """
                     SELECT g.id FROM Student s
                     JOIN [Group Student] gs ON gs.sid = s.id
                     JOIN [Group] g ON g.id = gs.gid
                     JOIN Subject su ON su.id = g.suid
                     WHERE s.id = ?  AND su.name= ? AND su.[from] <=? AND su.[to] >= ?""";
        stm1 = connection.prepareStatement(sql1);
        stm1.setString(1, sid);
        stm1.setString(2, subname);
        stm1.setDate(3, from);
        stm1.setDate(4, to);
        ResultSet rs = stm1.executeQuery();
        while (rs.next()) {
            String gid = rs.getString("id");
            PreparedStatement stm;
            String sql = """
                         SELECT gs.isgrade FROM Student s
                         JOIN [Group Student] gs ON gs.sid = s.id
                         JOIN [Group] g ON g.id = gs.gid
                         JOIN Subject su ON su.id = g.suid
                         WHERE s.id = ?  AND g.id = ?""";
            stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setString(2, gid);
            ResultSet rs1 = stm.executeQuery();
            while (rs1.next()) {
                isgrade = rs1.getInt("isgrade");
            }
        }
        return isgrade;
    }
}
