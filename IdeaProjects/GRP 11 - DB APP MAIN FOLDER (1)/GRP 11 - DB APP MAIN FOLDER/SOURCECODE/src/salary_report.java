
import java.sql.*;
import java.util.ArrayList;

public class salary_report {

    private Integer year; // Year filter for the report
    private ArrayList<String> employeeIDs = new ArrayList<>();
    private ArrayList<Double> totalSalaries = new ArrayList<>();
    private ArrayList<Double> averageSalaries = new ArrayList<>();

    // Constructor
    public salary_report() {
        clearReport();
    }

    // Clear all report data
    public void clearReport() {
        year = null;
        employeeIDs.clear();
        totalSalaries.clear();
        averageSalaries.clear();
    }

    // Generate the salary report
    public int generate(Integer year) {
        this.year = year;

        try {
            // Establish the database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employeedb", "root", "Sweetmochi*2003!");
            System.out.println("Connection Successful!");


            // SQL query
            String query = "SELECT es.employee_ID, " +
                    "       SUM(es.basic_salary + es.raises - es.taxes) AS total_salary_paid, " +
                    "       AVG(es.basic_salary + es.raises - es.taxes) AS average_salary, " +
                    "       YEAR(sd.disbursment_date) AS salary_year " +
                    "FROM employee_salary es " +
                    "JOIN salary_disbursement sd ON es.salary_ID = sd.salary_ID " +
                    "WHERE (YEAR(sd.disbursment_date) = ? OR ? IS NULL) " +
                    "GROUP BY es.employee_ID, salary_year " +
                    "ORDER BY es.employee_ID, salary_year ASC";

            PreparedStatement pstmt = conn.prepareStatement(query);

            // Set the year filter (or null for all years)
            pstmt.setObject(1, year);
            pstmt.setObject(2, year);

            ResultSet rs = pstmt.executeQuery();

            // Clear old data
            clearReport();

            // Fetch and store results
            while (rs.next()) {
                employeeIDs.add(rs.getString("employee_ID"));
                totalSalaries.add(rs.getDouble("total_salary_paid"));
                averageSalaries.add(rs.getDouble("average_salary"));
            }

            rs.close();
            pstmt.close();
            conn.close();

            return 1; // Success
        } catch (SQLException e) {
            System.out.println("Error generating salary report: " + e.getMessage());
            return 0; // Failure
        }
    }

    // Print the salary report
    public void printReport() {
        System.out.println("Salary Report for Year: " + (year == null ? "All" : year));
        System.out.println("===================================================================");
        System.out.printf("%-15s %-20s %-20s%n", "Employee ID", "Total Salary Paid", "Average Salary");
        System.out.println("-------------------------------------------------------------------");

        for (int i = 0; i < employeeIDs.size(); i++) {
            System.out.printf("%-15s %-20.2f %-20.2f%n",
                    employeeIDs.get(i),
                    totalSalaries.get(i),
                    averageSalaries.get(i)
            );
        }
    }

    // Main method to test the salary report
    public static void main(String[] args) {
        salary_report sr = new salary_report();

        // Example: Generate report for the year 2024
        if (sr.generate(2024) == 1) {
            sr.printReport();
        } else {
            System.out.println("Failed to generate the salary report.");
        }
    }
}
