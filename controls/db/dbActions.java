package controls.db;

import controls.db.dbProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbActions{
    private Connection conn;
    private dbProperties connectionProps;

    // 생성자
    public dbActions(){
        conn = null;
        connectionProps = new dbProperties();
    }

    // 커낵션 생성
    private void getConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(connectionProps.getServerInfo() ,connectionProps);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return;
    }

    // true: connected, false: not connected
    public boolean connect() throws SQLException{
        try{
            getConnection();
            if(conn != null)
                return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    // 구문 생성
    public Statement createStatement() throws SQLException{
        Statement st = null;
        st = conn.createStatement();
        return st;
    }

    // db Create Table
    public void createTable(String name, String[] args) throws SQLException{
        int n = args.length;
        Statement statement = null;
        String query = "CREATE TABLE " + name + " (\n";
        for(int i = 0;i < n-1;i++){
            query += args[i] + " VARCHAR(10),\n";
        }
        query += args[n-1] + " VARCHAR(10)\n";
        query += ");";
        statement = createStatement();
        if (statement.executeUpdate(query) >= 0) {
            System.out.println("CREATED");
        }else{
            System.out.println("ERROR");
        }
        // System.out.println(str);
    }

    public static void main(String[] args) {
        dbActions db = new dbActions();

        try{
            db.connect();
            Statement statement = null;
            ResultSet rs = null;
            
            statement = db.createStatement();
            if (statement.execute("SHOW TABLES;")) {
            	rs = statement.getResultSet();
            }

            // executeUpdate 함수는 뷰를 업데이트 한다. (= DDL)
            // if(statement.executeUpdate("SHOW DATABASES"));
            
            while (rs.next()) {
            	String str = rs.getNString(1);
                System.out.println(str);
            }

            // if (statement.executeUpdate("") >= 0) {
            // 	rs = statement.getResultSet();
            // }
            // createTable("일별평균대기오염도_2018","측정일시,측정소명,이산화질소농도,오존농도,이산화탄소농도,아황산가스,미세먼지,초미세먼지".split(","));

            // System.out.println(db.conn.toString());
            db.conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
