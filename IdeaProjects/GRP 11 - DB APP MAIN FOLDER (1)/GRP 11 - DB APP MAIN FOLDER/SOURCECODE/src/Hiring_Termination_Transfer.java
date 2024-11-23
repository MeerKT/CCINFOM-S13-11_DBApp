/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author river
 */
public class Hiring_Termination_Transfer {

    // fields
    public int employee_ID;
    public String event_type;
    public String event_date;
    public String department_from;
    public String department_to;

    // list of attributes
    public ArrayList<Integer> employee_IDList = new ArrayList<>();
    public ArrayList<String> event_typeList = new ArrayList<>();
    public ArrayList<String> event_dateList = new ArrayList<>();
    public ArrayList<String> event_departmentFromList = new ArrayList<>();
    public ArrayList<String> departmentToList = new ArrayList<>();

    public Hiring_Termination_Transfer() {};


    public int hire_employee(String newFirstName, String newLastName, String newGender, String newDepartment, String newPosition,
        int newExperienceLevel, String newEducation) {
        try{
            Connection conn;
            int employee_ID = 0;
            //establishes connection to a new database: ENSURE THAT YOUR USERNAME AND PASSWORD MATCHES THESE VALUES BEFORE TRYING TO DEBUG
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employeedb", "root", "WillKill4QP!");
            System.out.println("Connection Successful!");

            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(employee_ID) + 1 AS newID FROM  employee_records");
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                employee_ID = rst.getInt("newID");
            }
            pstmt = conn.prepareStatement("INSERT INTO employee_records (employee_ID, first_name, last_name, gender,department_name,position_type,years_of_experience, education,hire_date) VALUE(?,?,?,?,?,?,?,?,?)");

            pstmt.setInt(1, employee_ID);
            pstmt.setString(2, newFirstName);
            pstmt.setString(3, newLastName);
            pstmt.setString(4, newGender);
            pstmt.setString(5, newDepartment);
            pstmt.setString(6, newPosition);
            pstmt.setInt(7, newExperienceLevel);
            pstmt.setString(8, newEducation);

            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            pstmt.setDate(9, sqlDate);


            pstmt.executeUpdate();

            //updates event table
            int event_ID = 0;
            pstmt = conn.prepareStatement("SELECT MAX(event_ID) + 1 AS newID FROM  hiring_termination");
            rst = pstmt.executeQuery();
            while(rst.next()){
                event_ID = rst.getInt("newID");
            }
            pstmt = conn.prepareStatement("INSERT INTO hiring_termination (event_ID,employee_ID,event_type,event_date,department_to) VALUE(?,?,?,?,?)");
            pstmt.setInt(1, event_ID);
            pstmt.setInt(2, employee_ID);
            pstmt.setString(3, "Hired");
            pstmt.setDate(4, sqlDate);
            pstmt.setString(5, newDepartment);
            pstmt.executeUpdate();

            //updates employee_documentation table
            int docuID = 0;
            pstmt = conn.prepareStatement("SELECT MAX(document_ID) + 1 AS newID FROM  employee_documents");
            rst = pstmt.executeQuery();
            while(rst.next()){
                docuID = rst.getInt("newID");
            }

            pstmt = conn.prepareStatement("INSERT INTO employee_documents(employee_ID,document_ID,document_type,document_status,submission_date,expiry_date) VALUE(?,?,?,?,?,?)");
            pstmt.setInt(1, employee_ID);
            pstmt.setInt(2, docuID);
            pstmt.setString(3, "Government ID");
            pstmt.setString(4, "Active");
            pstmt.setDate(5, sqlDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sqlDate);
            calendar.add(Calendar.YEAR,5);
            java.sql.Date futureDate = new Date(calendar.getTimeInMillis());
            pstmt.setDate(6,futureDate);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement("INSERT INTO employee_documents(employee_ID,document_ID,document_type,document_status,submission_date,expiry_date) VALUE(?,?,?,?,?,?)");
            pstmt.setInt(1, employee_ID);
            pstmt.setInt(2, docuID+1);
            pstmt.setString(3, "Employee Contract");
            pstmt.setString(4, "Active");
            pstmt.setDate(5, sqlDate);
            calendar.setTime(sqlDate);
            calendar.add(Calendar.YEAR,2);
            futureDate = new Date(calendar.getTimeInMillis());
            pstmt.setDate(6,futureDate);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            System.out.println("Employee Hired!");
            return 1;

        }catch(Exception e){
            System.out.println("This isn't working");
            return 0;
        }
    }

    public int transfer_employee(int employee_ID, String department_to) {
        employee_records reference = new employee_records();
        try {
            Connection  conn;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employeedb", "root", "WillKill4QP!");

            reference.getEmployees();
            department_from = reference.department_nameList.get(employee_ID-1);

            if(department_to == reference.department_nameList.get(employee_ID-1)) {
                return 0;
            }

            //updates employee record
            PreparedStatement pstmt = conn.prepareStatement("UPDATE employee_records SET department_name = ? WHERE employee_ID = ?");
            pstmt.setString(1,department_to);
            pstmt.setInt(2,employee_ID);
            pstmt.executeUpdate();

            //updates hiring_termination table
            int event_ID = 0;
            pstmt = conn.prepareStatement("SELECT MAX(event_ID) + 1 AS newID FROM  hiring_termination");
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                event_ID = rst.getInt("newID");
            }
            pstmt = conn.prepareStatement("INSERT INTO hiring_termination (event_ID,employee_ID,event_type,event_date,department_from,department_to) VALUE(?,?,?,?,?,?)");
            pstmt.setInt(1, event_ID);
            pstmt.setInt(2, employee_ID);
            pstmt.setString(3, "Transferred");
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            pstmt.setDate(4, sqlDate);
            pstmt.setString(5, department_from);
            pstmt.setString(6, department_to);



            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return 1;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }


    public int terminate_employee(int employee_ID){


        try {
            Connection  conn;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employeedb", "root", "WillKill4QP!");
            PreparedStatement pstmt = conn.prepareStatement("UPDATE employee_records SET termination_date = ?, performance_reviews = ? WHERE employee_ID = ?");
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            pstmt.setDate(1, sqlDate);
            pstmt.setNull(2, java.sql.Types.VARCHAR);
            pstmt.setInt(3, employee_ID);

            pstmt.executeUpdate();

            //updates hiring_termination table
            int event_ID = 0;
            pstmt = conn.prepareStatement("SELECT MAX(event_ID) + 1 AS newID FROM  hiring_termination");
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                event_ID = rst.getInt("newID");
            }
            pstmt = conn.prepareStatement("INSERT INTO hiring_termination (event_ID, employee_ID,event_type,event_date) VALUE(?,?,?,?)");
            pstmt.setInt(1, event_ID);
            pstmt.setInt(2, employee_ID);
            pstmt.setString(3, "Terminated");
            pstmt.setDate(4, sqlDate);


            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    };

}
