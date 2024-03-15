/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Student {

    private String id;
    private String name;
    private String attent;
    private String des;
    private Group group;
    private int isgrade;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(String id, String name, String attent, String des) {
        this.id = id;
        this.name = name;
        this.attent = attent;
        this.des = des;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttent() {
        return attent;
    }

    public void setAttent(String attent) {
        this.attent = attent;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getIsgrade() {
        return isgrade;
    }

    public void setIsgrade(int isgrade) {
        this.isgrade = isgrade;
    }
    
}
