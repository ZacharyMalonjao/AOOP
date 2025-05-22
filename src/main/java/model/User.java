/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zach malonjao
 */
public abstract class User {
    
    protected String employeeId;
    protected String lastName;
    protected String firstName;
    protected String birthday;
    protected String address;
    protected String phoneNumber;
    protected String sssNumber;
    protected String philhealthNumber;
    protected String tinNumber;
    protected String pagIbigNumber;
    protected String status;
    protected String position;
    protected String immediateSupervisor;
    protected double basicSalary;
    protected double riceSubsidy;
    protected double phoneAllowance;
    protected double clothingAllowance;
    protected double grossSemiMonthlyRate;
    protected double hourlyRate;
    
     public User(String employeeId, String lastName, String firstName, String birthday, String address,
                   String phoneNumber, String sssNumber, String philhealthNumber, String tinNumber,
                   String pagIbigNumber, String status, String position, String immediateSupervisor,
                   double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance,
                   double grossSemiMonthlyRate, double hourlyRate) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.tinNumber = tinNumber;
        this.pagIbigNumber = pagIbigNumber;
        this.status = status;
        this.position = position;
        this.immediateSupervisor = immediateSupervisor;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
    }
  
    public abstract void accessDashboard(User user);
    
    public abstract void accessSalaryCalculation(AccessParameters accessParameters);
    
    public abstract void accessLeave(AccessParameters accessParameters);
    
    
    public double[] CalculatePayroll(Employee emp,String year,  String month){
    PayrollCalculator tc = new PayrollCalculator(emp, year, month);
    tc.calculatePayroll();
    
    double[] payrollData = {tc.getGrossPay(), tc.getMonthlyRate(), tc.getDailyRate(), tc.getSssDeduct(), tc.getPhilHealthDeduct(), tc.getPagibigDeduct(), tc.getWithHoldingTax(), tc.getTotalBenefits(), tc.getTotalDeductions(), tc.getNetPay()};
    return payrollData;
    
    
    
        /*
      INDEX ACCESS GUIDE
        Earnings
            0  Gross Pay
            1  Monthly Rate
            2  Daily Rate
        Deductions
            3 SSS deduction
            4 PhilhHealth Deduction
            5 Pagibig Deduction
            6 WithHolding tax
        Summary
            7 Total Benefits
            8 Total Deductions
            9 Net Pay


        */
  }  
    
    
    
    
    
    
   public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSssNumber() {
        return sssNumber;
    }

    public void setSssNumber(String sssNumber) {
        this.sssNumber = sssNumber;
    }

    public String getPhilhealthNumber() {
        return philhealthNumber;
    }

    public void setPhilhealthNumber(String philhealthNumber) {
        this.philhealthNumber = philhealthNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public String getPagIbigNumber() {
        return pagIbigNumber;
    }

    public void setPagIbigNumber(String pagIbigNumber) {
        this.pagIbigNumber = pagIbigNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImmediateSupervisor() {
        return immediateSupervisor;
    }

    public void setImmediateSupervisor(String immediateSupervisor) {
        this.immediateSupervisor = immediateSupervisor;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

   

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

   

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

 

public double getGrossSemiMonthlyRate() {
    return grossSemiMonthlyRate;
}



public double getClothingAllowance() {
    return clothingAllowance;
}



public double getHourlyRate() {
    return hourlyRate;
}







}
