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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Claire
 */
public class AttendanceTracker {
    private String csvFile;
    private ArrayList<String[]> attendanceRecord;
    
    public AttendanceTracker() {
        this.csvFile = "AttendanceRecords.csv";
        
    }
    
    public ArrayList<String> retrieveEmployeeAttendanceRecord(String employeeNum) {
        makeArrayCopyOfCSV();
        
        // create csv formatted arraylist needed to populate attendance table
        ArrayList<String> employeeAttendanceRecord = new ArrayList<String>(); // data lang, wala headers
        for(String[] record : attendanceRecord) {
            if (record[0].equals(employeeNum)) {
                String row = record[3] + "," + record[4] + "," + record[5];
                employeeAttendanceRecord.add(row);
            }
        }

        return new ArrayList<String>(employeeAttendanceRecord);
    }
    
    private void makeArrayCopyOfCSV() {
        this.attendanceRecord = new ArrayList<String[]>();
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            
            String line;
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                attendanceRecord.add(values);
            }
            
            for (String[] record : attendanceRecord) {
                System.out.println();
                for (String data : record) {
                    System.out.print(data + " ");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void clockIn(Employee emp) {
        // access AttendanceRecords.csv
        // get employee num
        String employeeNum = emp.getEmployeeId();
        // get last name
        String lastName = emp.getLastName();
        // get first name
        String firstName = emp.getFirstName();
        
        
        // add all details to string array (employee#, lastname, first name, date, time in, 'null' for time out)
        String line = employeeNum + "," + lastName + "," + firstName + "," + getCurrentDate() + "," + getCurrentTime() + "," + "--:--";
        
        System.out.println(line); // check string using console

        // use try-catch when writing into file (use csvfile and append only)
        try(BufferedWriter br = new BufferedWriter(new FileWriter(csvFile, true))) {
            // add new row and new line
            br.write(line);
            br.newLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
            
    }
    
    public void clockOut(Employee emp) throws IOException {
        // make arraylist copy of csv file
        makeArrayCopyOfCSV();
        // find today's attendance entry of employee
        for(String[] record : attendanceRecord) {
            if(record[0].equals(emp.getEmployeeId()) && record[3].equals(getCurrentDate())) {
                // update clock out time
                System.out.println(emp.getEmployeeId());
                System.out.println(getCurrentDate());
                System.out.println(getCurrentTime());
                record[5] = getCurrentTime();
                
//                for(String data : record) {
//                    System.out.print(data + " ");
//                }
            }
        }
        
        // update csv file
        updateCSV(attendanceRecord, csvFile);
        
    }

    private String getCurrentDate() {
        LocalDate currentDate = getCurrentDateTime().toLocalDate();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // month/day/year ex. 12/21/2024
        return currentDate.format(dateFormat);
    }
    private String getCurrentTime() {
        LocalTime currentTime = getCurrentDateTime().toLocalTime();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm"); // hour:minue ex. 09:30
        return currentTime.format(timeFormat);
    }
    
    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
    
//    public void updateCSV(ArrayList<String[]> csvArrayList, String file)throws IOException{
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            for(String[] updatedcsv : csvArrayList){
//                writer.write(String.join(",",updatedcsv));
//                writer.newLine();
//            }
//            
//        } catch(IOException e){
//            JOptionPane.showMessageDialog(null, "Error Writing to File");
//            e.printStackTrace();
//        }
//    }
    
    public void updateCSV(ArrayList<String[]> csvArrayList, String file) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        for (String[] updatedcsv : csvArrayList) {
            writer.write(String.join(",", updatedcsv));
            writer.newLine();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error Writing to File");
        e.printStackTrace();
        throw e; // rethrow to signal failure
    }
}

}
