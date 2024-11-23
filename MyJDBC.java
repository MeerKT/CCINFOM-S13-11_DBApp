import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MyJDBC {
    public static void main(String[] args)
    {
     try {
         Connection connection = DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/employeedb",
                 "root",
                 "1234"
         );

         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM projects");

         while (resultSet.next()) {
             System.out.println(resultSet.getString("employee_ID"));
             System.out.println(resultSet.getString("project_ID"));
         }
     }catch(SQLException e) {
         e.printStackTrace();
     }
    }
}
