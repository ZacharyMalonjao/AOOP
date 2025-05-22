/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import ui.AdminDashboardGUI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.JButton;
/*
*
 *
 * @author zach malonjao
 */
public class Admin extends User{
    public Admin(String employeeId, String lastName, String firstName, String birthday, String address,
                    String phoneNumber, String sssNumber, String philhealthNumber, String tinNumber,
                    String pagIbigNumber, String status, String position, String immediateSupervisor,
                    double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance,
                    double grossSemiMonthlyRate, double hourlyRate) {
        super(employeeId, lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber,
              tinNumber, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy,
              phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate);
    }
    
    CSVAccessor csva = new CSVAccessor();
    private List<String[]> csvArrayList = csva.loadCSV("MotorPH.csv");
    private List<String[]> leaveArrayList = csva.loadCSV("LeaveManagement.csv");
    
    
//-----------------------------------------------------------------
//ACCESS ROLES
    
    public void accessDatabase(JPanel parentPanel, JPanel databasePanel, JPanel databaseMenu, JPanel dashboardMenu, JPanel leaveMenu, JPanel salaryMenu){
        databaseMenu.setBackground(new Color (51,51,51));
        dashboardMenu.setBackground(new Color (153,0,0));
        leaveMenu.setBackground(new Color (153,0,0));
        salaryMenu.setBackground(new Color (153,0,0));
        parentPanel.removeAll();
        parentPanel.add(databasePanel);
        parentPanel.repaint();
        parentPanel.revalidate();
    }
    

    @Override
    public void accessLeave(AccessParameters accessParameters){
        accessParameters.getLeaveMenu().setBackground(new Color (51,51,51));
        accessParameters.getDashboardMenu().setBackground(new Color (153,0,0));
        accessParameters.getSalaryCalculationMenu().setBackground(new Color (153,0,0));
        accessParameters.getAdminDatabaseMenu().setBackground(new Color (153,0,0));
        accessParameters.getjPanelParent().removeAll();
        accessParameters.getjPanelParent().add(accessParameters.getDestination());
        accessParameters.getjPanelParent().repaint();
        accessParameters.getjPanelParent().revalidate();
    }
    
   @Override
    public void accessDashboard(User admin){
        AdminDashboardGUI adb = new AdminDashboardGUI((Admin)admin);
           adb.setVisible(true);
    }
    
    @Override
    public void accessSalaryCalculation(AccessParameters accessParameters) {
        
              
        accessParameters.getSalaryCalculationMenu().setBackground(new Color (51,51,51));
        accessParameters.getDashboardMenu().setBackground(new Color (153,0,0));
        accessParameters.getAdminDatabaseMenu().setBackground(new Color (153,0,0));
        accessParameters.getLeaveMenu().setBackground(new Color (153,0,0));
        accessParameters.getjPanelParent().removeAll();
        accessParameters.getjPanelParent().add(accessParameters.getDestination());
        accessParameters.getjPanelParent().repaint();
        accessParameters.getjPanelParent().revalidate();
        
       
    }
    
//-----------------------------------------------------------------
//CSV Tools
    //Para magbasa ng csv, use this method.
   
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
    
 //Stuff only they can do
    
     protected void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }
     
      protected void setRiceSubsidy(double riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }
      
     protected void setPhoneAllowance(double phoneAllowance) {
    this.phoneAllowance = phoneAllowance;
}
     
     protected void setGrossSemiMonthlyRate(double grossSemiMonthlyRate) {
    this.grossSemiMonthlyRate = grossSemiMonthlyRate;
}
    protected void setClothingAllowance(double clothingAllowance) {
    this.clothingAllowance = clothingAllowance;
}
    
    protected void setHourlyRate(double hourlyRate) {
    this.hourlyRate = hourlyRate;
}
    
    
//CRUD ACTIONS   
    //------------------------------------C R E A T E -- E M P L O Y E E-----------------------------------------
    private boolean isValid;
   
    private boolean alreadyExists(String input, int index){
         
         for (String[] row : csvArrayList) {
           //this is to avoid IndexOutOfBounds
            if (row.length >= index) {
                // Check the index of each array
                if (row[index].equals(input)) {
                    return true;
                }
            }
        }
         return false;
     }
    

     public void createEmployee(String empNum, String firstName, String lastName, String birthday, String address,
                                String phoneNum, String sssNum, String philHealthNum, String tinNum,
                                String pagibigNum, String status, String position, String supervisor,
                                String basicSalary, String riceSubsidy, String phoneAllowance,
                                String clothingAllowance, JFrame parent) throws ParseException {
        
        isValid = true;
        String[] newRow = new String[19];

        // Validate inputs
        if (firstName.trim().isEmpty() || lastName.trim().isEmpty() || address.trim().isEmpty() ||
            phoneNum.trim().isEmpty() || sssNum.trim().isEmpty() || philHealthNum.trim().isEmpty() ||
            tinNum.trim().isEmpty() || pagibigNum.trim().isEmpty() || basicSalary.trim().isEmpty() ||
            riceSubsidy.trim().isEmpty() || phoneAllowance.trim().isEmpty() || clothingAllowance.trim().isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Please fill in all fields");
            
            isValid=false;
            //if switch case:break = ifElse:return
            return;
        }

        Validator validator = new Validator();
        newRow[0] = empNum;
        
            
            

        if (!validator.isValidString(lastName)) {
            JOptionPane.showMessageDialog(parent, "Please input valid Last Name");
            isValid = false;
            return;
        } else {
            newRow[1] = lastName;
        }

        if (!validator.isValidString(firstName)) {
            JOptionPane.showMessageDialog(parent, "Please input valid First Name");
            isValid = false;
            return;
        } else {
            newRow[2] = firstName;
        }
        
        // get current date
        Calendar today = Calendar.getInstance();
        Date currentDate = today.getTime();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date bdayDate = dateFormat.parse(birthday);
        
        if (bdayDate.after(currentDate)) {
            JOptionPane.showMessageDialog(parent, "Please input valid Birthday");
            isValid = false;
            return;
        }
            
        
        newRow[3] = birthday; // Assuming birthday is already formatted

        if (!validator.isValidAddress(address)) {
            JOptionPane.showMessageDialog(parent, "Please exclude using commas in Address");
            isValid = false;
            return;
        } else {
            newRow[4] = address;
        }

        if (!validator.isValidPhoneNumber(phoneNum)) {
            JOptionPane.showMessageDialog(parent, "Please input valid Phone Number (xxx-xxx-xxx)");
            isValid = false;
            return;
        } else {
            
             if(alreadyExists(phoneNum, 5)){
                JOptionPane.showMessageDialog(parent, "Phone number already exists, please try again");
                isValid = false;
                return;
            }else{
                newRow[5] = phoneNum;
            }
        }

        if (!validator.isValidSssNumber(sssNum)) {
            JOptionPane.showMessageDialog(parent, "Please format SSS as xx-xxxxxxx-x");
            isValid = false;
            return;
        } else {
            if(alreadyExists(sssNum, 6)){
                JOptionPane.showMessageDialog(parent, "SSS already exists, please try again");
                isValid = false;
                return;
            }else{
                newRow[6] = sssNum;
            }
        }
        
        

        if (!validator.isValidPhilhealthNumber(philHealthNum)) {
            JOptionPane.showMessageDialog(parent, "Please format PhilHealth as xxxxxxxxxxxx (12 digits)");
            isValid = false;
            return;
        } else {
            if(alreadyExists(philHealthNum, 7)){
                JOptionPane.showMessageDialog(parent, "PhilHealth already exists, please try again");
                isValid = false;
                return;
            }else{
                 newRow[7] = philHealthNum;
            }
           
        }

        if (!validator.isValidTin(tinNum)) {
            JOptionPane.showMessageDialog(parent, "Please format TIN as xxx-xxx-xxx-xxx");
            isValid = false;
            return;
        } else {
            if(alreadyExists(tinNum, 8)){
                JOptionPane.showMessageDialog(parent, "Tin Number already exists, please try again");
                isValid = false;
                return;
            }else{
                 newRow[8] = tinNum;
            }
            
        }

        if (!validator.isValidPagibigNumber(pagibigNum)) {
            JOptionPane.showMessageDialog(parent, "Please format Pagibig as xxxxxxxxxxxx (12 digits)");
            isValid = false;
            return;
        } else {
            if(alreadyExists(pagibigNum, 9)){
                JOptionPane.showMessageDialog(parent, "Pagibig already exists, please try again");
                isValid = false;
                return;
            }else{
                 newRow[9] = pagibigNum;
            }
            
        }

        newRow[10] = status;
        newRow[11] = position;
        newRow[12] = supervisor;

        if (!validator.isValidNumber(basicSalary)) {
            JOptionPane.showMessageDialog(parent, "Please input valid Basic Salary");
            isValid = false;
            return;
        } else {
            newRow[13] = basicSalary;
        }

        if (!validator.isValidNumber(riceSubsidy)) {
            JOptionPane.showMessageDialog(parent, "Please input valid Rice Subsidy");
            isValid = false;
            return;
        } else {
            newRow[14] = riceSubsidy;
        }

        if (!validator.isValidNumber(phoneAllowance)) {
            JOptionPane.showMessageDialog(parent, "Please input valid Phone Allowance");
            isValid = false;
            return;
        } else {
            newRow[15] = phoneAllowance;
        }

        if (!validator.isValidNumber(clothingAllowance)) {
            JOptionPane.showMessageDialog(parent, "Please input valid Clothing Allowance");
            isValid = false;
            return;
        } else {
            newRow[16] = clothingAllowance;
        }

        newRow[17] = String.valueOf(Double.parseDouble(basicSalary) / 2);
        newRow[18] = String.valueOf((Double.parseDouble(basicSalary) / 2) / 84);

       if (isValid) {
           
           try{
                csvArrayList.add(newRow);
                updateCSV(csvArrayList, "MotorPH.csv");
                JOptionPane.showMessageDialog(parent, "Updated Successfully");
        
           }catch(IOException e){
               JOptionPane.showMessageDialog(parent, "Error Writing to File");
               e.printStackTrace();
           }
           
       }   
     }
     
     public boolean isInputValid(){
         return isValid;
     }
     
    
     //--------------------------------E D I T -- E M P L O Y E E-------------------------------------------
    
   private boolean isValidEdit;
   
   private boolean alreadyExists(String input, int index, String employeeID) {
    for (String[] row : csvArrayList) {
        // Skip the row of the employee being edited
        if (!row[0].equals(employeeID) && row.length >= index && row[index].equals(input)) {
            return true;
        }
    }
    return false;
}
   public void updateEmployee(String employeeID, String firstName, String lastName, Date birthday,
                           String address, String phoneNum, String sssNum, String philHealthNum,
                           String tinNum, String pagibigNum, String status, String position,
                           String supervisor, String basicSalary, String riceSubsidy,
                           String phoneAllowance, String clothingAllowance, String grossSemiMonthly,
                           String hourlyRate, JFrame parent) {
       isValidEdit=true;
    Validator validator = new Validator();

    // Validate inputs
    //
     if (firstName.trim().isEmpty() || lastName.trim().isEmpty() || address.trim().isEmpty() ||
            phoneNum.trim().isEmpty() || sssNum.trim().isEmpty() || philHealthNum.trim().isEmpty() ||
            tinNum.trim().isEmpty() || pagibigNum.trim().isEmpty() || basicSalary.trim().isEmpty() ||
            riceSubsidy.trim().isEmpty() || phoneAllowance.trim().isEmpty() || clothingAllowance.trim().isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Please fill in all fields");
            
            isValidEdit=false;
            //if switch case:break = ifElse:return
            return;
        }
   
    if (!validator.isValidString(firstName)) {
           JOptionPane.showMessageDialog(parent, "Please input valid First Name");
           isValidEdit = false;
           return;
       }
    

   
    
    if (!validator.isValidString(lastName)) {
            JOptionPane.showMessageDialog(parent, "Please input valid Last Name");
            isValidEdit = false;
            return;
        } 
    
    Calendar today = Calendar.getInstance();
    Date currentDate = today.getTime();
    
    if (birthday.after(currentDate)) {
        JOptionPane.showMessageDialog(parent, "Please input valid Birthday");
        isValidEdit = false;
        return;
    }
   
    if (!validator.isValidAddress(address)) {
            JOptionPane.showMessageDialog(parent, "Please exclude using commas in Address");
            isValidEdit = false;
            return;
        }

 

    if (!validator.isValidPhoneNumber(phoneNum)) {
        JOptionPane.showMessageDialog(parent, "Please input a valid Phone Number (xxx-xxx-xxx).");
        isValidEdit=false;
        return;
    }else{
        if(alreadyExists(phoneNum, 5, employeeID)){
                JOptionPane.showMessageDialog(parent, "Phone number already exists, please try again");
                isValidEdit = false;
                return;
            }
    }

        if(!validator.isValidSssNumber(sssNum)){
            JOptionPane.showMessageDialog(parent, "Please format SSS as xx-xxxxxxx-x.");
            isValidEdit = false;
            return;
        }else{
            if(alreadyExists(sssNum, 6, employeeID)){
                    JOptionPane.showMessageDialog(parent, "SSS already exists, please try again");
                    isValidEdit = false;
                    return;
                }
        }
    

    if (!validator.isValidPhilhealthNumber(philHealthNum)) {
        JOptionPane.showMessageDialog(parent, "Please format PhilHealth as xxxxxxxxxxxx (12 digits).");
        isValidEdit=false;
        return;
    }else{
        if(alreadyExists(philHealthNum, 7, employeeID)){
                JOptionPane.showMessageDialog(parent, "PhilHealth already exists, please try again");
                isValidEdit = false;
                return;
            }
    }

    if (!validator.isValidTin(tinNum)) {
        JOptionPane.showMessageDialog(parent, "Please format TIN as xxx-xxx-xxx-xxx.");
        isValidEdit=false;
        return;
    }else{
         if(alreadyExists(tinNum, 8, employeeID)){
                JOptionPane.showMessageDialog(parent, "Tin Number already exists, please try again");
                isValidEdit = false;
                return;
            }
    }

    if (!validator.isValidPagibigNumber(pagibigNum)) {
        JOptionPane.showMessageDialog(parent, "Please format Pagibig as xxxxxxxxxxxx (12 digits).");
        isValidEdit=false;
        return;
    }else{
        if(alreadyExists(pagibigNum, 9, employeeID)){
                JOptionPane.showMessageDialog(parent, "Pagibig already exists, please try again");
                isValidEdit = false;
                return;
            }
    }

    if (!validator.isValidNumber(basicSalary)) {
        JOptionPane.showMessageDialog(parent, "Please input a valid Basic Salary.");
        isValidEdit=false;
        return;
    }

    if (!validator.isValidNumber(riceSubsidy)) {
        JOptionPane.showMessageDialog(parent, "Please input a valid Rice Subsidy.");
        isValidEdit=false;
        return;
    }

    if (!validator.isValidNumber(phoneAllowance)) {
        JOptionPane.showMessageDialog(parent, "Please input a valid Phone Allowance.");
        isValidEdit=false;
        return;
    }

    if (!validator.isValidNumber(clothingAllowance)) {
        JOptionPane.showMessageDialog(parent, "Please input a valid Clothing Allowance.");
        isValidEdit=false;
        return;
    }

    if (!validator.isValidNumber(grossSemiMonthly)) {
        JOptionPane.showMessageDialog(parent, "Please input a valid Gross Semi Monthly.");
        isValidEdit=false;
        return;
    }

    if (!validator.isValidNumber(hourlyRate)) {
        JOptionPane.showMessageDialog(parent, "Please input a valid Hourly Rate.");
        isValidEdit=false;
        return;
    }

    // Find and update the employee record
    boolean employeeFound = false;
    for (String[] employee : csvArrayList) {
        //If the input's id is found, edit that employee
        if (employee[0].equals(employeeID)) {
            isValidEdit=true;
            employee[1] = lastName;
            employee[2] = firstName;
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            employee[3] = sdf.format(birthday);
            employee[4] = address;
            employee[5] = phoneNum;
            employee[6] = sssNum;
            employee[7] = philHealthNum;
            employee[8] = tinNum;
            employee[9] = pagibigNum;
            employee[10] = status;
            employee[11] = position;
            employee[12] = supervisor;
            employee[13] = basicSalary;
            employee[14] = riceSubsidy;
            employee[15] = phoneAllowance;
            employee[16] = clothingAllowance;
            employee[17] = grossSemiMonthly;
            employee[18] = hourlyRate;

            // Write updated data back to CSV
            try {
                csva.updateCSV(csvArrayList, "MotorPH.csv");
                JOptionPane.showMessageDialog(parent, "Updated Successfully");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parent, "Error writing to file: " + e.getMessage());
            }

            employeeFound = true;
            break; // Exit the loop after updating
        }
    }

    // If employee not found, display a message
    if (!employeeFound) {
        JOptionPane.showMessageDialog(parent, "Employee not found.");
    }
    
}
   public boolean getIsValidEditInput(){
       return isValidEdit;
   }
   
   
   public void deleteEmployee(int index){
        for (int i = 0; i < csvArrayList.size(); i++) {
                String[] record = csvArrayList.get(i);

                if (record[0].equals(String.valueOf(index))) {
                    csvArrayList.remove(i);

                    break;
                }
            }
        try{
            updateCSV(csvArrayList, "MotorPH.csv");
       }catch(IOException e){
          JOptionPane.showMessageDialog(null, "Error Writing to file");
          e.printStackTrace();
      }
   }

  //------------------------------L E A V E----------------------------------------------------
    public void approveLeave(String index){
        leaveArrayList = csva.loadCSV("LeaveManagement.csv");
        String[] empLeaveData = csva.searchUserData(leaveArrayList, index);
                if (empLeaveData != null) {
                     empLeaveData[8] = "Approved";
                     
                     try{
                         csva.updateCSV(leaveArrayList,"LeaveManagement.csv" );
                     }catch(IOException e){
                         JOptionPane.showMessageDialog(null, "Error Writing File");
                         e.printStackTrace();
                     }
                     
                     //Denise, palitan mo ng pangalan ng approve button yung "null"
                    JOptionPane.showMessageDialog(null, "Succesfully Approved!");
                    
                } else {
                    // handle the case where employeeData is null
                    JOptionPane.showMessageDialog(null, "Employee not found!");
                } 
        
    }
    
    public void denyLeave(String index, JButton parent){
        leaveArrayList = csva.loadCSV("LeaveManagement.csv");
        String[] empLeaveData = csva.searchUserData(leaveArrayList, index);
                if (empLeaveData != null) {
                     empLeaveData[8] = "Denied";
                     
                     try{
                         csva.updateCSV(leaveArrayList,"LeaveManagement.csv" );
                     }catch(IOException e){
                         JOptionPane.showMessageDialog(null, "Error Writing File");
                         e.printStackTrace();
                     }
                     
                     //Denise, palitan mo ng pangalan ng approve button yung "null"
                    JOptionPane.showMessageDialog(parent, "Succesfully Denied!");
                    
                } else {
                    // handle the case where employeeData is null
                    JOptionPane.showMessageDialog(parent, "Employee not found!");
                } 
        
    }
   
     
     
     
}
   

    