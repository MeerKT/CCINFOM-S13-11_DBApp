
import java.sql.*;
import java.util.ArrayList;

public class employeeCountReport {

    private ArrayList<String> departmentNames = new ArrayList<>();
    private ArrayList<Integer> employeeCount = new ArrayList<>();

    public employeeCountReport() {
        clearReport();
    }

    // Clear all report data
    public void clearReport() {
        employeeCount.clear();
        departmentNames.clear();
    }

    // Generate the report
    public int generate() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");
            System.out.println("Connection Successful!");


            String query ="select department_name as Department, count(employee_ID) as EmployeeCount\n" +
                    "from employee_records\n" +
                    "group by department_name\n" +
                    "Order by EmployeeCount Desc";

            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            // Clear old data
            clearReport();

            // Fetch and store results
            while (rs.next()) {
                departmentNames.add(rs.getString("Department"));
                employeeCount.add(rs.getInt("EmployeeCount"));
            }

            rs.close();
            pstmt.close();
            conn.close();

            return 1; // Success
        } catch (SQLException e) {
            System.out.println("Error generating report: " + e.getMessage());
            return 0; // Failure
        }
    }

    // Print the report
    public void printReport() {
        System.out.println("================================================================================");
        System.out.printf("%-30s %-15s%n",
                "Department Name", "EmployeeCount");
        System.out.println("--------------------------------------------------------------------------------");

        for (int i = 0; i < departmentNames.size(); i++) {
            System.out.printf("%-30s %-15d%n",
                    departmentNames.get(i),
                    employeeCount.get(i)
            );
        }
    }
}
