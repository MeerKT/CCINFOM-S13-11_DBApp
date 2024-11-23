import java.sql.*;
import java.util.ArrayList;

public class performance_report {

    private Integer year; // Year filter for the report
    private ArrayList<String> employeeIDs = new ArrayList<>();
    private ArrayList<Double> avgPerformanceScores = new ArrayList<>();
    private ArrayList<Double> promotionRates = new ArrayList<>();

    // Constructor
    public performance_report() {
        clearReport();
    }

    // Clear all report data
    public void clearReport() {
        year = null;
        employeeIDs.clear();
        avgPerformanceScores.clear();
        promotionRates.clear();
    }

    // Generate the performance review report
    public int generate(Integer year) {
        this.year = year;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");
            System.out.println("Connection Successful!");

            // SQL query
            String query = "SELECT pr.employee_ID, " +
                    "       AVG(pr.review_score) AS avg_performance_score, " +
                    "       SUM(CASE " +
                    "               WHEN pr.review_score >= 80 THEN 100 " +
                    "               WHEN pr.review_score BETWEEN 60 AND 79 THEN 50 " +
                    "               ELSE 0 " +
                    "           END) / COUNT(*) AS promotion_rate, " +
                    "       YEAR(pr.review_date) AS review_year " +
                    "FROM performance_review pr " +
                    "WHERE (YEAR(pr.review_date) = ? OR ? IS NULL) " +
                    "GROUP BY pr.employee_ID, review_year " +
                    "ORDER BY pr.employee_ID ASC";

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
                avgPerformanceScores.add(rs.getDouble("avg_performance_score"));
                promotionRates.add(rs.getDouble("promotion_rate"));
            }

            rs.close();
            pstmt.close();
            conn.close();

            return 1; // Success
        } catch (SQLException e) {
            System.out.println("Error generating performance review report: " + e.getMessage());
            return 0; // Failure
        }
    }

    // Print the performance review report
    public void printReport() {
        System.out.println("Performance Review Report for Year: " + (year == null ? "All" : year));
        System.out.println("==========================================================================");
        System.out.printf("%-15s %-25s %-25s%n", "Employee ID", "Avg Performance Score", "Promotion Rate (%)");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < employeeIDs.size(); i++) {
            System.out.printf("%-15s %-25.2f %-25.2f%n",
                    employeeIDs.get(i),
                    avgPerformanceScores.get(i),
                    promotionRates.get(i)
            );
        }
    }

    // Main method to test the performance review report
    public static void main(String[] args) {
        performance_report pr = new performance_report();

        // Example: Generate report for the year 2023
        if (pr.generate(2023) == 1) {
            pr.printReport();
        } else {
            System.out.println("Failed to generate the performance review report.");
        }
    }
}
