/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author river
 */
public class change_employee_status {

    // fields
    public int employee_ID;
    public String status_type;
    public String status_change_date;
    public int status_changeID;

    // list of attributes
    public ArrayList<Integer> employee_IDList = new ArrayList<>();
    public ArrayList<String> status_typeList = new ArrayList<>();
    public ArrayList<String> event_dateList = new ArrayList<>();
    public ArrayList<Integer> status_changeIDList = new ArrayList<>();

    public change_employee_status() {};
    public int update_employee_status(int employee_ID, String status_type) {
        employee_records reference = new employee_records();
        try {
            Connection  conn;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employeedb", "root", "WillKill4QP!");
            reference.getEmployees();

            int status_changeID = 0;
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(status_changeID) + 1 AS newID FROM  employee_status_change");
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                status_changeID = rst.getInt("newID");
            }


            //updates status change record
            pstmt = conn.prepareStatement("INSERT INTO employee_status_change (employee_ID, status_changeID, status_type,status_change_date) VALUE(?,?,?,?)");
            pstmt.setInt(1,employee_ID);
            pstmt.setInt(2,status_changeID);
            pstmt.setString(3, status_type);
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            pstmt.setDate(4, sqlDate);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return 1;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }



}
