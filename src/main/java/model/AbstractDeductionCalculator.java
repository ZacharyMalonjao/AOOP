/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author zach malonjao
 */


//The purpose of this abstract class is to hide the lengthy calculation for 
//WithHolding Tax and Total Deduction


public abstract class AbstractDeductionCalculator {
    protected double taxableIncome;
    protected Employee emp;
    protected String info;
    protected long withHoldingTax;
    
    protected double basicSalary;
    protected double pagibigDeduct;
    protected double philHealthDeduct;
    protected double sssDeduct;
    protected double totalDeduction;
    
   AbstractDeductionCalculator(Employee emp){
        this.emp=emp;
       // basicSalary= emp.getBasicSalary();
        
    } 
   
 
    
    
    //WITHHOLDING TAX
    protected long calculateWithHoldingTax(double taxableIncome){
     if (taxableIncome < 20832) {
        info = "No withholding tax";
        withHoldingTax = 0;
    } else if (taxableIncome >= 20833 && taxableIncome <= 33333) {
        withHoldingTax = (long) (taxableIncome * 0.2);
        info = String.format("20%% in excess of 20,833: %.2f", taxableIncome - 20833);
    } else if (taxableIncome >= 33333 && taxableIncome <= 66667) {
        withHoldingTax = (long) (taxableIncome * 0.25 - 2500);
        info = String.format("2,500 plus 25%% in excess of 33,333: %.2f", taxableIncome - 33333);
    } else if (taxableIncome >= 66667 && taxableIncome <= 166667) {
        withHoldingTax = (long) (taxableIncome * 0.3 - 10833);
        info = String.format("10,833 plus 30%% in excess of 66,667: %.2f", taxableIncome - 66667);
    } else if (taxableIncome >= 166667 && taxableIncome <= 666667) {
        withHoldingTax = (long) (taxableIncome * 0.32 - 40833.33);
        info = String.format("40,833.33 plus 32%% in excess over 166,667: %.2f", taxableIncome - 166667);
    } else if (taxableIncome > 666667) {
        withHoldingTax = (long) (taxableIncome * 0.35 - 200833.33);
        info = String.format("200,833.33 plus 35%% in excess of 666,667: %.2f", taxableIncome - 666667);
    } else {
        System.out.println("Error");
        info = "";
    }
    
     return withHoldingTax;
    }
    
    
    
    protected String getInfo(){
        return info;
    }
    //TOTAL DEDUCTION
  protected double calculateTotalDeduction(double monthlyBasicSalary){
    pagibigDeduct = 100;  
    philHealthDeduct = monthlyBasicSalary * 0.03;
    
    if (monthlyBasicSalary < 3250) {
        sssDeduct = 135.00;
    } else if (monthlyBasicSalary >= 3250 && monthlyBasicSalary <= 3750) {
        sssDeduct = 157.00;
    } else if (monthlyBasicSalary >= 3750 && monthlyBasicSalary <= 4250) {
        sssDeduct = 180.00;
    } else if (monthlyBasicSalary >= 4250 && monthlyBasicSalary <= 4750) {
        sssDeduct = 202.50;
    } else if (monthlyBasicSalary >= 4750 && monthlyBasicSalary <= 5250) {
        sssDeduct = 225.00;
    } else if (monthlyBasicSalary >= 5250 && monthlyBasicSalary <= 5750) {
        sssDeduct = 247.50;
    } else if (monthlyBasicSalary >= 5750 && monthlyBasicSalary <= 6250) {
        sssDeduct = 270.00;
    } else if (monthlyBasicSalary >= 6250 && monthlyBasicSalary <= 6750) {
        sssDeduct = 292.50;
    } else if (monthlyBasicSalary >= 6750 && monthlyBasicSalary <= 7250) {
        sssDeduct = 315.00;
    } else if (monthlyBasicSalary >= 7250 && monthlyBasicSalary <= 7750) {
        sssDeduct = 337.50;
    } else if (monthlyBasicSalary >= 7750 && monthlyBasicSalary <= 8250) {
        sssDeduct = 360.00;
    } else if (monthlyBasicSalary >= 8250 && monthlyBasicSalary <= 8750) {
        sssDeduct = 382.50;
    } else if (monthlyBasicSalary >= 8750 && monthlyBasicSalary <= 9250) {
        sssDeduct = 405.00;
    } else if (monthlyBasicSalary >= 9250 && monthlyBasicSalary <= 9750) {
        sssDeduct = 427.50;
    } else if (monthlyBasicSalary >= 9750 && monthlyBasicSalary <= 10250) {
        sssDeduct = 450.00;
    } else if (monthlyBasicSalary >= 10250 && monthlyBasicSalary <= 10750) {
        sssDeduct = 472.50;
    } else if (monthlyBasicSalary >= 10750 && monthlyBasicSalary <= 11250) {
        sssDeduct = 495.00;
    } else if (monthlyBasicSalary >= 11250 && monthlyBasicSalary <= 11750) {
        sssDeduct = 517.50;
    } else if (monthlyBasicSalary >= 11750 && monthlyBasicSalary <= 12250) {
        sssDeduct = 540.00;
    } else if (monthlyBasicSalary >= 12250 && monthlyBasicSalary <= 12750) {
        sssDeduct = 562.50;
    } else if (monthlyBasicSalary >= 12750 && monthlyBasicSalary <= 13250) {
        sssDeduct = 585.00;
    } else if (monthlyBasicSalary >= 13250 && monthlyBasicSalary <= 13750) {
        sssDeduct = 607.50;
    } else if (monthlyBasicSalary >= 13750 && monthlyBasicSalary <= 14250) {
        sssDeduct = 630.00;
    } else if (monthlyBasicSalary >= 14250 && monthlyBasicSalary <= 14750) {
        sssDeduct = 652.50;
    } else if (monthlyBasicSalary >= 14750 && monthlyBasicSalary <= 15250) {
        sssDeduct = 675.00;
    } else if (monthlyBasicSalary >= 15250 && monthlyBasicSalary <= 15750) {
        sssDeduct = 697.50;
    } else if (monthlyBasicSalary >= 15750 && monthlyBasicSalary <= 16250) {
        sssDeduct = 720.00;
    } else if (monthlyBasicSalary >= 16250 && monthlyBasicSalary <= 16750) {
        sssDeduct = 742.50;
    } else if (monthlyBasicSalary >= 16750 && monthlyBasicSalary <= 17250) {
        sssDeduct = 765.00;
    } else if (monthlyBasicSalary >= 17250 && monthlyBasicSalary <= 17750) {
        sssDeduct = 787.50;
    } else if (monthlyBasicSalary >= 17750 && monthlyBasicSalary <= 18250) {
        sssDeduct = 810.00;
    } else if (monthlyBasicSalary >= 18250 && monthlyBasicSalary <= 18750) {
        sssDeduct = 832.50;
    } else if (monthlyBasicSalary >= 18750 && monthlyBasicSalary <= 19250) {
        sssDeduct = 855.00;
    } else if (monthlyBasicSalary >= 19250 && monthlyBasicSalary <= 19750) {
        sssDeduct = 877.50;
    } else if (monthlyBasicSalary >= 19750 && monthlyBasicSalary <= 20250) {
        sssDeduct = 900.00;
    } else if (monthlyBasicSalary >= 20250 && monthlyBasicSalary <= 20750) {
        sssDeduct = 922.50;
    } else if (monthlyBasicSalary >= 20750 && monthlyBasicSalary <= 21750) {
        sssDeduct = 967.50;
    } else if (monthlyBasicSalary >= 21750 && monthlyBasicSalary <= 22250) {
        sssDeduct = 990.00;
    } else if (monthlyBasicSalary >= 22250 && monthlyBasicSalary <= 22750) {
        sssDeduct = 1012.50;
    } else if (monthlyBasicSalary >= 22750 && monthlyBasicSalary <= 23250) {
        sssDeduct = 1035.00;
    } else if (monthlyBasicSalary >= 23250 && monthlyBasicSalary <= 23750) {
        sssDeduct = 1057.50;
    } else if (monthlyBasicSalary >= 23750 && monthlyBasicSalary <= 24250) {
        sssDeduct = 1080.00;
    } else if (monthlyBasicSalary >= 24250 && monthlyBasicSalary <= 24750) {
        sssDeduct = 1102.50;
    } else if (monthlyBasicSalary > 24750) { 
        sssDeduct = 1125.50;
    } else {
        System.out.println("Error");
    }
    
    totalDeduction = sssDeduct + philHealthDeduct + pagibigDeduct;
    return totalDeduction;
}

    
    
    
  
    
    public double getAllowances (Employee emp){
        double allAllowances = emp.getClothingAllowance()+emp.getRiceSubsidy()+emp.getPhoneAllowance();
        return allAllowances;
    }
    
    
   
         public static int calculateWorkingDays(String year, String month) {
        // Map of month names to Calendar month constants
        Map<String, Integer> monthMap = new HashMap<>();
        monthMap.put("January", Calendar.JANUARY);
        monthMap.put("February", Calendar.FEBRUARY);
        monthMap.put("March", Calendar.MARCH);
        monthMap.put("April", Calendar.APRIL);
        monthMap.put("May", Calendar.MAY);
        monthMap.put("June", Calendar.JUNE);
        monthMap.put("July", Calendar.JULY);
        monthMap.put("August", Calendar.AUGUST);
        monthMap.put("September", Calendar.SEPTEMBER);
        monthMap.put("October", Calendar.OCTOBER);
        monthMap.put("November", Calendar.NOVEMBER);
        monthMap.put("December", Calendar.DECEMBER);

        // Parse the year
        int yearInt;
        try {
            yearInt = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid year format. Please enter a valid year.");
        }

        // Get the month index from the map
        Integer monthIndex = monthMap.get(month);
        if (monthIndex == null) {
            throw new IllegalArgumentException("Invalid month name. Please enter a valid month.");
        }

        // Create a Calendar instance
        Calendar calendar = Calendar.getInstance();
        calendar.set(yearInt, monthIndex, 1); // Set to the first day of the month

        // Get the number of days in the month
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int workingDaysCount = 0;

        // Iterate through each day of the month
        for (int day = 1; day <= daysInMonth; day++) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            // Check if the day is a weekday (Monday to Friday)
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workingDaysCount++;
            }
        }

        return workingDaysCount;
    }
   
    
}
