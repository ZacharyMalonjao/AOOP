/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import ui.EmployeeDashboardGUI;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author zach malonjao
 */
public class Employee extends User {
    private List<String[]> csvArrayList;
    CSVAccessor csva = new CSVAccessor();
    double salary;
        
    
    public Employee(String employeeId, String lastName, String firstName, String birthday, String address,
                    String phoneNumber, String sssNumber, String philhealthNumber, String tinNumber,
                    String pagIbigNumber, String status, String position, String immediateSupervisor,
                    double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance,
                    double grossSemiMonthlyRate, double hourlyRate) {
        super(employeeId, lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber,
              tinNumber, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy,
              phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate);
    }

 //------------------------------------------------------------------------   
 //ACCESS METHODS 
    
  //1. GO TO FILE LEAVE
   @Override
   public void accessLeave(AccessParameters accessParameters){
        accessParameters.getLeaveMenu().setBackground(new Color (51,51,51));
        accessParameters.getDashboardMenu().setBackground(new Color (153,0,0));
        accessParameters.getSalaryCalculationMenu().setBackground(new Color (153,0,0));
        accessParameters.getjPanelParent().removeAll();
        accessParameters.getjPanelParent().add(accessParameters.getDestination());
        accessParameters.getjPanelParent().repaint();
        accessParameters.getjPanelParent().revalidate();
   }
   //2. GO TO DASHBOARD 
   @Override
    public void accessDashboard(User emp){
        EmployeeDashboardGUI ed = new EmployeeDashboardGUI((Employee)emp);
           ed.setVisible(true);
    }
   //3. GO TO SALARY CALC FOR EMPLOYEES  
    @Override
    public void accessSalaryCalculation(AccessParameters accessParameters){
        accessParameters.getSalaryCalculationMenu().setBackground(new Color (51,51,51));
        accessParameters.getDashboardMenu().setBackground(new Color (153,0,0));
        accessParameters.getLeaveMenu().setBackground(new Color (153,0,0));
        accessParameters.getjPanelParent().removeAll();
        accessParameters.getjPanelParent().add(accessParameters.getDestination());
        accessParameters.getjPanelParent().repaint();
        accessParameters.getjPanelParent().revalidate();
        
       //parevise in the future
    }
    
    public void accessAttendance(AccessParameters accessParameters){
        accessParameters.getAttendanceMenu().setBackground(new Color (51,51,51));
        accessParameters.getDashboardMenu().setBackground(new Color (153,0,0));
        accessParameters.getLeaveMenu().setBackground(new Color (153,0,0));
        accessParameters.getjPanelParent().removeAll();
        accessParameters.getjPanelParent().add(accessParameters.getDestination());
        accessParameters.getjPanelParent().repaint();
        accessParameters.getjPanelParent().revalidate();
        
       //parevise in the future
    }

//------------------------------------------------------------------------    
//CSV TOOLS
 
    public List<String[]> loadCSV(String csvFile){
    return csva.loadCSV(csvFile);
    }
    
    //Para magupdate, use this method

    public void updateCSV(List<String[]> csvArrayList, String file)throws IOException{
        csva.updateCSV(csvArrayList, file);
    }
    
    //Para magsearch, use this method

    public String[] searchUserData(List<String[]> csvArrayList, String index){
        return csva.searchUserData(csvArrayList, index);
    }
//------------------------------------------------------------------------
//STUFF THE USER DOES  
  public void fileLeave(String[] leaveData, List<String[]> csvArrayList, String file){
      csvArrayList.add(leaveData);
      try{updateCSV(csvArrayList, file);
      }catch(IOException e){
          JOptionPane.showMessageDialog(null, "Error Writing to file");
          e.printStackTrace();
      }
  
    }
}
    
   

    
    

