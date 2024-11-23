import java.sql.*;
import java.util.ArrayList;

public class hire_attrition_report {

    private Integer hireYear, hireMonth, terminateYear, terminateMonth;
    private ArrayList<String> employeeIDs = new ArrayList<>();
    private ArrayList<Double> avgHires = new ArrayList<>();
    private ArrayList<Integer> numberTerminated = new ArrayList<>();

    // Constructor
    public hire_attrition_report() {
        clearReport();
    }

    // Clear all report data
    public void clearReport() {
        hireYear = null;
        hireMonth = null;
        terminateYear = null;
        terminateMonth = null;
        employeeIDs.clear();
        avgHires.clear();
        numberTerminated.clear();
    }

    // Generate the hires and attrition report
    public int generate(Integer hireYear, Integer hireMonth, Integer terminateYear, Integer terminateMonth) {
        this.hireYear = hireYear;
        this.hireMonth = hireMonth;
        this.terminateYear = terminateYear;
        this.terminateMonth = terminateMonth;

        try {
            // Establish the database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");
            System.out.println("Connection Successful!");

            // SQL query
            String query = "SELECT ht.employee_ID, " +
                    "       AVG(ht.event_type = 'Hired') AS avg_hires, " +
                    "       SUM(ht.event_type = 'Terminated') AS number_of_terminated, " +
                    "       YEAR(er.hire_date) AS hire_year, " +
                    "       YEAR(er.termination_date) AS terminate_year, " +
                    "       MONTH(er.hire_date) AS hire_month, " +
                    "       MONTH(er.termination_date) AS terminate_month " +
                    "FROM hiring_termination ht " +
                    "JOIN employee_records er ON ht.employee_ID = er.employee_ID " +
                    "WHERE (YEAR(er.hire_date) = ? AND MONTH(er.hire_date) = ?) " +
                    "      AND (YEAR(er.termination_date) = ? AND MONTH(er.termination_date) = ?) " +
                    "GROUP BY ht.employee_ID, hire_year, hire_month, terminate_year, terminate_month " +
                    "ORDER BY ht.employee_ID ASC";

            PreparedStatement pstmt = conn.prepareStatement(query);

            // Set the year and month filters for hire and termination
            pstmt.setObject(1, hireYear);
            pstmt.setObject(2, hireMonth);
            pstmt.setObject(3, terminateYear);
            pstmt.setObject(4, terminateMonth);

            ResultSet rs = pstmt.executeQuery();

            // Clear old data
            clearReport();

            // Fetch and store results
            while (rs.next()) {
                employeeIDs.add(rs.getString("employee_ID"));
                avgHires.add(rs.getDouble("avg_hires"));
                numberTerminated.add(rs.getInt("number_of_terminated"));
            }

            rs.close();
            pstmt.close();
            conn.close();

            return 1; // Success
        } catch (SQLException e) {
            System.out.println("Error generating hires and attrition report: " + e.getMessage());
            return 0; // Failure
        }
    }

    // Print the hires and attrition report
    public void printReport() {
        System.out.println("Employee Hires and Attrition Report");
        System.out.println("Filter - Hire: " + (hireMonth == null ? "All Months" : hireMonth) + "/" + (hireYear == null ? "All Years" : hireYear) +
                ", Termination: " + (terminateMonth == null ? "All Months" : terminateMonth) + "/" + (terminateYear == null ? "All Years" : terminateYear));
        System.out.println("==========================================================================");
        System.out.printf("%-15s %-25s %-25s%n", "Employee ID", "Avg Hires", "Number Terminated");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < employeeIDs.size(); i++) {
            System.out.printf("%-15s %-25.2f %-25d%n",
                    employeeIDs.get(i),
                    avgHires.get(i),
                    numberTerminated.get(i)
            );
        }
    }

    // Main method to test the hires and attrition report
    public static void main(String[] args) {
        hire_attrition_report har = new hire_attrition_report();

        // Example: Generate report for hires in Nov 2021 and terminations in Oct 2024
        if (har.generate(2021, 11, 2024, 10) == 1) {
            har.printReport();
        } else {
            System.out.println("Failed to generate the hires and attrition report.");
        }
    }
}
