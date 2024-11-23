import java.sql.Date;
import java.sql.*;
import java.util.*;

public class project_management {
    // Fields
    public String project_type;
    public int employee_ID;
    public int project_ID;
    public Date start_date;
    public Date end_date;
    public Date project_deadline;
    public String project_description;
    public String project_status;
    public String client;

    // List of attributes
    public ArrayList<String> project_typeList = new ArrayList<>();
    public ArrayList<Integer> employee_IDList = new ArrayList<>();
    public ArrayList<Integer> project_IDList = new ArrayList<>();
    public ArrayList<Date> start_dateList = new ArrayList<>();
    public ArrayList<Date> end_dateList = new ArrayList<>();
    public ArrayList<Date> project_deadlineList = new ArrayList<>();
    public ArrayList<String> project_descriptionList = new ArrayList<>();
    public ArrayList<String> project_statusList = new ArrayList<>();
    public ArrayList<String> clientList = new ArrayList<>();

    public project_management() {
    }

    // Method to fetch projects
    public int getProjects() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");
            System.out.println("Connection Successful!");

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM projects");
            ResultSet projects = pstmt.executeQuery();

            project_typeList.clear();
            employee_IDList.clear();
            project_IDList.clear();
            start_dateList.clear();
            end_dateList.clear();
            project_deadlineList.clear();
            project_descriptionList.clear();
            project_statusList.clear();
            clientList.clear();

            while (projects.next()) {
                project_typeList.add(projects.getString("project_type"));
                employee_IDList.add(projects.getInt("employee_ID"));
                project_IDList.add(projects.getInt("project_ID"));
                start_dateList.add(projects.getDate("start_date"));
                end_dateList.add(projects.getDate("end_date"));
                project_deadlineList.add(projects.getDate("project_deadline"));
                project_descriptionList.add(projects.getString("project_description"));
                project_statusList.add(projects.getString("project_status"));
                clientList.add(projects.getString("client"));
            }

            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return 0;
        }
    }

    // Method to add a new project
    public boolean addNewProject(int employee_ID, String project_type, String start_date, String end_date, String deadline, String description, String status, String client) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");
            System.out.println("Connection Successful!");

            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO projects (employee_ID, project_type, start_date, end_date, project_deadline, project_description, project_status, client) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            pstmt.setInt(1, employee_ID);
            pstmt.setString(2, project_type);
            pstmt.setDate(3, Date.valueOf(start_date));
            pstmt.setDate(4, Date.valueOf(end_date));
            pstmt.setDate(5, Date.valueOf(deadline));
            pstmt.setString(6, description);
            pstmt.setString(7, status);
            pstmt.setString(8, client);

            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }

    // Method to validate if a project exists
    public boolean isValidProject(int project_ID) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");
            System.out.println("Connection Successful!");

            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM projects WHERE project_ID = ?");
            pstmt.setInt(1, project_ID);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            boolean exists = rs.getInt(1) > 0;

            pstmt.close();
            conn.close();
            return exists;

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }

    // Method to update an existing project
    public boolean updateProject(int project_ID, int employee_ID, String project_type, String start_date, String end_date, String deadline, String description, String status, String client) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");
            System.out.println("Connection Successful!");

            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE projects SET employee_ID = ?, project_type = ?, start_date = ?, end_date = ?, project_deadline = ?, project_description = ?, project_status = ?, client = ? WHERE project_ID = ?"
            );

            pstmt.setInt(1, employee_ID);
            pstmt.setString(2, project_type);
            pstmt.setDate(3, Date.valueOf(start_date));
            pstmt.setDate(4, Date.valueOf(end_date));
            pstmt.setDate(5, Date.valueOf(deadline));
            pstmt.setString(6, description);
            pstmt.setString(7, status);
            pstmt.setString(8, client);
            pstmt.setInt(9, project_ID);

            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }
}
