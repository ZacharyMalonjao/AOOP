/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zach malonjao
 */
public class AppSession {
   
    private static Employee loggedInEmployee;
    private static Admin loggedInAdmin;

    public static Employee getLoggedInEmployee() {
        return loggedInEmployee;
    }

    public static void setLoggedInEmployee(Employee employee) {
        loggedInEmployee = employee;
    }
    
    public static Admin getLoggedInAdmin(){
        return loggedInAdmin;
    }
    
    public static void setLoggedInAdmin(Admin admin){
        loggedInAdmin = admin;
    }
    
    
}

