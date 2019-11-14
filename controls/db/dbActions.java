package controls.db;

import controls.db.dbProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbActions{
    private Connection conn;
    dbProperties connectionProps;

    public dbActions(){
        conn = null;
        connectionProps = new dbProperties();
    }

    private void getConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(connectionProps.getServerInfo() ,connectionProps);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return;
    }

    //true: connected, false: not connected
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

    public Statement createStatement() throws SQLException{
        Statement st = null;
        st = conn.createStatement();
        return st;
    }

    public static void main(String[] args) {
        dbActions db = new dbActions();

        try{
            db.connect();
            Statement statement = null;
            ResultSet rs = null;
            
            statement = db.createStatement();
            if (statement.execute("SHOW DATABASES")) {
            	rs = statement.getResultSet();
            }

            // executeUpdate 함수는 뷰를 업데이트 한다.
            // if(statement.executeUpdate("SHOW DATABASES"));
            
            while (rs.next()) {
            	String str = rs.getNString(1);
                System.out.println(str);
            }

            System.out.println(db.conn.getClientInfo());
            System.out.println(db.conn.toString());
            db.conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
