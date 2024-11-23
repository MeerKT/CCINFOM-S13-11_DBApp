/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.*;
import java.sql.*;
import java.util.*;


/**
 *
 * @author katie
 */
public class employee_salary {
    // Fields
    public int employee_ID;
    public int salary_ID;
    public double basic_salary;
    public String bank_account;
    public int overtime_record;
    public double taxes;
    public String benefits;
    public double raises;
    public double netTotal;

    //list of employees
    public ArrayList<Integer> employee_IDList = new ArrayList<>();
    public ArrayList<Integer> salary_IDList = new ArrayList<>();
    public ArrayList<Double> basic_salaryList = new ArrayList<>();
    public ArrayList<String> bank_accountList = new ArrayList<>();
    public ArrayList<Integer> overtime_recordList = new ArrayList<>();
    public ArrayList<Double> taxesList = new ArrayList<>();
    public ArrayList<String> benefitsList = new ArrayList<>();
    public ArrayList<Double> raisesList = new ArrayList<>();
    public ArrayList<Double> netTotalList = new ArrayList<>();

    public int fillLists() {
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employee_salary");
            ResultSet salaries = pstmt.executeQuery();

            employee_IDList.clear();
            salary_IDList.clear();
            basic_salaryList.clear();
            bank_accountList.clear();
            overtime_recordList.clear();
            taxesList.clear();
            benefitsList.clear();
            raisesList.clear();
            netTotalList.clear();

            while (salaries.next()) {
                employee_ID = salaries.getInt("employee_ID");
                salary_ID = salaries.getInt("salary_ID");
                basic_salary = salaries.getDouble("basic_salary");
                bank_account = salaries.getString("bank_account");
                overtime_record = salaries.getInt("overtime_record");
                taxes = salaries.getDouble("taxes");
                benefits = salaries.getString("benefits");
                raises = salaries.getDouble("raises");
                netTotal = salaries.getDouble("net_total_salary");

                employee_IDList.add(employee_ID);
                salary_IDList.add(salary_ID);
                basic_salaryList.add(basic_salary);
                bank_accountList.add(bank_account);
                overtime_recordList.add(overtime_record);
                taxesList.add(taxes);
                benefitsList.add(benefits);
                raisesList.add(raises);
                netTotalList.add(netTotal);
            }
            pstmt.close();
            conn.close();

            return 1;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return 0;
        }
    }

    public int register_salary(int empID, double newSalary, String bankAcc, int overTime, double Taxes, String benefits, double raise) {
        try {
            this.fillLists();

            if (!(this.employee_IDList.contains(empID))) {
                Connection conn;
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");

                PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(salary_ID) + 1 AS newID FROM employee_salary");
                ResultSet rst = pstmt.executeQuery();
                while (rst.next()) {
                    salary_ID = rst.getInt("newID");
                }


                pstmt = conn.prepareStatement("INSERT INTO employee_salary (employee_ID, salary_ID, basic_salary, bank_account, overtime_record, taxes, benefits,raises,net_total_salary) VALUES(?,?,?,?,?,?,?,?,?)");
                pstmt.setInt(1, empID);
                pstmt.setInt(2, salary_ID);
                pstmt.setDouble(3, newSalary);
                pstmt.setString(4, bankAcc);
                pstmt.setInt(5, overTime);
                pstmt.setDouble(6, Taxes);
                pstmt.setString(7, benefits);
                pstmt.setDouble(8, raise);
                pstmt.setDouble(9, newSalary + raise - Taxes);
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();

                System.out.print("Salary Registered!");
                return 1;
            } else {

                Connection conn;
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");

                PreparedStatement pstmt = conn.prepareStatement("UPDATE employee_salary SET basic_salary = ? , bank_account = ?, overtime_record = ?, taxes = ?, benefits = ? ,raises = ? ,net_total_salary = ? WHERE employee_ID = ?");
                pstmt.setDouble(1,newSalary);
                pstmt.setString(2,bankAcc);
                pstmt.setInt(3, overTime);
                pstmt.setDouble(4, Taxes);
                pstmt.setString(5, benefits);
                pstmt.setDouble(6, raise);
                pstmt.setDouble(7, newSalary + raise - Taxes);
                pstmt.setInt(8, empID);
                pstmt.executeUpdate();
                System.out.print("Salary updated!");
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }


}
