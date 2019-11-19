package controls.db;

import controls.db.dbProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

    //parser
    private String parseUTF16(String in){
        String out = "";
        for(int i=0;i<in.length();i++){
            String t = Integer.toHexString(in.charAt(i));
            if(t.length() < 4){
                out += "00"+t;
            }else{
                out += t;
            }
        }
        return out;
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
        Statement statement = null;
        statement = conn.createStatement();
        return statement;
    }

    // 구문 생성
    public PreparedStatement preparedStatement(String query) throws SQLException{
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement(query);
        return pstmt;
    }

    //
    public boolean isTable(String name) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SHOW TABLES";
        pstmt = preparedStatement(query);

        if (pstmt.execute(query)) {
            rs = pstmt.getResultSet();
        }

        while (rs.next()) {
        	String str = rs.getNString(1);
            if(str.equals(name)){
                return true;
            }
        }
        return false;
    }

    // db Create Table
    public void createTable(String name, int size) throws SQLException{
        Statement statement = null;
        if(!isTable(name)){
            statement = createStatement();
            String query = "CREATE TABLE " + name + " (\n";
            for(int i = 0;i < size-1;i++){
                query += "args"+i+" VARCHAR(20),\n";
            }
            query +=  "args"+(size-1)+" VARCHAR(20)\n";
            query += ");";
            // System.out.println(query);
            if (statement.executeUpdate(query) >= 0) {
                System.out.println("CREATED");
            }else{
                System.out.println("ERROR");
            }
        }else{
            System.out.println("table already exists");
        }
    }

    public void insertIntoTable(String name, String[] args, int size) throws SQLException{
        int n = args.length;
        PreparedStatement pstmt = null;

        // System.out.println(query);
        if(isTable(name)){
            String query = "INSERT INTO "+name+" VALUES('";
            for(int i = 0; i < size-1; i++){
                if(n <= i){
                    query += "','";
                }else{
                    query += args[i]+"', '";
                }
            }
            if(n <= size-1){
                query += "');";
            }else{
                query += args[size-1]+"');";
            }
            // System.out.println(query);
            pstmt = preparedStatement(query);
            if (pstmt.executeUpdate(query) >= 0) {
                // System.out.println("INSERTED");
            }else{
                // System.out.println("ERROR");
                throw new SQLException("insertIntoTable::ERROR");
            }
        }else{
            System.out.println("insertIntoTable::Table does not exist");
        }
    }

    public void DropTable(String name) throws SQLException{
        Statement statement = null;
        if(isTable(name)){
            statement = createStatement();
            String query = "DROP TABLE " + name;
            // System.out.println(query);
            if (statement.executeUpdate(query) >= 0) {
                System.out.println("DROP");
            }else{
                System.out.println("ERROR");
            }
        }else{
            System.out.println("DropTable::table does not exists");
        }
    }

    public static void main(String[] args) {
        dbActions db = new dbActions();

        try{
            db.connect();
            Statement statement = null;
            ResultSet rs = null;
            
            // statement = db.createStatement();
            // if (statement.execute("SHOW TABLES;")) {
            // 	rs = statement.getResultSet();
            // }

            // executeUpdate 함수는 뷰를 업데이트 한다. (= DDL)
            // if(statement.executeUpdate("SHOW DATABASES"));
            
            // while (rs.next()) {
            // 	String str = rs.getNString(1);
            //     System.out.println(str);
            // }

            // if (statement.executeUpdate("") >= 0) {
            // 	rs = statement.getResultSet();
            // }
            // db.createTable("일별평균대기오염도_2018","측정일시,측정소명,이산화질소농도,오존농도,이산화탄소농도,아황산가스,미세먼지,초미세먼지".split(","));

            // System.out.println(db.conn.toString());
            db.conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
