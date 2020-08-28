/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Vaibhav
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vaibhav
 */
public class Swim extends HttpServlet {

    private String name;
    private int age;
    private int contact;
    private String address;
    private String time;

    private String pack;

    public Swim(String name, int age, int contact, String address, String time, String pack) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.address = address;
        this.time = time;
        this.pack = pack;
    }

    public Swim() {
        name = "";
        age = 0;
        contact = 0;
        address = "";
        time = "";
        pack = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

}
