/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Swim;
import view.UserDB;

/**
 *
 * @author Vaibhav
 */
public class SwimClub extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //retrieve context params (db credentials) from DD
        String connUrl = getServletContext().
                    getInitParameter("connUrl");
        String user = getServletContext().
                    getInitParameter("user");
        String pass = getServletContext().
                    getInitParameter("pass"); 
            
        String url = "";
        String message = "";
        //Collect Request
        String name = (String) request.getParameter("name");
        String age = (String) request.getParameter("age");
        String contact = (String) request.getParameter("contact");
        String address = (String) request.getParameter("address");
        String time = (String) request.getParameter("time");
        String pack = (String) request.getParameter("pack");

//      process input
        int age_s;
        int contact_s;

        age_s = Integer.parseInt(age);
        contact_s = Integer.parseInt(contact);
        
       
        Swim swimmer1 = new Swim(name, age_s, contact_s, address, time, pack);
 
// Create a data access object for database interactions
        UserDB userDB = new UserDB(connUrl, user, pass);
        
        boolean exists = UserDB.contactExists(swimmer1.getContact());
        System.out.println("++++++++"+exists);
        if (exists){
            message ="Person already exists. User information in the database is:";
            Swim user1 = UserDB.selectUser(swimmer1.getContact());
            request.setAttribute("swimmer1", user1);
            request.setAttribute("message", message);
            url = "/response.jsp";
        }
        else {
            // insert user data to the database
            int in = UserDB.insert(swimmer1);
            if (in != 0) {
                Swim user2 = UserDB.selectUser(contact_s);
               message = "User information as inserted to the database";
               request.setAttribute("swimmer1", user2);
               request.setAttribute("message", message);
                url = "/response.jsp";
                } 
            else {
                message = "Person not inserted into the database. Input data was:";
                request.setAttribute("swimmer1", swimmer1);
                request.setAttribute("message", message);
                url = "/response.jsp"; 
            } 
        }
        
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);   
    }
}
            
//        
////      create Response
//        response.setContentType("text/html;charset=UTF-8");
//        request.setAttribute("swimmer1", swimmer1);
//        RequestDispatcher view = request.getRequestDispatcher("response.jsp");
//        view.forward(request, response);
//
//    }
//}
