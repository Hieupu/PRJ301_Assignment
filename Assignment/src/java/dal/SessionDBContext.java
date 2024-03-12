/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Group;
import entity.Lecture;
import entity.Room;
import entity.Session;
import entity.Slot;
import entity.Student;
import entity.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> leclist(String lid, Date from, Date to) throws SQLException {
        ArrayList<Session> ses = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "SELECT se.date, su.name AS subject_name, sl.value, sl.duration, sl.day, l.name AS lecture_name, g.name AS group_name, r.code, se.istaken "
                + "FROM [Group] g "
                + "JOIN [Session] se ON se.gid = g.id "
                + "JOIN Slot sl ON sl.id = se.slid "
                + "JOIN Subject su ON su.id = g.suid "
                + "JOIN Lecture l ON l.id = se.lid "
                + "JOIN Room r ON r.id = se.rid "
                + "WHERE se.lid=? AND se.[date] >=? AND se.[date]<=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slot sl = new Slot();
                sl.setValue(rs.getInt("value"));
                sl.setDuration(rs.getString("duration"));
                sl.setDay(rs.getInt("day"));

                Subject su = new Subject();
                su.setName(rs.getString("subject_name"));

                Lecture l = new Lecture();
                l.setName(rs.getString("lecture_name"));

                Group g = new Group();
                g.setName(rs.getString("group_name"));

                Room r = new Room();
                r.setCode(rs.getString("code"));

                Session session = new Session();
                session.setSlot(sl);
                session.setSubject(su);
                session.setLecture(l);
                session.setGroup(g);
                session.setRoom(r);
                java.sql.Date datefromDB = rs.getDate("date");
                session.setDate(datefromDB);
                session.setTaken(rs.getBoolean("istaken"));
                ses.add(session);
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
        return ses;
    }

    public ArrayList<Session> stulist(String sid, Date from, Date to) throws SQLException {
        ArrayList<Session> ses = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = """
                     SELECT se.date, su.name AS subject_name, sl.value, sl.duration, sl.day, l.name AS lecture_name, g.name AS group_name, r.code, a.isattend, s.name
                                     FROM [Group] g 
                                      JOIN [Session] se ON se.gid = g.id 
                                      JOIN Slot sl ON sl.id = se.slid 
                                      JOIN Subject su ON su.id = g.suid 
                                      JOIN Lecture l ON l.id = se.lid 
                                      JOIN Room r ON r.id = se.rid 
                                      JOIN Attendance a ON se.id = a.seid
                                      JOIN Student s ON s.id = a.sid
                                      WHERE a.sid= ? AND se.[date] >= ? AND se.[date]<= ?""";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slot sl = new Slot();
                sl.setValue(rs.getInt("value"));
                sl.setDuration(rs.getString("duration"));
                sl.setDay(rs.getInt("day"));

                Subject su = new Subject();
                su.setName(rs.getString("subject_name"));

                Lecture l = new Lecture();
                l.setName(rs.getString("lecture_name"));

                Group g = new Group();
                g.setName(rs.getString("group_name"));

                Room r = new Room();
                r.setCode(rs.getString("code"));

                Student s = new Student();
                s.setName(rs.getString("name"));
                s.setAttent(rs.getString("isattend"));
                Session session = new Session();
                session.setSlot(sl);
                session.setSubject(su);
                session.setLecture(l);
                session.setGroup(g);
                session.setRoom(r);
                session.setStu(s);
                java.sql.Date datefromDB = rs.getDate("date");
                session.setDate(datefromDB);
                ses.add(session);
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
        return ses;
    }
}
