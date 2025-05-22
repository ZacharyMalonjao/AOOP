/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import javax.swing.JPanel;
import java.awt.Color;
/**
 *
 * @author zach malonjao
 */


    /*This class exists because 
     1. User class has access methods that take 6 parameters (4 pages, 1 panel and 1 destination, all JPanels)
     2. El mucho problemo because Employee only needs 5 parameters (3 pages, 1 panel and 1 destination, all JPanels)
     3. To solve it, I will replace the parameters with this object (called a data transfer object)  so polymorphism won't be jumbled
     */


public class AccessParameters {
    private JPanel LeaveMenu;
    private JPanel DashboardMenu;
    private JPanel SalaryCalculationMenu;
    private JPanel adminDatabaseMenu;
    private JPanel jPanelParent;
    private JPanel attendanceMenu;
    
    private JPanel destination;
    
    
    public void setEmployeePageParameters(JPanel LeaveMenu,JPanel DashboardMenu, JPanel SalaryCalculationMenu, JPanel jPanelParent, JPanel attendanceMenu, JPanel destination){
        this.LeaveMenu=LeaveMenu;
        this.DashboardMenu= DashboardMenu;
        this.SalaryCalculationMenu=SalaryCalculationMenu;
        this.attendanceMenu = attendanceMenu;
        this.jPanelParent = jPanelParent;
        this.destination=destination;
    }
    
    public void setAdminPageParameters(JPanel LeaveMenu,JPanel DashboardMenu, JPanel SalaryCalculationMenu, JPanel adminDatabaseMenu, JPanel jPanelParent, JPanel destination ){
        this.LeaveMenu=LeaveMenu;
        this.DashboardMenu= DashboardMenu;
        this.SalaryCalculationMenu=SalaryCalculationMenu;
        this.adminDatabaseMenu=adminDatabaseMenu;
        this.jPanelParent = jPanelParent;
        this.destination=destination;
    }
    
    
     // Getter and Setter for adminLeaveMenu
    public JPanel getLeaveMenu() {
        return LeaveMenu;
    }
    
    public void setLeaveMenu(JPanel LeaveMenu) {
        this.LeaveMenu = LeaveMenu;
    }
    
    // Getter and Setter for DashboardMenu
    public JPanel getDashboardMenu() {
        return DashboardMenu;
    }
    
    public void setDashboardMenu(JPanel DashboardMenu) {
        this.DashboardMenu = DashboardMenu;
    }
    
    // Getter and Setter for SalaryCalculationMenu
    public JPanel getSalaryCalculationMenu() {
        return SalaryCalculationMenu;
    }
    
    public void setSalaryCalculationMenu(JPanel SalaryCalculationMenu) {
        this.SalaryCalculationMenu = SalaryCalculationMenu;
    }
    
    // Getter and Setter for adminDatabaseMenu
    public JPanel getAdminDatabaseMenu() {
        return adminDatabaseMenu;
    }
    
    public void setAdminDatabaseMenu(JPanel adminDatabaseMenu) {
        this.adminDatabaseMenu = adminDatabaseMenu;
    }
    
    // Getter and Setter for jPanelParent
    public JPanel getjPanelParent() {
        return jPanelParent;
    }
    
    public void setjPanelParent(JPanel jPanelParent) {
        this.jPanelParent = jPanelParent;
    }
    
    // Getter and Setter for adminLeaveManagement
    public JPanel getDestination() {
        return destination;
    }
    
    public void setDestination(JPanel destination) {
        this.destination = destination;
    }
    public JPanel getAttendanceMenu() {
        return attendanceMenu;
    }
    
}
