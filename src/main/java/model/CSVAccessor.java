/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author zach malonjao
 */
public class CSVAccessor {
     
    private List<String[]> csvArrayList;
    
//    CSVAccessor(List<String[]> csvArrayList){
//        this.csvArrayList=csvArrayList;
//    }
//    
    public List<String[]> loadCSV(String csvFile){
       csvArrayList = new ArrayList<>();
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
    
    //Para magupdate, use this method
  
    public void updateCSV(List<String[]> csvArrayList, String file)throws IOException{
         try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(String[] updatedcsv:csvArrayList){
                writer.write(String.join(",",updatedcsv));
                writer.newLine();
              }
        }catch(IOException e){
               JOptionPane.showMessageDialog(null, "Error Writing to File");
               e.printStackTrace();
           }
    }
    
    //Para magsearch, use this method

    public String[] searchUserData(List<String[]> csvArrayList, String index){
        for (String[] row : csvArrayList) {
            if (row[0].equals(index)) {
                return row;
            }
        }
        return null; // Returns null if the employee doesn't exist


    }
    
}
