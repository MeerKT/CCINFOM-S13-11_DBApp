
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author katie
 */
import java.sql.Date;
import java.time.*;
import java.sql.*;
import java.util.*;

public class employee_records {
    // Fields
    public int          employee_ID;
    public String       first_name;
    public String       last_name;
    public String       gender;
    public String       department_name;
    public String       position_type;
    public int          years_of_experience;
    public String       education;
    public Time    check_in_time;
    public Time    check_out_time;
    public Date termination_date;
    public Date hire_date;

    //list of attributes
    public ArrayList<Integer> employee_IDList = new ArrayList<>();
    public ArrayList<String> first_nameList = new ArrayList<>();
    public ArrayList<String> last_nameList = new ArrayList<>();
    public ArrayList<String> genderList = new ArrayList<>();
    public ArrayList<String> department_nameList = new ArrayList<>();
    public ArrayList<String> position_typeList = new ArrayList<>();
    public ArrayList<Integer> years_of_experienceList = new ArrayList<>();
    public ArrayList<String> educationList = new ArrayList<>();
    public ArrayList<Time> check_in_timeList = new ArrayList<>();
    public ArrayList<Time> check_out_timeList = new ArrayList<>();
    public ArrayList<Date> termination_dateList = new ArrayList<>();
    public ArrayList<Date> hire_dateList = new ArrayList<>();

    public employee_records(){}




    public int getEmployees() {
        try{
            Connection conn;
            System.out.println("Connection Successful!");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb" , "root","WillKill4QP!");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employee_records");
            ResultSet employees = pstmt.executeQuery();

            employee_IDList.clear();
            first_nameList.clear();
            last_nameList.clear();
            genderList.clear();
            department_nameList.clear();
            position_typeList.clear();
            years_of_experienceList.clear();
            educationList.clear();
            check_in_timeList.clear();
            check_out_timeList.clear();
            hire_dateList.clear();
            termination_dateList.clear();


            while(employees.next()) {
                employee_ID = employees.getInt("employee_ID");
                first_name = employees.getString("first_name");
                last_name = employees.getString("last_name");
                gender = employees.getString("gender");
                department_name = employees.getString("department_name");
                position_type = employees.getString("position_type");
                years_of_experience = employees.getInt("years_of_experience");
                education = employees.getString("education");
                check_in_time = employees.getTime("check_in_time");
                check_out_time = employees.getTime("check_out_time");
                hire_date = employees.getDate("hire_date");
                termination_date = employees.getDate("termination_date");


                employee_IDList.add(employee_ID);
                first_nameList.add(first_name);
                last_nameList.add(last_name);
                genderList.add(gender);
                department_nameList.add(department_name);
                position_typeList.add(position_type);
                years_of_experienceList.add(years_of_experience);
                educationList.add(education);
                check_in_timeList.add(check_in_time);
                check_out_timeList.add(check_out_time);
                hire_dateList.add(hire_date);
                termination_dateList.add(termination_date);
            }
            pstmt.close();
            conn.close();

            return 1;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return 0;
        }
    }
}
