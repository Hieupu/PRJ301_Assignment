/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Student {
    private String id;
    private String name;
    private boolean attent;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(String id, String name, boolean attent) {
        this.id = id;
        this.name = name;
        this.attent = attent;
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

    public boolean isAttent() {
        return attent;
    }

    public void setAttent(boolean attent) {
        this.attent = attent;
    }
    
}
