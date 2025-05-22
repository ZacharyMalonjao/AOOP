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
public class LoginValidator extends AbstractLoginValidator{

    
    public boolean validateLogin(UnidentifiedUser unidentifiedUser){
        if(unidentifiedUser==null){
            throw new IllegalArgumentException("UnidentifiedUser  cannot be null");
        }else{
        
            String csvFile = "LoginCredentials.csv";
             String line = "";
             String csvDelimiter = ",";
             BufferedReader br = null;

             try {
                 br = new BufferedReader(new FileReader(csvFile));

                 //BufferedReader reads cvs file line by line
                 while ((line = br.readLine()) != null) {
                     String[] user = line.split(csvDelimiter);
                     // if the user's inputted credenentials is in one of these lines, login is valid
                     if (user.length >=3 && user[1].equals(unidentifiedUser.getUsername()) && user[2].equals(unidentifiedUser.getPassword())) {
                         unidentifiedUser.setEmployeeID(user[0]);
                         unidentifiedUser.setRole(user[3]); // + set role of user account
                         
                         
                        
       
                         return true;
                     }
                 }
             } catch (IOException e) {
                 e.getMessage();
             } finally {
                 try {
                     // close the BufferedReader in case while loop already finished executing 
                     // but text can still be read so there's no data leak
                     if (br != null) {
                         br.close();
                     }
                 } catch (IOException e) {
                     e.getMessage();
                 }
             }

             // if the inputted credentials is not in the cvs file, credentials are invalid
             return false; 
           
        } 
    
 }
    
    public void openDashboard(UnidentifiedUser unidentifiedUser){
        
        if (unidentifiedUser .getRole() == null) {
            throw new IllegalArgumentException("User  role cannot be null");
        }else{
        
            if(unidentifiedUser.getRole().equals("Admin")){
                Admin admin = setAdminData(unidentifiedUser);
                 AppSession.setLoggedInAdmin(admin);
                  admin.accessDashboard(admin);

            }else{
               Employee emp = setEmployeeData(unidentifiedUser);
               AppSession.setLoggedInEmployee(emp);
                    emp.accessDashboard(emp); 
                 
            }

 
        }
        
     }
    
    
      
       
}
