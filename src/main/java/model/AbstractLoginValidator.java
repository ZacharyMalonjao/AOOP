/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zach malonjao
 */
public abstract class AbstractLoginValidator {
     String[] userData;
     protected List<String[]> loadCSV(String csvFile){
       List<String[]> csvArrayList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                csvArrayList.add(columns);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return csvArrayList;
    }
     
     protected String[] searchUserData(List<String[]> csvArrayList, String index){
        for (String[] row : csvArrayList) {
            if (row[0].equals(index)) {
                return row;
            }
        }
        return null;
        
    }
     
      public Employee setEmployeeData(UnidentifiedUser unidentifiedUser){
        String index = unidentifiedUser.getEmployeeID();
        List<String[]> csvArrayList = loadCSV("MotorPH.csv");
        userData = searchUserData(csvArrayList, index);
        
            if (userData == null) {
                throw new IllegalArgumentException("User  data not found for index: " + index);
            }else{
               Employee emp = new Employee(
                userData[0],  // employeeId
                userData[1],  // lastName
                userData[2],  // firstName
                userData[3],  // birthday
                userData[4],  // address
                userData[5],  // phoneNumber
                userData[6],  // sssNumber
                userData[7],  // philhealthNumber
                userData[8],  // tinNumber
                userData[9],  // pagIbigNumber
                userData[10], // status
                userData[11], // position
                userData[12], // immediateSupervisor
                Double.parseDouble(userData[13]), // basicSalary
                Double.parseDouble(userData[14]), // riceSubsidy
                Double.parseDouble(userData[15]), // phoneAllowance
                Double.parseDouble(userData[16]), // clothingAllowance
                Double.parseDouble(userData[17]), // grossSemiMonthlyRate
                Double.parseDouble(userData[18])  // hourlyRate
            );
           return emp;    
            }
    }
    
    public Admin setAdminData(UnidentifiedUser unidentifiedUser){
        String index = unidentifiedUser.getEmployeeID();
        List<String[]> csvArrayList = loadCSV("MotorPH.csv");
         userData = searchUserData(csvArrayList, index);
        
            if (userData == null) {
                throw new IllegalArgumentException("User  data not found for index: " + index);
            }else{
               Admin admin = new Admin(
                userData[0],  // employeeId
                userData[1],  // lastName
                userData[2],  // firstName
                userData[3],  // birthday
                userData[4],  // address
                userData[5],  // phoneNumber
                userData[6],  // sssNumber
                userData[7],  // philhealthNumber
                userData[8],  // tinNumber
                userData[9],  // pagIbigNumber
                userData[10], // status
                userData[11], // position
                userData[12], // immediateSupervisor
                Double.parseDouble(userData[13]), // basicSalary
                Double.parseDouble(userData[14]), // riceSubsidy
                Double.parseDouble(userData[15]), // phoneAllowance
                Double.parseDouble(userData[16]), // clothingAllowance
                Double.parseDouble(userData[17]), // grossSemiMonthlyRate
                Double.parseDouble(userData[18])  // hourlyRate
            );
              
               return admin; 
            }
    }
     
  
    
    
   
    
    
}
