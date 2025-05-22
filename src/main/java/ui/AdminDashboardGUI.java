/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import model.AccessParameters;
import model.Admin;
import model.Employee;
import model.LoginValidator;
import model.StatusCellRenderer;
import model.UnidentifiedUser;
import java.awt.Color;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminDashboardGUI extends javax.swing.JFrame {
    Admin admin;
    private List<String[]> leaveDatabase;
    private List<String[]> employeeDatabase;
    private DefaultTableModel model;
    private DefaultTableModel databaseModel;
    private Employee employeeToCompute;

    /**
     * Creates new form AdminDashboard
     */
    public AdminDashboardGUI(Admin admin) {
        initComponents();
        this.admin=admin;

        jTextDashboardEmpNo.setText(admin.getEmployeeId());
        jTextDashboardFirstName.setText(admin.getFirstName());
        jTextDashboardLastName.setText(admin.getLastName());
        jAreaDashboardAddress.setText(admin.getAddress());
        jTextDashboardBirthday.setText(admin.getBirthday());
        jTextDashboardPhoneNo.setText(admin.getPhoneNumber());
        jTextDashboardStatus.setText(admin.getStatus());
        jTextDashboardPosition.setText(admin.getPosition());
        jTextDashboardSupervisor.setText(admin.getImmediateSupervisor());
        jTextDashboardSssNum.setText(admin.getSssNumber());
        jTextDashboardPhilhealthNum.setText(admin.getPhilhealthNumber());
        jTextDashboardPagibigNum.setText(admin.getPagIbigNumber());
        jTextDashboardTinNum.setText(admin.getTinNumber());
        jTextDashboardRiceSubsidy.setText(Double.toString(admin.getRiceSubsidy()));
        jTextDashboardPhoneAllowance.setText(Double.toString(admin.getPhoneAllowance()));
        jTextDashboardClothingAllowance.setText(Double.toString(admin.getClothingAllowance()));
        jTextDashboardHourlyRate.setText(Double.toString(admin.getHourlyRate()));
        jTextDashboardGrossSemiMonthlyRate.setText(Double.toString(admin.getGrossSemiMonthlyRate()));
        jTextDashboardBasicSalary.setText(Double.toString(admin.getBasicSalary()));
        
        
        
        
       
    }
    
    private void populateLeaveTable(String file) {
        leaveDatabase = admin.loadCSV(file);
        String[] columnNames = {"Request #", "ID", "First Name","Last Name","Position","Leave Type","Start Date","End Date","Status"};
        
         model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // makes cells uneditable
            }
        };

        // Add existing data to the model
        for (String[] row : leaveDatabase) {
            model.addRow(row);
        }

        jTableLeaveManagement.setModel(model);
        // jTableLeaveManagement.getColumnModel().getColumn(8).setCellRenderer(new StatusCellRenderer());
        jTableLeaveManagement.setDefaultRenderer(model.getColumnClass(8), new StatusCellRenderer());
        // jTableLeaveManagement.setDefaultRenderer(String.class, new StatusCellRenderer());
    }

    private void populateDatabaseTable(String file) {
        employeeDatabase = admin.loadCSV(file);
        String[] columnNames = {"ID", "Last Name", "First Name", "Birthday", "Address", "Phone Number", "SSS #", "Philhealth #", "TIN #", "Pag-ibig #", "Status", 
           "Position", "Immediate Supervisor", "Basic Salary", "Rice Subsidy", "Phone Allowance", "Clothing Allowance", "Gross Semi-monthly Rate", "Hourly Rate"};

        databaseModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // makes cells uneditable
            }
        };

        // Add existing data to the model
        for (String[] row : employeeDatabase) {
            databaseModel.addRow(row);
        }

        jTableDatabase.setModel(databaseModel);
    }
    //Used in Admin Salary Page to search ID and return an Object
    private Employee setEmployeeData(String employeeID){
    //Search user data and store in Array
        employeeDatabase = admin.loadCSV("MotorPH.csv");
        String[] userData=admin.searchUserData(employeeDatabase, employeeID);
        //Populate the array into an employee object
        if (userData == null) {
            JOptionPane.showMessageDialog(jButtonSearchID, "User with that ID does not exist");
            jTextSalaryEmpNo.setText("");
            jTextSalaryLastName.setText("");
            jTextSalaryFirstName.setText("");
            jAreaSalaryAddress.setText("");
            jTextSalaryPhoneNo.setText("");
            jTextSalaryBirthday.setText("");
            jTextSalaryStatus.setText("");
            jTextSalarySupervisor.setText("");
            jTextSalaryPosition.setText("");
            jTextSalarySssNum.setText("");
            jTextSalaryTin.setText("");
            jTextSalaryPagibigNum.setText("");
            jTextFieldBasicSalary.setText("");
            jTextFieldGrossSemiMonthlyRate.setText("");
            jTextFieldHourlyRate.setText("");
            jTextFieldRiceSubsidy.setText("");
            jTextFieldPhoneAllowance.setText("");
            jTextFieldClothingAllowance.setText("");
            jTextSalaryPhilhealthNum.setText("");
            jTextFieldSSSContribution.setText("");
            jTextFieldPhilHealthContribution.setText("");
            jTextFieldPagIBIGContribution.setText("");
            jTextFieldGrossPay.setText("");
            jTextFieldNetPay.setText("");
            jButtonComputeSalary.setEnabled(false);
            
            throw new IllegalArgumentException("User data not found for index: " + employeeID);
        }else{
            Employee emp = new Employee(
            userData[0], // employeeId
            userData[1], // lastName
            userData[2], // firstName
            userData[3], // birthday
            userData[4], // address
            userData[5], // phoneNumber
            userData[6], // sssNumber
            userData[7], // philhealthNumber
            userData[8], // tinNumber
            userData[9], // pagIbigNumber
            userData[10], // status
            userData[11], // position
            userData[12], // immediateSupervisor
            Double.parseDouble(userData[13]), // basicSalary
            Double.parseDouble(userData[14]), // riceSubsidy
            Double.parseDouble(userData[15]), // phoneAllowance
            Double.parseDouble(userData[16]), // clothingAllowance
            Double.parseDouble(userData[17]), // grossSemiMonthlyRate
            Double.parseDouble(userData[18]) // hourlyRate
        );
        return emp;
    }


    }



    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createRecord = new javax.swing.JFrame();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldEmpNum1 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jTextFieldFirstName2 = new javax.swing.JTextField();
        jTextFieldLastName1 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jCalendarBirthday = new com.toedter.calendar.JCalendar();
        jLabel68 = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jTextFieldPhoneNum1 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jComboBoxSupervisor = new javax.swing.JComboBox<>();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jComboBoxPosition = new javax.swing.JComboBox<>();
        jLabel97 = new javax.swing.JLabel();
        jTextFieldClothingAllowance1 = new javax.swing.JTextField();
        jTextFieldRiceSubsidy1 = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jTextFieldPhoneAllowance1 = new javax.swing.JTextField();
        jTextFieldBasicSalary1 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jTextFieldPagibigNum = new javax.swing.JTextField();
        jTextFieldTINNum = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jTextFieldPhilHealthNum = new javax.swing.JTextField();
        jTextFieldSSSNum = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        saveCreateButton = new javax.swing.JButton();
        cancelCreateButton = new javax.swing.JButton();
        editRecord = new javax.swing.JFrame();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jTextFieldEditEmpNum = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jTextFieldEditFirstName = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jTextFieldEditLastName = new javax.swing.JTextField();
        jCalendarBirthday2 = new com.toedter.calendar.JCalendar();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jTextFieldEditAddress = new javax.swing.JTextField();
        jTextFieldEditPhoneNum = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jComboBoxEditSupervisor = new javax.swing.JComboBox<>();
        jComboBoxEditStatus = new javax.swing.JComboBox<>();
        jComboBoxEditPosition = new javax.swing.JComboBox<>();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jTextFieldEditSSSNum = new javax.swing.JTextField();
        jTextFieldEditPhilHealthNum = new javax.swing.JTextField();
        jTextFieldEditTINNum = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jTextFieldEditPagibigNum = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jTextFieldEditPhoneAllowance = new javax.swing.JTextField();
        jLabel122 = new javax.swing.JLabel();
        jTextFieldEditRiceSubsidy = new javax.swing.JTextField();
        jTextFieldEditClothingAllowance = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jTextFieldEditBasicSalary = new javax.swing.JTextField();
        jTextFieldEditGrossSemiMonthly = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jTextFieldEditHourlyRateAllowance = new javax.swing.JTextField();
        cancelEditButton = new javax.swing.JButton();
        jLabel127 = new javax.swing.JLabel();
        saveEditButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        adminDashboardMenu = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        adminSalaryCalculationMenu = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        adminLeaveMenu = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButtonLogout = new javax.swing.JButton();
        adminDatabaseMenu = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanelParent = new javax.swing.JPanel();
        adminDashboard = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTextDashboardHourlyRate = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jTextDashboardGrossSemiMonthlyRate = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jTextDashboardBasicSalary = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jTextDashboardStatus = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jTextDashboardSupervisor = new javax.swing.JTextField();
        jTextDashboardPosition = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jTextDashboardPhoneNo = new javax.swing.JTextField();
        jTextDashboardBirthday = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jAreaDashboardAddress = new javax.swing.JTextArea();
        jTextDashboardLastName = new javax.swing.JTextField();
        jTextDashboardFirstName = new javax.swing.JTextField();
        jTextDashboardEmpNo = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jTextDashboardRiceSubsidy = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jTextDashboardPhoneAllowance = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jTextDashboardClothingAllowance = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jTextDashboardTinNum = new javax.swing.JTextField();
        jTextDashboardPagibigNum = new javax.swing.JTextField();
        jTextDashboardPhilhealthNum = new javax.swing.JTextField();
        jTextDashboardSssNum = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        adminSalaryCalculation = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextSalaryStatus = new javax.swing.JTextField();
        jTextSalaryPosition = new javax.swing.JTextField();
        jTextSalarySupervisor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextSalarySssNum = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextSalaryPagibigNum = new javax.swing.JTextField();
        jTextSalaryTin = new javax.swing.JTextField();
        jTextSalaryPhilhealthNum = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextSalaryBirthday = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextSalaryPhoneNo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextSalaryEmpNo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextSalaryLastName = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextSalaryFirstName = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jAreaSalaryAddress = new javax.swing.JTextArea();
        jLabel44 = new javax.swing.JLabel();
        jComboBoxYear = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldBasicSalary = new javax.swing.JTextField();
        jTextFieldGrossSemiMonthlyRate = new javax.swing.JTextField();
        jTextFieldHourlyRate = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldSSSContribution = new javax.swing.JTextField();
        jTextFieldPhilHealthContribution = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldPagIBIGContribution = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldGrossPay = new javax.swing.JTextField();
        jTextFieldNetPay = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextFieldRiceSubsidy = new javax.swing.JTextField();
        jTextFieldPhoneAllowance = new javax.swing.JTextField();
        jTextFieldClothingAllowance = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButtonComputeSalary = new javax.swing.JButton();
        jTextFieldIDtoSearch = new javax.swing.JTextField();
        jButtonSearchID = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        jComboBoxMonth = new javax.swing.JComboBox<>();
        adminDatabase = new javax.swing.JPanel();
        jTextFieldEmpNum = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextFieldLastName = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextFieldFirstName = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextFieldPhoneNum = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextFieldSupervisor = new javax.swing.JTextField();
        jTextFieldPosition = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        createButton = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jAreaSalaryAddress1 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableDatabase = new javax.swing.JTable();
        adminLeaveManagement = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableLeaveManagement = new javax.swing.JTable();
        jButtonApprove = new javax.swing.JButton();
        jButtonReject1 = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();

        createRecord.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        createRecord.setTitle("Create Employee Record");
        createRecord.setAlwaysOnTop(true);
        createRecord.setBackground(new java.awt.Color(51, 51, 51));
        createRecord.setLocation(new java.awt.Point(0, 0));
        createRecord.setSize(new java.awt.Dimension(750, 620));

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setPreferredSize(new java.awt.Dimension(748, 618));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldEmpNum1.setEditable(false);
        jTextFieldEmpNum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmpNum1ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextFieldEmpNum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 80, -1));

        jLabel67.setBackground(new java.awt.Color(255, 255, 255));
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Employee No. :");
        jPanel4.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 110, 20));

        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("First Name :");
        jPanel4.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 70, 20));

        jTextFieldFirstName2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFirstName2ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextFieldFirstName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 200, -1));
        jPanel4.add(jTextFieldLastName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 200, -1));

        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Last Name :");
        jPanel4.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 80, 20));

        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Birthday :");
        jPanel4.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 80, 20));

        jCalendarBirthday.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jPanel4.add(jCalendarBirthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 200, 140));

        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText(" Address :");
        jPanel4.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 70, 20));
        jPanel4.add(jTextFieldAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 200, 20));
        jPanel4.add(jTextFieldPhoneNum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 200, -1));

        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Phone No. :");
        jPanel4.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 90, 20));

        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setText("Position :");
        jPanel4.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 70, 20));

        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setText("Status :");
        jPanel4.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 70, 20));

        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
        jLabel113.setText("Supervisor :");
        jPanel4.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 100, 20));

        jComboBoxSupervisor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Garcia Manuel III", "Lim Antonio", "Aquino Bianca Sofia", "Reyes Isabella", "Alvaro Roderick", "Villanueva Andrea Mae", "Romualdez Fredrick", "Salcedo Anthony", "De Leon Selena", "Mata Christian", "San Jose Brad" }));
        jPanel4.add(jComboBoxSupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 200, -1));

        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular", "Probationary", " " }));
        jComboBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStatusActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBoxStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 200, -1));

        jComboBoxPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chief Executive Officer", "Chief Operating Officer", "Chief Finance Officer", "Chief Marketing Officer", "Account Manager", "IT Operations and Systems", "HR Manager", "Accounting Head", "Sales & Marketing", "Supply Chain and Logistics", "Customer Service and Relations", "Payroll Manager", "HR Team Leader", "Account Team Leader", "Payroll Team Leader", "Account Rank and File", "Payroll Rank and File", "HR Rank and File" }));
        jComboBoxPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPositionActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBoxPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 200, -1));

        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("Clothing Allowance :");
        jPanel4.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, -1, 20));

        jTextFieldClothingAllowance1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClothingAllowance1ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextFieldClothingAllowance1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 180, -1));
        jPanel4.add(jTextFieldRiceSubsidy1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 180, -1));

        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText("Rice Subsidy :");
        jPanel4.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, -1, 20));

        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("Phone Allowance :");
        jPanel4.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, -1, 20));
        jPanel4.add(jTextFieldPhoneAllowance1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, 180, -1));
        jPanel4.add(jTextFieldBasicSalary1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 180, -1));

        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("Basic Salary :");
        jPanel4.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 90, 20));

        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Pag-IBIG No. :");
        jPanel4.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 100, 20));
        jPanel4.add(jTextFieldPagibigNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 180, -1));
        jPanel4.add(jTextFieldTINNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 180, -1));

        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("TIN :");
        jPanel4.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 70, 20));

        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("PhilHealth No. :");
        jPanel4.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 100, 20));
        jPanel4.add(jTextFieldPhilHealthNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 180, -1));
        jPanel4.add(jTextFieldSSSNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 180, -1));

        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("SSS No. :");
        jPanel4.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 90, 20));

        jLabel114.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 255, 255));
        jLabel114.setText("Create Employee Record");
        jPanel4.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 260, 30));

        jLabel101.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(204, 204, 204));
        jLabel101.setText("Hourly Rate and Gross Semi-monthly will");
        jPanel4.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, 240, 20));

        jLabel100.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(204, 204, 204));
        jLabel100.setText(" automatically be calculated based on Basic Salary");
        jPanel4.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 470, 270, 20));

        saveCreateButton.setBackground(new java.awt.Color(51, 51, 51));
        saveCreateButton.setForeground(new java.awt.Color(255, 255, 255));
        saveCreateButton.setText("Save");
        saveCreateButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        saveCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveCreateButtonActionPerformed(evt);
            }
        });
        jPanel4.add(saveCreateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 90, 30));

        cancelCreateButton.setBackground(new java.awt.Color(51, 51, 51));
        cancelCreateButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelCreateButton.setText("Cancel");
        cancelCreateButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancelCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelCreateButtonActionPerformed(evt);
            }
        });
        jPanel4.add(cancelCreateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 30));

        jScrollPane7.setViewportView(jPanel4);

        createRecord.getContentPane().add(jScrollPane7, java.awt.BorderLayout.CENTER);

        editRecord.setTitle("Edit Employee Record");
        editRecord.setAlwaysOnTop(true);
        editRecord.setBackground(new java.awt.Color(51, 51, 51));
        editRecord.setSize(new java.awt.Dimension(750, 620));

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel88.setBackground(new java.awt.Color(255, 255, 255));
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Employee No. :");
        jPanel5.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, 20));

        jTextFieldEditEmpNum.setEditable(false);
        jTextFieldEditEmpNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEditEmpNumActionPerformed(evt);
            }
        });
        jPanel5.add(jTextFieldEditEmpNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 80, -1));

        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("First Name :");
        jPanel5.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 70, 20));

        jTextFieldEditFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEditFirstNameActionPerformed(evt);
            }
        });
        jPanel5.add(jTextFieldEditFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 210, -1));

        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Last Name :");
        jPanel5.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 80, 20));

        jTextFieldEditLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEditLastNameActionPerformed(evt);
            }
        });
        jPanel5.add(jTextFieldEditLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 210, -1));

        jCalendarBirthday2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jPanel5.add(jCalendarBirthday2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 210, 140));

        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Birthday :");
        jPanel5.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 80, 20));

        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText(" Address :");
        jPanel5.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 70, 20));
        jPanel5.add(jTextFieldEditAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 210, 20));

        jTextFieldEditPhoneNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEditPhoneNumActionPerformed(evt);
            }
        });
        jPanel5.add(jTextFieldEditPhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 210, -1));

        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Phone No. :");
        jPanel5.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 90, 20));

        jLabel118.setForeground(new java.awt.Color(255, 255, 255));
        jLabel118.setText("Position :");
        jPanel5.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 70, 20));

        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setText("Status :");
        jPanel5.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 70, 20));

        jLabel120.setForeground(new java.awt.Color(255, 255, 255));
        jLabel120.setText("Supervisor :");
        jPanel5.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 100, 20));

        jComboBoxEditSupervisor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Garcia Manuel III", "Lim Antonio", "Aquino Bianca Sofia", "Reyes Isabella", "Alvaro Roderick", "Villanueva Andrea Mae", "Romualdez Fredrick", "Salcedo Anthony", "De Leon Selena", "Mata Christian", "San Jose Brad" }));
        jPanel5.add(jComboBoxEditSupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 210, -1));

        jComboBoxEditStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular", "Probationary", " " }));
        jComboBoxEditStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEditStatusActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBoxEditStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 210, -1));

        jComboBoxEditPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chief Executive Officer", "Chief Operating Officer", "Chief Finance Officer", "Chief Marketing Officer", "Account Manager", "IT Operations and Systems", "HR Manager", "Accounting Head", "Sales & Marketing", "Supply Chain and Logistics", "Customer Service and Relations", "Payroll Manager", "HR Team Leader", "Account Team Leader", "Payroll Team Leader", "Account Rank and File", "Payroll Rank and File", "HR Rank and File" }));
        jComboBoxEditPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEditPositionActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBoxEditPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 210, -1));

        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("SSS No. :");
        jPanel5.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 90, 20));

        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setText("PhilHealth No. :");
        jPanel5.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 100, 20));
        jPanel5.add(jTextFieldEditSSSNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 180, -1));
        jPanel5.add(jTextFieldEditPhilHealthNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 180, -1));
        jPanel5.add(jTextFieldEditTINNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 180, -1));

        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("TIN :");
        jPanel5.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 70, 20));

        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setText("Pag-IBIG No. :");
        jPanel5.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 100, 20));
        jPanel5.add(jTextFieldEditPagibigNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 180, -1));

        jLabel121.setForeground(new java.awt.Color(255, 255, 255));
        jLabel121.setText("Phone Allowance :");
        jPanel5.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, -1, 20));
        jPanel5.add(jTextFieldEditPhoneAllowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 180, -1));

        jLabel122.setForeground(new java.awt.Color(255, 255, 255));
        jLabel122.setText("Rice Subsidy :");
        jPanel5.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, -1, 20));
        jPanel5.add(jTextFieldEditRiceSubsidy, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, 180, -1));

        jTextFieldEditClothingAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEditClothingAllowanceActionPerformed(evt);
            }
        });
        jPanel5.add(jTextFieldEditClothingAllowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 180, -1));

        jLabel123.setForeground(new java.awt.Color(255, 255, 255));
        jLabel123.setText("Clothing Allowance :");
        jPanel5.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, -1, 20));

        jLabel124.setForeground(new java.awt.Color(255, 255, 255));
        jLabel124.setText("Basic Salary :");
        jPanel5.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 400, 80, 20));
        jPanel5.add(jTextFieldEditBasicSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 400, 180, -1));

        jTextFieldEditGrossSemiMonthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEditGrossSemiMonthlyActionPerformed(evt);
            }
        });
        jPanel5.add(jTextFieldEditGrossSemiMonthly, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, 180, -1));

        jLabel125.setForeground(new java.awt.Color(255, 255, 255));
        jLabel125.setText("Gross Semi-monthly :");
        jPanel5.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, -1, 20));

        jLabel126.setForeground(new java.awt.Color(255, 255, 255));
        jLabel126.setText("Hourly Rate :");
        jPanel5.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, -1, 20));

        jTextFieldEditHourlyRateAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEditHourlyRateAllowanceActionPerformed(evt);
            }
        });
        jPanel5.add(jTextFieldEditHourlyRateAllowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 460, 180, -1));

        cancelEditButton.setBackground(new java.awt.Color(51, 51, 51));
        cancelEditButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelEditButton.setText("Cancel");
        cancelEditButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancelEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelEditButtonActionPerformed(evt);
            }
        });
        jPanel5.add(cancelEditButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 30));

        jLabel127.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(255, 255, 255));
        jLabel127.setText("Edit Employee Record");
        jPanel5.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 230, 30));

        saveEditButton.setBackground(new java.awt.Color(51, 51, 51));
        saveEditButton.setForeground(new java.awt.Color(255, 255, 255));
        saveEditButton.setText("Save");
        saveEditButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        saveEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveEditButtonActionPerformed(evt);
            }
        });
        jPanel5.add(saveEditButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 90, 30));

        jScrollPane8.setViewportView(jPanel5);

        editRecord.getContentPane().add(jScrollPane8, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(1500, 750));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 3, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MotorPH");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 130, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Payroll System");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 70));

        jPanel3.setBackground(new java.awt.Color(153, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adminDashboardMenu.setBackground(new java.awt.Color(51, 51, 51));
        adminDashboardMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)));
        adminDashboardMenu.setForeground(new java.awt.Color(51, 51, 51));
        adminDashboardMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminDashboardMenuMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dashboard");
        jLabel3.setPreferredSize(new java.awt.Dimension(406, 320));

        javax.swing.GroupLayout adminDashboardMenuLayout = new javax.swing.GroupLayout(adminDashboardMenu);
        adminDashboardMenu.setLayout(adminDashboardMenuLayout);
        adminDashboardMenuLayout.setHorizontalGroup(
            adminDashboardMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminDashboardMenuLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        adminDashboardMenuLayout.setVerticalGroup(
            adminDashboardMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminDashboardMenuLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel3.add(adminDashboardMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 180, 60));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 14));

        adminSalaryCalculationMenu.setBackground(new java.awt.Color(153, 0, 0));
        adminSalaryCalculationMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)));
        adminSalaryCalculationMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminSalaryCalculationMenuMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Salary Calculation");

        javax.swing.GroupLayout adminSalaryCalculationMenuLayout = new javax.swing.GroupLayout(adminSalaryCalculationMenu);
        adminSalaryCalculationMenu.setLayout(adminSalaryCalculationMenuLayout);
        adminSalaryCalculationMenuLayout.setHorizontalGroup(
            adminSalaryCalculationMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminSalaryCalculationMenuLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        adminSalaryCalculationMenuLayout.setVerticalGroup(
            adminSalaryCalculationMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminSalaryCalculationMenuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.add(adminSalaryCalculationMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 180, 60));

        adminLeaveMenu.setBackground(new java.awt.Color(153, 0, 0));
        adminLeaveMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)));
        adminLeaveMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminLeaveMenuMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Leave Management");

        javax.swing.GroupLayout adminLeaveMenuLayout = new javax.swing.GroupLayout(adminLeaveMenu);
        adminLeaveMenu.setLayout(adminLeaveMenuLayout);
        adminLeaveMenuLayout.setHorizontalGroup(
            adminLeaveMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLeaveMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        adminLeaveMenuLayout.setVerticalGroup(
            adminLeaveMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLeaveMenuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.add(adminLeaveMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 180, -1));

        jButtonLogout.setBackground(new java.awt.Color(102, 0, 0));
        jButtonLogout.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogout.setText("Logout");
        jButtonLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 120, 30));

        adminDatabaseMenu.setBackground(new java.awt.Color(153, 0, 0));
        adminDatabaseMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)));
        adminDatabaseMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminDatabaseMenuMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Database");

        javax.swing.GroupLayout adminDatabaseMenuLayout = new javax.swing.GroupLayout(adminDatabaseMenu);
        adminDatabaseMenu.setLayout(adminDatabaseMenuLayout);
        adminDatabaseMenuLayout.setHorizontalGroup(
            adminDatabaseMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminDatabaseMenuLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        adminDatabaseMenuLayout.setVerticalGroup(
            adminDatabaseMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminDatabaseMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.add(adminDatabaseMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 180, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 180, 680));

        jPanelParent.setBackground(new java.awt.Color(51, 51, 51));
        jPanelParent.setLayout(new java.awt.CardLayout());

        adminDashboard.setBackground(new java.awt.Color(51, 51, 51));
        adminDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextDashboardHourlyRate.setEditable(false);
        jPanel9.add(jTextDashboardHourlyRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 190, -1));

        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Hourly Rate :");
        jPanel9.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 20));

        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Gross Semi-monthly Rate : ");
        jPanel9.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 20));

        jTextDashboardGrossSemiMonthlyRate.setEditable(false);
        jPanel9.add(jTextDashboardGrossSemiMonthlyRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 190, -1));

        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Basic Salary :");
        jPanel9.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 20));

        jTextDashboardBasicSalary.setEditable(false);
        jPanel9.add(jTextDashboardBasicSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 190, -1));

        adminDashboard.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 460, 435, 170));

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Position : ");
        jPanel10.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, 20));

        jTextDashboardStatus.setEditable(false);
        jPanel10.add(jTextDashboardStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 190, -1));

        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Status :");
        jPanel10.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, 20));

        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Immediate Supervisor : ");
        jPanel10.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, 20));

        jTextDashboardSupervisor.setEditable(false);
        jPanel10.add(jTextDashboardSupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 190, -1));

        jTextDashboardPosition.setEditable(false);
        jPanel10.add(jTextDashboardPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 190, -1));

        adminDashboard.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 455, 179));

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Employee No. :");
        jPanel11.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 20));

        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("First Name :");
        jPanel11.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 20));

        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Last Name :");
        jPanel11.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, 20));

        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Address :");
        jPanel11.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, 20));

        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Birthday :");
        jPanel11.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, 20));

        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Phone No. :");
        jPanel11.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, 20));

        jTextDashboardPhoneNo.setEditable(false);
        jPanel11.add(jTextDashboardPhoneNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 120, -1));

        jTextDashboardBirthday.setEditable(false);
        jPanel11.add(jTextDashboardBirthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 120, -1));

        jAreaDashboardAddress.setEditable(false);
        jAreaDashboardAddress.setColumns(20);
        jAreaDashboardAddress.setLineWrap(true);
        jAreaDashboardAddress.setRows(5);
        jScrollPane2.setViewportView(jAreaDashboardAddress);

        jPanel11.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 200, 50));

        jTextDashboardLastName.setEditable(false);
        jPanel11.add(jTextDashboardLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 120, -1));

        jTextDashboardFirstName.setEditable(false);
        jPanel11.add(jTextDashboardFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 120, -1));

        jTextDashboardEmpNo.setEditable(false);
        jPanel11.add(jTextDashboardEmpNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 60, -1));

        adminDashboard.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 455, 363));

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextDashboardRiceSubsidy.setEditable(false);
        jPanel12.add(jTextDashboardRiceSubsidy, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 190, -1));

        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Rice Subsidy :");
        jPanel12.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 20));

        jTextDashboardPhoneAllowance.setEditable(false);
        jPanel12.add(jTextDashboardPhoneAllowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 190, -1));

        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Phone Allowance :");
        jPanel12.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 20));

        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Clothing Allowance :");
        jPanel12.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 20));

        jTextDashboardClothingAllowance.setEditable(false);
        jPanel12.add(jTextDashboardClothingAllowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 190, -1));

        adminDashboard.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 435, 173));

        jPanel13.setBackground(new java.awt.Color(51, 51, 51));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextDashboardTinNum.setEditable(false);
        jPanel13.add(jTextDashboardTinNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 190, -1));

        jTextDashboardPagibigNum.setEditable(false);
        jPanel13.add(jTextDashboardPagibigNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 190, -1));

        jTextDashboardPhilhealthNum.setEditable(false);
        jPanel13.add(jTextDashboardPhilhealthNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 190, -1));

        jTextDashboardSssNum.setEditable(false);
        jPanel13.add(jTextDashboardSssNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 190, -1));

        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("SSS No. :");
        jPanel13.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 20));

        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("PhilHealth No. :");
        jPanel13.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, 20));

        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("PagIBIG No. :");
        jPanel13.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, 20));

        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("TIN :");
        jPanel13.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, 20));

        adminDashboard.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 435, 180));

        jLabel45.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Admin Dashboard");
        adminDashboard.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 190, 40));

        jPanelParent.add(adminDashboard, "card2");

        adminSalaryCalculation.setBackground(new java.awt.Color(51, 51, 51));
        adminSalaryCalculation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setBackground(new java.awt.Color(51, 51, 51));
        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Status :");
        jPanel19.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 60, 20));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Position :");
        jPanel19.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 90, 20));

        jTextSalaryStatus.setEditable(false);
        jPanel19.add(jTextSalaryStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 170, -1));

        jTextSalaryPosition.setEditable(false);
        jPanel19.add(jTextSalaryPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 170, -1));

        jTextSalarySupervisor.setEditable(false);
        jPanel19.add(jTextSalarySupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 170, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("SSS No. :");
        jPanel19.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 60, 20));

        jTextSalarySssNum.setEditable(false);
        jPanel19.add(jTextSalarySssNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 140, -1));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("PhilHealth No. :");
        jPanel19.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 90, 20));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("TIN :");
        jPanel19.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 60, 20));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Pag-IBIG No. :");
        jPanel19.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 110, 90, 20));

        jTextSalaryPagibigNum.setEditable(false);
        jPanel19.add(jTextSalaryPagibigNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 110, 140, -1));

        jTextSalaryTin.setEditable(false);
        jPanel19.add(jTextSalaryTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 80, 140, -1));

        jTextSalaryPhilhealthNum.setEditable(false);
        jPanel19.add(jTextSalaryPhilhealthNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, 140, -1));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Immediate Supervisor :");
        jPanel19.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 130, 20));

        jTextSalaryBirthday.setEditable(false);
        jPanel19.add(jTextSalaryBirthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 170, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Birthday :");
        jPanel19.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, -1));

        jTextSalaryPhoneNo.setEditable(false);
        jPanel19.add(jTextSalaryPhoneNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 170, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Phone No. :");
        jPanel19.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 100, 20));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Employee No. : ");
        jPanel19.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, 20));

        jTextSalaryEmpNo.setEditable(false);
        jTextSalaryEmpNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextSalaryEmpNoActionPerformed(evt);
            }
        });
        jPanel19.add(jTextSalaryEmpNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 50, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Last Name : ");
        jPanel19.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 20));

        jTextSalaryLastName.setEditable(false);
        jPanel19.add(jTextSalaryLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 210, -1));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("First Name :");
        jPanel19.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 20));

        jTextSalaryFirstName.setEditable(false);
        jPanel19.add(jTextSalaryFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 210, -1));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Address :");
        jPanel19.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 60, 20));

        jAreaSalaryAddress.setEditable(false);
        jAreaSalaryAddress.setColumns(20);
        jAreaSalaryAddress.setLineWrap(true);
        jAreaSalaryAddress.setRows(5);
        jScrollPane3.setViewportView(jAreaSalaryAddress);

        jPanel19.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 210, 50));

        adminSalaryCalculation.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1000, 180));

        jLabel44.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Admin Salary Calculation");
        adminSalaryCalculation.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 260, 40));

        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022", "2023", "2024", "2025" }));
        jComboBoxYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxYearActionPerformed(evt);
            }
        });
        adminSalaryCalculation.add(jComboBoxYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 280, 100, 40));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Select Year");
        adminSalaryCalculation.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, 100, 20));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Earnings");
        adminSalaryCalculation.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 70, 20));

        jTextFieldBasicSalary.setEditable(false);
        adminSalaryCalculation.add(jTextFieldBasicSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 270, -1));

        jTextFieldGrossSemiMonthlyRate.setEditable(false);
        adminSalaryCalculation.add(jTextFieldGrossSemiMonthlyRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 270, -1));

        jTextFieldHourlyRate.setEditable(false);
        jTextFieldHourlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHourlyRateActionPerformed(evt);
            }
        });
        adminSalaryCalculation.add(jTextFieldHourlyRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 270, -1));

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Hourly Rate :");
        adminSalaryCalculation.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, 20));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Gross Semi-Monthly Rate :");
        adminSalaryCalculation.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 160, 20));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Basic Salary :");
        adminSalaryCalculation.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 100, 20));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Deductions");
        adminSalaryCalculation.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, 90, 20));

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("SSS Contribution :");
        adminSalaryCalculation.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 360, 110, 20));

        jTextFieldSSSContribution.setEditable(false);
        jTextFieldSSSContribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSSSContributionActionPerformed(evt);
            }
        });
        adminSalaryCalculation.add(jTextFieldSSSContribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 360, 270, -1));

        jTextFieldPhilHealthContribution.setEditable(false);
        jTextFieldPhilHealthContribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhilHealthContributionActionPerformed(evt);
            }
        });
        adminSalaryCalculation.add(jTextFieldPhilHealthContribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, 270, -1));

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("PhilHealth Contribution :");
        adminSalaryCalculation.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 140, 20));

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Pag-IBIG Contribution :");
        adminSalaryCalculation.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 420, 150, 20));

        jTextFieldPagIBIGContribution.setEditable(false);
        jTextFieldPagIBIGContribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPagIBIGContributionActionPerformed(evt);
            }
        });
        adminSalaryCalculation.add(jTextFieldPagIBIGContribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 420, 270, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Total");
        adminSalaryCalculation.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 100, 20));

        jTextFieldGrossPay.setEditable(false);
        adminSalaryCalculation.add(jTextFieldGrossPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 510, 270, -1));

        jTextFieldNetPay.setEditable(false);
        adminSalaryCalculation.add(jTextFieldNetPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 540, 270, -1));

        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Gross Pay :");
        adminSalaryCalculation.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 510, 100, 20));

        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Net Pay :");
        adminSalaryCalculation.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 540, 100, 20));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Allowances");
        adminSalaryCalculation.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, 100, 20));

        jTextFieldRiceSubsidy.setEditable(false);
        adminSalaryCalculation.add(jTextFieldRiceSubsidy, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 500, 270, -1));

        jTextFieldPhoneAllowance.setEditable(false);
        adminSalaryCalculation.add(jTextFieldPhoneAllowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, 270, -1));

        jTextFieldClothingAllowance.setEditable(false);
        jTextFieldClothingAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClothingAllowanceActionPerformed(evt);
            }
        });
        adminSalaryCalculation.add(jTextFieldClothingAllowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 560, 270, -1));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Clothing Allowance :");
        adminSalaryCalculation.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 140, 20));

        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Phone Allowance :");
        adminSalaryCalculation.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 110, 20));

        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Rice Subsidy :");
        adminSalaryCalculation.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 90, 20));

        jButtonComputeSalary.setBackground(new java.awt.Color(102, 102, 102));
        jButtonComputeSalary.setForeground(new java.awt.Color(255, 255, 255));
        jButtonComputeSalary.setText("Compute");
        jButtonComputeSalary.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonComputeSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComputeSalaryActionPerformed(evt);
            }
        });
        adminSalaryCalculation.add(jButtonComputeSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 600, 160, 40));

        jTextFieldIDtoSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDtoSearchActionPerformed(evt);
            }
        });
        adminSalaryCalculation.add(jTextFieldIDtoSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 40, -1));

        jButtonSearchID.setText("Search Employee ID");
        jButtonSearchID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchIDActionPerformed(evt);
            }
        });
        adminSalaryCalculation.add(jButtonSearchID, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Select Month");
        adminSalaryCalculation.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 100, 20));

        jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboBoxMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMonthActionPerformed(evt);
            }
        });
        adminSalaryCalculation.add(jComboBoxMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, 100, 40));

        jPanelParent.add(adminSalaryCalculation, "card3");

        adminDatabase.setBackground(new java.awt.Color(51, 51, 51));
        adminDatabase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldEmpNum.setEditable(false);
        jTextFieldEmpNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmpNumActionPerformed(evt);
            }
        });
        adminDatabase.add(jTextFieldEmpNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 50, 30));

        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Employee Number: ");
        adminDatabase.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 20));

        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Last Name:");
        adminDatabase.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, 20));

        jTextFieldLastName.setEditable(false);
        jTextFieldLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLastNameActionPerformed(evt);
            }
        });
        adminDatabase.add(jTextFieldLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 250, 30));

        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("First Name:");
        adminDatabase.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, 20));

        jTextFieldFirstName.setEditable(false);
        jTextFieldFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFirstNameActionPerformed(evt);
            }
        });
        adminDatabase.add(jTextFieldFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 250, 30));

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Phone No.:");
        adminDatabase.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, 20));

        jTextFieldPhoneNum.setEditable(false);
        jTextFieldPhoneNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhoneNumActionPerformed(evt);
            }
        });
        adminDatabase.add(jTextFieldPhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 250, 30));

        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Address:");
        adminDatabase.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, 20));

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Supervisor:");
        adminDatabase.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 70, 20));

        jTextFieldSupervisor.setEditable(false);
        jTextFieldSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSupervisorActionPerformed(evt);
            }
        });
        adminDatabase.add(jTextFieldSupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 250, 30));

        jTextFieldPosition.setEditable(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        adminDatabase.add(jTextFieldPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 250, 30));

        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Position:");
        adminDatabase.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 50, 20));

        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Status:");
        adminDatabase.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 50, 30));

        jTextFieldStatus.setEditable(false);
        jTextFieldStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStatusActionPerformed(evt);
            }
        });
        adminDatabase.add(jTextFieldStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 250, 30));

        createButton.setBackground(new java.awt.Color(51, 51, 51));
        createButton.setForeground(new java.awt.Color(255, 255, 255));
        createButton.setText("Create Record");
        createButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });
        adminDatabase.add(createButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 160, 40));

        jLabel65.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Employee Record Database");
        adminDatabase.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 290, 40));

        deleteButton.setBackground(new java.awt.Color(51, 51, 51));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete Record");
        deleteButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        adminDatabase.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 550, 160, 40));

        clearButton.setBackground(new java.awt.Color(51, 51, 51));
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clearButton.setEnabled(false);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        adminDatabase.add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, 160, 40));

        editButton.setBackground(new java.awt.Color(51, 51, 51));
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("Edit Record");
        editButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        adminDatabase.add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 160, 40));

        jAreaSalaryAddress1.setEditable(false);
        jAreaSalaryAddress1.setColumns(20);
        jAreaSalaryAddress1.setLineWrap(true);
        jAreaSalaryAddress1.setRows(5);
        jScrollPane5.setViewportView(jAreaSalaryAddress1);

        adminDatabase.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 250, 70));

        jTableDatabase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableDatabase.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableDatabase.setMaximumSize(new java.awt.Dimension(0, 0));
        jTableDatabase.setMinimumSize(new java.awt.Dimension(0, 0));
        jTableDatabase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDatabaseMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableDatabase);

        adminDatabase.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 550, 540));

        jPanelParent.add(adminDatabase, "card4");

        adminLeaveManagement.setBackground(new java.awt.Color(51, 51, 51));
        adminLeaveManagement.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableLeaveManagement.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Request #", "Employee #", "First Name", "Last Name", "Position", "Leave Type", "Start Date", "Date of Return", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLeaveManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLeaveManagementMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableLeaveManagement);

        adminLeaveManagement.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 830, 570));

        jButtonApprove.setBackground(new java.awt.Color(0, 153, 0));
        jButtonApprove.setForeground(new java.awt.Color(255, 255, 255));
        jButtonApprove.setText("Approve");
        jButtonApprove.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonApprove.setEnabled(false);
        jButtonApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApproveActionPerformed(evt);
            }
        });
        adminLeaveManagement.add(jButtonApprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 530, 150, 50));

        jButtonReject1.setBackground(new java.awt.Color(204, 0, 0));
        jButtonReject1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonReject1.setText("Reject");
        jButtonReject1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonReject1.setEnabled(false);
        jButtonReject1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReject1ActionPerformed(evt);
            }
        });
        adminLeaveManagement.add(jButtonReject1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 600, 150, 50));

        jLabel66.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Leave Management");
        adminLeaveManagement.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 200, 30));

        jPanelParent.add(adminLeaveManagement, "card5");

        jPanel1.add(jPanelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 1050, 680));

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void adminDashboardMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminDashboardMenuMouseClicked
        // TODO add your handling code here:
        adminDashboardMenu.setBackground(new Color (51,51,51));
        adminSalaryCalculationMenu.setBackground(new Color (153,0,0));
        adminDatabaseMenu.setBackground(new Color (153,0,0));
        adminLeaveMenu.setBackground(new Color (153,0,0));
        jPanelParent.removeAll();
        jPanelParent.add(adminDashboard);
        jPanelParent.repaint();
        jPanelParent.revalidate();

    }//GEN-LAST:event_adminDashboardMenuMouseClicked

    private void adminSalaryCalculationMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminSalaryCalculationMenuMouseClicked
        // TODO add your handling code here:
        
//        
//        adminSalaryCalculationMenu.setBackground(new Color (51,51,51));
//        adminDashboardMenu.setBackground(new Color (153,0,0));
//        adminDatabaseMenu.setBackground(new Color (153,0,0));
//        adminLeaveMenu.setBackground(new Color (153,0,0));
//        jPanelParent.removeAll();
//        jPanelParent.add(adminSalaryCalculation);
//        jPanelParent.repaint();
//        jPanelParent.revalidate();
//        
        AccessParameters accessParameters = new AccessParameters();
            accessParameters.setAdminPageParameters(adminLeaveMenu, adminDashboardMenu, adminSalaryCalculationMenu, adminDatabaseMenu, jPanelParent, adminSalaryCalculation);
            admin.accessSalaryCalculation(accessParameters);
            
            jButtonComputeSalary.setEnabled(false);
            
    }//GEN-LAST:event_adminSalaryCalculationMenuMouseClicked

    private void adminLeaveMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminLeaveMenuMouseClicked
        // TODO add your handling code here:
//        adminLeaveMenu.setBackground(new Color (51,51,51));
//        adminDashboardMenu.setBackground(new Color (153,0,0));
//        adminSalaryCalculationMenu.setBackground(new Color (153,0,0));
//        adminDatabaseMenu.setBackground(new Color (153,0,0));
//        jPanelParent.removeAll();
//        jPanelParent.add(adminLeaveManagement);
//        jPanelParent.repaint();
//        jPanelParent.revalidate();
        
        
        AccessParameters accessParameters = new AccessParameters();
        accessParameters.setAdminPageParameters(adminLeaveMenu, adminDashboardMenu, adminSalaryCalculationMenu, adminDatabaseMenu, jPanelParent, adminLeaveManagement);
         
        admin.accessLeave(accessParameters);
        
        populateLeaveTable("LeaveManagement.csv");
       
    }//GEN-LAST:event_adminLeaveMenuMouseClicked

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void adminDatabaseMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminDatabaseMenuMouseClicked
        // TODO add your handling code here:
//        adminDatabaseMenu.setBackground(new Color (51,51,51));
//        adminDashboardMenu.setBackground(new Color (153,0,0));
//        adminLeaveMenu.setBackground(new Color (153,0,0));
//        adminSalaryCalculationMenu.setBackground(new Color (153,0,0));
//        jPanelParent.removeAll();
//        jPanelParent.add(adminDatabase);
//        jPanelParent.repaint();
//        jPanelParent.revalidate();
        
        admin.accessDatabase(jPanelParent, adminDatabase, adminDatabaseMenu, 
                adminDashboardMenu, adminLeaveMenu, adminSalaryCalculationMenu);
        
       populateDatabaseTable("MotorPH.csv");
        
    }//GEN-LAST:event_adminDatabaseMenuMouseClicked

    private void jTextSalaryEmpNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextSalaryEmpNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextSalaryEmpNoActionPerformed

    private void jComboBoxYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxYearActionPerformed

    private void jTextFieldHourlyRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHourlyRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHourlyRateActionPerformed

    private void jTextFieldSSSContributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSSSContributionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSSSContributionActionPerformed

    private void jTextFieldPhilHealthContributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPhilHealthContributionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPhilHealthContributionActionPerformed

    private void jTextFieldPagIBIGContributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPagIBIGContributionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPagIBIGContributionActionPerformed

    private void jButtonComputeSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComputeSalaryActionPerformed
        // TODO add your handling code here:
        
        if(!jTextFieldIDtoSearch.getText().trim().isEmpty()){
            
            double[] payrollData= admin.CalculatePayroll(employeeToCompute, (String)jComboBoxYear.getSelectedItem(), (String) jComboBoxMonth.getSelectedItem());
           jTextFieldSSSContribution.setText(Double.toString(payrollData[3]));
           jTextFieldPhilHealthContribution.setText(Double.toString(payrollData[4]));
           jTextFieldPagIBIGContribution.setText(Double.toString(payrollData[5]));
           
           jTextFieldGrossPay.setText(Double.toString(payrollData[0]));
            jTextFieldNetPay.setText(Double.toString(payrollData[9]));
            
        }else{
            //If search is empty, displlay "please search"
            if(jTextFieldIDtoSearch.getText().trim().isEmpty()){
              JOptionPane.showMessageDialog(this, "Please search an employee ID first");
            //If the text box has any input but not found, disaply "That doesnt exist"  
            }else{
               JOptionPane.showMessageDialog(this, "Employee searched does not exist");
            }    
        }
    }//GEN-LAST:event_jButtonComputeSalaryActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION){
            admin.deleteEmployee(Integer.parseInt(jTextFieldEmpNum.getText()));

            JOptionPane.showMessageDialog(null, " Record deleted successfully.");
                jTextFieldEmpNum.setText("");
                jTextFieldLastName.setText("");
                jTextFieldFirstName.setText("");
                jTextFieldPhoneNum.setText("");
                jAreaSalaryAddress1.setText("");
                jTextFieldSupervisor.setText("");
                jTextFieldPosition.setText("");
                jTextFieldStatus.setText("");
                deleteButton.setEnabled(false);
                editButton.setEnabled(false);
                clearButton.setEnabled(false);

            populateDatabaseTable("MotorPH.csv");
            

        } else{}
        
    }//GEN-LAST:event_deleteButtonActionPerformed
        
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
       // TODO add your handling code here:
        createRecord.setVisible(true);
        createRecord.pack();
        createRecord.setLocationRelativeTo(this);
        
        employeeDatabase = admin.loadCSV("MotorPH.csv");
        //Set new ID in create GUI
        
        //Execute this if csv is not empty and does not have 1 element, do this
        if (!employeeDatabase.isEmpty() && employeeDatabase.get(employeeDatabase.size()-1).length>0){
            
             //get the ID of the last employee of the list
             String lastID = employeeDatabase.get(employeeDatabase.size()-1)[0];
             //Add 1
             int ID = Integer.parseInt(lastID)+1;
             //set it as ID
             jTextFieldEmpNum1.setText(Integer.toString(ID));
        }
       
    }//GEN-LAST:event_createButtonActionPerformed

    private void jTextFieldStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStatusActionPerformed

    private void jTextFieldPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPositionActionPerformed

    private void jTextFieldSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSupervisorActionPerformed

    private void jTextFieldPhoneNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPhoneNumActionPerformed

    private void jTextFieldFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFirstNameActionPerformed

    private void jTextFieldLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLastNameActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        jTextFieldEmpNum.setText("");
        jTextFieldLastName.setText("");
        jTextFieldFirstName.setText("");
        jTextFieldPhoneNum.setText("");
        jAreaSalaryAddress1.setText("");
        jTextFieldSupervisor.setText("");
        jTextFieldPosition.setText("");
        jTextFieldStatus.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        editRecord.setVisible(true);
        editRecord.pack();
        editRecord.setLocationRelativeTo(this);
    }//GEN-LAST:event_editButtonActionPerformed

    private void jTableLeaveManagementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLeaveManagementMouseClicked
        // TODO add your handling code here:
        jButtonApprove.setEnabled(true);
        jButtonReject1.setEnabled(true);

    }//GEN-LAST:event_jTableLeaveManagementMouseClicked

    private void jButtonApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApproveActionPerformed
      
        int selectedRowIndex = jTableLeaveManagement.getSelectedRow();
        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(this, "No row selected. Please select a leave record.");
            
        }else if  (model.getValueAt(selectedRowIndex, 8).toString().equals("Pending")) {
            String employeeID = model.getValueAt(selectedRowIndex, 0).toString();
            admin.approveLeave(employeeID);
           populateLeaveTable("LeaveManagement.csv");
        }
        else {
            JOptionPane.showMessageDialog(this, "Leave record cannot be edited anymore.");
        }
        
    }//GEN-LAST:event_jButtonApproveActionPerformed

    private void jButtonReject1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReject1ActionPerformed
       
        int selectedRowIndex = jTableLeaveManagement.getSelectedRow();
        
         if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(this, "No row selected. Please select a leave record.");
            
        }else if (model.getValueAt(selectedRowIndex, 8).toString().equals("Pending")) {
            String employeeID = model.getValueAt(selectedRowIndex, 0).toString();
            admin.denyLeave(employeeID, jButtonReject1);
             populateLeaveTable("LeaveManagement.csv");
        } else {
            JOptionPane.showMessageDialog(this, "Leave record cannot be edited anymore.");
        }

    }//GEN-LAST:event_jButtonReject1ActionPerformed

    private void jTableDatabaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDatabaseMouseClicked
        // TODO add your handling code here:
        createButton.setEnabled(true);
        editButton.setEnabled(true);
        clearButton.setEnabled(true);
        deleteButton.setEnabled(true);
        
        //Initialize Table and get the Index of the selected row
        model = (DefaultTableModel) jTableDatabase.getModel();
        int SelectedRowIndex = jTableDatabase.getSelectedRow();
        
        //Populate the rest of the Fields based on the selected row
        jTextFieldEmpNum.setText(model.getValueAt(SelectedRowIndex, 0).toString());
        jTextFieldLastName.setText(model.getValueAt(SelectedRowIndex, 1).toString());
        jTextFieldFirstName.setText(model.getValueAt(SelectedRowIndex, 2).toString());
        jTextFieldPhoneNum.setText(model.getValueAt(SelectedRowIndex, 5).toString());
        jAreaSalaryAddress1.setText(model.getValueAt(SelectedRowIndex, 4).toString());
        jTextFieldSupervisor.setText(model.getValueAt(SelectedRowIndex, 12).toString());
        jTextFieldPosition.setText(model.getValueAt(SelectedRowIndex, 11).toString());
        jTextFieldStatus.setText(model.getValueAt(SelectedRowIndex, 10).toString());
        
        
        //Once a row has been selected, populate the Edit Employee page
         jTextFieldEditEmpNum.setText(jTextFieldEmpNum.getText());
         String[] infoToUpdate = admin.searchUserData(employeeDatabase,jTextFieldEmpNum.getText() );
         
         jTextFieldEditFirstName.setText(infoToUpdate[2]);
         jTextFieldEditLastName.setText(infoToUpdate[1]);
         jTextFieldEditAddress.setText(infoToUpdate[4]);
         jTextFieldEditPhoneNum.setText(infoToUpdate[5]);
         jTextFieldEditSSSNum.setText(infoToUpdate[6]);
         jTextFieldEditPhilHealthNum.setText(infoToUpdate[7]);
         jTextFieldEditTINNum.setText(infoToUpdate[8]);
         jTextFieldEditPagibigNum.setText(infoToUpdate[9]);
         jComboBoxEditStatus.setSelectedItem(infoToUpdate[10]);
         jComboBoxEditPosition.setSelectedItem(infoToUpdate[11]);
         jComboBoxEditSupervisor.setSelectedItem(infoToUpdate[12]);
         jTextFieldEditBasicSalary.setText(infoToUpdate[13]);
         jTextFieldEditRiceSubsidy.setText(infoToUpdate[14]);
         jTextFieldEditPhoneAllowance.setText(infoToUpdate[15]);
         jTextFieldEditClothingAllowance.setText(infoToUpdate[16]);
         jTextFieldEditGrossSemiMonthly.setText(infoToUpdate[17]);
         jTextFieldEditHourlyRateAllowance.setText(infoToUpdate[18]);
         
         String birthday = infoToUpdate[3];
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date birthdayDate;
        try {
            birthdayDate = sdf.parse(birthday);
        } catch (ParseException e) {
            // handle parsing error
            JOptionPane.showMessageDialog(this, "Error parsing birthday: " + e.getMessage());
            return;
        }
        jCalendarBirthday2.setDate(birthdayDate);
        jCalendarBirthday2.repaint();
         
        //dito cj
         
    }//GEN-LAST:event_jTableDatabaseMouseClicked

    private void jTextFieldEmpNum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmpNum1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmpNum1ActionPerformed

    private void jTextFieldFirstName2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFirstName2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFirstName2ActionPerformed

    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxStatusActionPerformed

    private void jComboBoxPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPositionActionPerformed

    private void jTextFieldClothingAllowance1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClothingAllowance1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClothingAllowance1ActionPerformed

    private void saveCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveCreateButtonActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
        try {
            admin.createEmployee(jTextFieldEmpNum1.getText(), jTextFieldFirstName2.getText(), jTextFieldLastName1.getText(),
                    sdf.format(jCalendarBirthday.getDate()), jTextFieldAddress.getText(), jTextFieldPhoneNum1.getText(), jTextFieldSSSNum.getText(), jTextFieldPhilHealthNum.getText(),
                    jTextFieldTINNum.getText(), jTextFieldPagibigNum.getText(), jComboBoxStatus.getSelectedItem().toString(), jComboBoxPosition.getSelectedItem().toString(),
                    jComboBoxSupervisor.getSelectedItem().toString(), jTextFieldBasicSalary1.getText(), jTextFieldRiceSubsidy1.getText(),
                    jTextFieldPhoneAllowance1.getText(), jTextFieldClothingAllowance1.getText(), createRecord);
        } catch (ParseException ex) {
            Logger.getLogger(AdminDashboardGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

 
        if(admin.isInputValid()){
            populateDatabaseTable("MotorPH.csv");
            createRecord.setVisible(false);
           jTextFieldFirstName2.setText("");
           jTextFieldLastName1.setText("");
           jTextFieldAddress.setText("");
           jTextFieldPhoneNum1.setText("");
           jTextFieldSSSNum.setText("");
           jTextFieldPhilHealthNum.setText("");
           jTextFieldTINNum.setText("");
           jTextFieldPagibigNum.setText("");
           jTextFieldBasicSalary1.setText("");
           jTextFieldPhoneAllowance1.setText("");
           jTextFieldRiceSubsidy1.setText("");
           jTextFieldClothingAllowance1.setText(""); 
        }
                  
    }//GEN-LAST:event_saveCreateButtonActionPerformed

    private void cancelCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelCreateButtonActionPerformed
        // TODO add your handling code here:
        createRecord.setVisible(false);
        jTextFieldFirstName2.setText("");
        jTextFieldLastName1.setText("");
        jTextFieldAddress.setText("");
        jTextFieldPhoneNum1.setText("");
        jTextFieldSSSNum.setText("");
        jTextFieldPhilHealthNum.setText("");
        jTextFieldTINNum.setText("");
        jTextFieldPagibigNum.setText("");
        jTextFieldBasicSalary1.setText("");
        jTextFieldPhoneAllowance1.setText("");
        jTextFieldRiceSubsidy1.setText("");
        jTextFieldClothingAllowance1.setText("");
    }//GEN-LAST:event_cancelCreateButtonActionPerformed

    private void jTextFieldEditEmpNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEditEmpNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEditEmpNumActionPerformed

    private void jTextFieldEditFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEditFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEditFirstNameActionPerformed

    private void jComboBoxEditStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEditStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxEditStatusActionPerformed

    private void jComboBoxEditPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEditPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxEditPositionActionPerformed

    private void jTextFieldEditClothingAllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEditClothingAllowanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEditClothingAllowanceActionPerformed

    private void jTextFieldEditGrossSemiMonthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEditGrossSemiMonthlyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEditGrossSemiMonthlyActionPerformed

    private void jTextFieldEditHourlyRateAllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEditHourlyRateAllowanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEditHourlyRateAllowanceActionPerformed

    private void cancelEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelEditButtonActionPerformed
        // TODO add your handling code here:
        editRecord.setVisible(false);
    }//GEN-LAST:event_cancelEditButtonActionPerformed

    private void saveEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveEditButtonActionPerformed
        // TODO add your handling code here:

        admin.updateEmployee(jTextFieldEditEmpNum.getText(), jTextFieldEditFirstName.getText(), jTextFieldEditLastName.getText(),(jCalendarBirthday2.getDate()),
                jTextFieldEditAddress.getText(), jTextFieldEditPhoneNum.getText(), jTextFieldEditSSSNum.getText(), jTextFieldEditPhilHealthNum.getText(), jTextFieldEditTINNum.getText(), 
                jTextFieldEditPagibigNum.getText(), jComboBoxEditStatus.getSelectedItem().toString(), jComboBoxEditPosition.getSelectedItem().toString(), 
                jComboBoxEditSupervisor.getSelectedItem().toString(), jTextFieldEditBasicSalary.getText(), 
                jTextFieldEditRiceSubsidy.getText(), jTextFieldEditPhoneAllowance.getText(), jTextFieldEditClothingAllowance.getText(), jTextFieldEditGrossSemiMonthly.getText(), 
                jTextFieldEditHourlyRateAllowance.getText(), editRecord);
        
 
        
        if(admin.getIsValidEditInput()){
            populateDatabaseTable("MotorPH.csv");
            editRecord.setVisible(false);
        }
    }//GEN-LAST:event_saveEditButtonActionPerformed

    private void jTextFieldEmpNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmpNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmpNumActionPerformed

    private void jTextFieldEditLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEditLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEditLastNameActionPerformed

    private void jTextFieldEditPhoneNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEditPhoneNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEditPhoneNumActionPerformed

    private void jTextFieldIDtoSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDtoSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDtoSearchActionPerformed

    private void jButtonSearchIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchIDActionPerformed
        // TODO add your handling code here:
        
       // search user data then turn it into an object
        employeeToCompute = setEmployeeData(jTextFieldIDtoSearch.getText());
        //If not null, proceed as normal. No else statement because it's already done in the Compute button. see the event for more details
        if (employeeToCompute!=null){
                jButtonComputeSalary.setEnabled(true);
                
                jTextSalaryEmpNo.setText(employeeToCompute.getEmployeeId());
                jTextSalaryFirstName.setText(employeeToCompute.getFirstName());
                jTextSalaryLastName.setText(employeeToCompute.getLastName());
                jAreaSalaryAddress.setText(employeeToCompute.getAddress());
                jTextSalaryPhoneNo.setText(employeeToCompute.getPhoneNumber());
                jTextSalaryBirthday.setText(employeeToCompute.getBirthday());
                jTextSalaryStatus.setText(employeeToCompute.getStatus());
                jTextSalaryPosition.setText(employeeToCompute.getPosition());
                jTextSalarySupervisor.setText(employeeToCompute.getImmediateSupervisor());
                jTextFieldBasicSalary.setText(Double.toString(employeeToCompute.getBasicSalary()));
                jTextFieldGrossSemiMonthlyRate.setText(Double.toString(employeeToCompute.getGrossSemiMonthlyRate()));
                jTextFieldHourlyRate.setText(Double.toString(employeeToCompute.getHourlyRate()));
                jTextFieldRiceSubsidy.setText(Double.toString(employeeToCompute.getPhoneAllowance()));
                jTextFieldPhoneAllowance.setText(Double.toString(employeeToCompute.getPhoneAllowance()));
                jTextFieldClothingAllowance.setText(Double.toString(employeeToCompute.getClothingAllowance()));
                jTextSalarySssNum.setText(employeeToCompute.getSssNumber());
                jTextSalaryPhilhealthNum.setText(employeeToCompute.getPhilhealthNumber());
                jTextSalaryTin.setText(employeeToCompute.getTinNumber());
                jTextSalaryPagibigNum.setText(employeeToCompute.getPagIbigNumber());
              // Dito cj
         
        }else{jButtonComputeSalary.setEnabled(false);} 
                
        
    }//GEN-LAST:event_jButtonSearchIDActionPerformed

    private void jTextFieldClothingAllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClothingAllowanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClothingAllowanceActionPerformed

    private void jComboBoxMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMonthActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               UnidentifiedUser unidentifiedUser = new UnidentifiedUser();
                unidentifiedUser.setRole("Admin");
                
                LoginValidator lv = new LoginValidator();
                lv.openDashboard(unidentifiedUser);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adminDashboard;
    private javax.swing.JPanel adminDashboardMenu;
    private javax.swing.JPanel adminDatabase;
    private javax.swing.JPanel adminDatabaseMenu;
    private javax.swing.JPanel adminLeaveManagement;
    private javax.swing.JPanel adminLeaveMenu;
    private javax.swing.JPanel adminSalaryCalculation;
    private javax.swing.JPanel adminSalaryCalculationMenu;
    private javax.swing.JButton cancelCreateButton;
    private javax.swing.JButton cancelEditButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton createButton;
    public javax.swing.JFrame createRecord;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JFrame editRecord;
    private javax.swing.JTextArea jAreaDashboardAddress;
    private javax.swing.JTextArea jAreaSalaryAddress;
    private javax.swing.JTextArea jAreaSalaryAddress1;
    private javax.swing.JButton jButtonApprove;
    private javax.swing.JButton jButtonComputeSalary;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonReject1;
    private javax.swing.JButton jButtonSearchID;
    private com.toedter.calendar.JCalendar jCalendarBirthday;
    private com.toedter.calendar.JCalendar jCalendarBirthday2;
    private javax.swing.JComboBox<String> jComboBoxEditPosition;
    private javax.swing.JComboBox<String> jComboBoxEditStatus;
    private javax.swing.JComboBox<String> jComboBoxEditSupervisor;
    private javax.swing.JComboBox<String> jComboBoxMonth;
    private javax.swing.JComboBox<String> jComboBoxPosition;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JComboBox<String> jComboBoxSupervisor;
    private javax.swing.JComboBox<String> jComboBoxYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelParent;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableDatabase;
    private javax.swing.JTable jTableLeaveManagement;
    private javax.swing.JTextField jTextDashboardBasicSalary;
    private javax.swing.JTextField jTextDashboardBirthday;
    private javax.swing.JTextField jTextDashboardClothingAllowance;
    private javax.swing.JTextField jTextDashboardEmpNo;
    private javax.swing.JTextField jTextDashboardFirstName;
    private javax.swing.JTextField jTextDashboardGrossSemiMonthlyRate;
    private javax.swing.JTextField jTextDashboardHourlyRate;
    private javax.swing.JTextField jTextDashboardLastName;
    private javax.swing.JTextField jTextDashboardPagibigNum;
    private javax.swing.JTextField jTextDashboardPhilhealthNum;
    private javax.swing.JTextField jTextDashboardPhoneAllowance;
    private javax.swing.JTextField jTextDashboardPhoneNo;
    private javax.swing.JTextField jTextDashboardPosition;
    private javax.swing.JTextField jTextDashboardRiceSubsidy;
    private javax.swing.JTextField jTextDashboardSssNum;
    private javax.swing.JTextField jTextDashboardStatus;
    private javax.swing.JTextField jTextDashboardSupervisor;
    private javax.swing.JTextField jTextDashboardTinNum;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldBasicSalary;
    private javax.swing.JTextField jTextFieldBasicSalary1;
    private javax.swing.JTextField jTextFieldClothingAllowance;
    private javax.swing.JTextField jTextFieldClothingAllowance1;
    private javax.swing.JTextField jTextFieldEditAddress;
    private javax.swing.JTextField jTextFieldEditBasicSalary;
    private javax.swing.JTextField jTextFieldEditClothingAllowance;
    private javax.swing.JTextField jTextFieldEditEmpNum;
    private javax.swing.JTextField jTextFieldEditFirstName;
    private javax.swing.JTextField jTextFieldEditGrossSemiMonthly;
    private javax.swing.JTextField jTextFieldEditHourlyRateAllowance;
    private javax.swing.JTextField jTextFieldEditLastName;
    private javax.swing.JTextField jTextFieldEditPagibigNum;
    private javax.swing.JTextField jTextFieldEditPhilHealthNum;
    private javax.swing.JTextField jTextFieldEditPhoneAllowance;
    private javax.swing.JTextField jTextFieldEditPhoneNum;
    private javax.swing.JTextField jTextFieldEditRiceSubsidy;
    private javax.swing.JTextField jTextFieldEditSSSNum;
    private javax.swing.JTextField jTextFieldEditTINNum;
    private javax.swing.JTextField jTextFieldEmpNum;
    private javax.swing.JTextField jTextFieldEmpNum1;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldFirstName2;
    private javax.swing.JTextField jTextFieldGrossPay;
    private javax.swing.JTextField jTextFieldGrossSemiMonthlyRate;
    private javax.swing.JTextField jTextFieldHourlyRate;
    private javax.swing.JTextField jTextFieldIDtoSearch;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldLastName1;
    private javax.swing.JTextField jTextFieldNetPay;
    private javax.swing.JTextField jTextFieldPagIBIGContribution;
    private javax.swing.JTextField jTextFieldPagibigNum;
    private javax.swing.JTextField jTextFieldPhilHealthContribution;
    private javax.swing.JTextField jTextFieldPhilHealthNum;
    private javax.swing.JTextField jTextFieldPhoneAllowance;
    private javax.swing.JTextField jTextFieldPhoneAllowance1;
    private javax.swing.JTextField jTextFieldPhoneNum;
    private javax.swing.JTextField jTextFieldPhoneNum1;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldRiceSubsidy;
    private javax.swing.JTextField jTextFieldRiceSubsidy1;
    private javax.swing.JTextField jTextFieldSSSContribution;
    private javax.swing.JTextField jTextFieldSSSNum;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldSupervisor;
    private javax.swing.JTextField jTextFieldTINNum;
    private javax.swing.JTextField jTextSalaryBirthday;
    private javax.swing.JTextField jTextSalaryEmpNo;
    private javax.swing.JTextField jTextSalaryFirstName;
    private javax.swing.JTextField jTextSalaryLastName;
    private javax.swing.JTextField jTextSalaryPagibigNum;
    private javax.swing.JTextField jTextSalaryPhilhealthNum;
    private javax.swing.JTextField jTextSalaryPhoneNo;
    private javax.swing.JTextField jTextSalaryPosition;
    private javax.swing.JTextField jTextSalarySssNum;
    private javax.swing.JTextField jTextSalaryStatus;
    private javax.swing.JTextField jTextSalarySupervisor;
    private javax.swing.JTextField jTextSalaryTin;
    private javax.swing.JButton saveCreateButton;
    private javax.swing.JButton saveEditButton;
    // End of variables declaration//GEN-END:variables
}
