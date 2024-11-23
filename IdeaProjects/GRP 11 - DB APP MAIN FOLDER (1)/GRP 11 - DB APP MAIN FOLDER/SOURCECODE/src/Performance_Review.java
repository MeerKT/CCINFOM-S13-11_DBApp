
import java.sql.*;
import java.util.*;

public class Performance_Review {
    public int reviewID;
    public int employeeID;
    public String reviewDate;
    public int reviewScore;
    public String comments;


    public ArrayList<Integer> reviewIDList = new ArrayList<>();
    public ArrayList<Integer> employeeIDList = new ArrayList<>();
    public ArrayList<String> reviewDateList = new ArrayList<>();
    public ArrayList<Integer> reviewScoreList = new ArrayList<>();
    public ArrayList<String> commentsList = new ArrayList<>();


    public int add_review(int employeeID, int reviewScore, String comments) {
        try{
            Connection conn;
            //establishes connection to a new database: ENSURE THAT YOUR USERNAME AND PASSWORD MATCHES THESE VALUES BEFORE TRYING TO DEBUG
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "WillKill4QP!");
            System.out.println("Connection Successful!");

            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(reviewID) + 1 AS newID FROM performance_review");
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                reviewID = rst.getInt("newID");
            }
            pstmt = conn.prepareStatement("INSERT INTO performance_review (employee_ID, reviewID, review_date,review_score,comments) VALUE(?,?,?,?,?)");
            pstmt.setInt(1, employeeID);
            pstmt.setInt(2,reviewID);
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            pstmt.setDate(3, sqlDate);

            pstmt.setInt(4,reviewScore);
            if(comments!="") {
                pstmt.setString(5,comments);
            }
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;

        }catch(Exception e){
            System.out.println("This isn't working");
            return 0;
        }
    }
}
