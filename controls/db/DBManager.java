package controls.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager{
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/aoop";
        
    private final String USER_NAME = "root";
    private final String PASSWORD = "toor";

    private Connection connection = null;
    private Statement statement = null;

    public DBManager(){
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            statement = connection.createStatement();

            String sql; //SQL문을 저장할 String
            sql = "SELECT * FROM student";
            ResultSet rs = statement.executeQuery(sql); //SQL문을 전달하여 실행
                        
            while(rs.next()){
                String number = rs.getString("_number");
                String name = rs.getString("name");
                String kor = rs.getString("kor");
                String math = rs.getString("math");
                String eng = rs.getString("eng");
                System.out.println("Number: "+ number + "\nName: " + name + "\nKOR: " + kor); 
                System.out.println("MATH: "+ math + "\nENG: " + eng + "\n-------------\n");
            }
                
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException sqlException){
            //TODO: handle exception
            System.out.println(sqlException.getErrorCode());

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.toString());
        }

    }

    public static void main(String[] args) {
        DBManager db = new DBManager();
    }
}