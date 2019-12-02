package controls.db;

import controls.CTRL;
import controls.db.dbProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dbActions{
    private Connection conn;
    private dbProperties connectionProps;

    // 생성자
    public dbActions(){
        conn = null;
        connectionProps = new dbProperties();
    }

    //parser String to utf16
    private String parseUTF16(String str){
        String out = "";
        for(int i=0;i<str.length();i++){
            String t = Integer.toHexString(str.charAt(i));
            if(t.length() < 4){
                out += "00"+t;
            }else{
                out += t;
            }
        }
        return out;
    }

    // parse n add to info
    public List<info> parseNadd(String str){
        String tStr = CTRL.parse(str);
        List<info> list = new ArrayList<info>();
        list.add(new info(str, tStr));
        return list;
    }

    // parse All n add to info
    private List<List<info>> parseNaddAll(String name, List<String> list){
        List<List<info>> lists = new ArrayList<List<info>>();
        lists.add(parseNadd(name));
        for (String str : list) {
            lists.add(parseNadd(str));
        }
        return lists;
    }

    // 연결하는 함수 true: connected, false: not connected
    public boolean connect() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(connectionProps.getServerInfo() ,connectionProps);
            if(conn != null)
                return true;
        }catch(ClassNotFoundException e){
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

    // 테이블이 존재하는 지
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
    public void createTable(String name, List<String> header) throws SQLException{
        if(!isTable(name)){
            int length = header.size()-1;
            String query = null;
            Statement statement = null;
            parseNaddAll(name, header);
            statement = createStatement();
            query = "CREATE TABLE " + name + " (\n";
            for (int i = 0; i < length; i++) {
                query += CTRL.parse(header.get(i))+" VARCHAR(20),\n";
            }
            query +=  CTRL.parse(header.get(length))+" VARCHAR(20)\n";
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

    // Insert 
    public void insertIntoTable(String name, List<List<String>> contents, int size) throws SQLException{
        PreparedStatement pstmt = null;

        // System.out.println(query);
        if(isTable(name)){
            for (List<String> list : contents) {
                String query = "INSERT INTO "+name+" VALUES ( '";

                for (int i = 0; i < size-1; i++) {
                    if(i >= list.size()){
                        query += "', '";
                    }else{
                        query += list.get(i)+"', '";
                    }
                }
                if(size-1 >= list.size()){
                    query += "');";
                }else{
                    query += list.get(size-1)+"');";
                }

                // System.out.println(query);
                pstmt = preparedStatement(query);
                if (pstmt.executeUpdate(query) >= 0) {
                    // System.out.println("INSERTED");
                }else{
                    // System.out.println("ERROR");
                    throw new SQLException("insertIntoTable::ERROR");
                }
            }
        }else{
            System.out.println("insertIntoTable::Table does not exist");
        }
    }

    //SELECT cols FROM TABLE name WHERE rows
    public List<List<String>> SelectColsFromTableRows(String name, String[] cols, String[][] rows) throws SQLException{
        PreparedStatement pstmt = null;
        List<List<String>> sList = null;
        int lenCol = cols.length;
        String query = "SELECT ";
        for(int i = 0; i < lenCol-1; i++){
            query += cols[i] + ", ";
        }
        if(rows[0].length == 0 && rows[1].length == 0)
            return null; 

        query += cols[lenCol-1] + " FROM " + name;
        if(rows[0].length == 1){
            query += " WHERE 측정일시 LIKE '" + rows[0][0] + "'";
        }else if(rows[0].length > 1){
            query += " WHERE 측정일시 REGEXP('";
            for(int j = 0; j < rows[0].length-1; j++){
                query += rows[0][j] + "|";
            }
            query += rows[0][rows[0].length-1] + "')";
        }
        if(rows[0].length > 0 && rows[1].length > 0){
            query += " AND";
        }else if(rows[1].length > 0){
            query += " WHERE";
        }

        if(rows[1].length == 1){
            query += " 측정소명 LIKE '" + rows[1][0] + "'";
        }else if(rows[1].length > 1){
            query += " 측정소명 REGEXP('";
            for(int j = 0; j < rows[1].length-1; j++){
                query += rows[1][j] + "|";
            }
            query += rows[1][rows[1].length-1] + "')";
        }
        
        System.out.println(query);
        if(isTable(name)){
            ResultSet rs = null;
            pstmt = preparedStatement(query);
            sList = new ArrayList<List<String>>();
            // System.out.println(query);
            if (pstmt.execute(query)) {
                rs = pstmt.getResultSet();
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                String arr = "";
                List<String> tmpList = new ArrayList<String>();
                for(int i=1;i<columnCount;i++){
                    String str = rs.getString(i);
                    System.out.println(str);
                    if( i == 1 ){
                        arr += str;
                    }else{
                        arr += "," + str;
                    }
                }
                tmpList = Arrays.asList(arr.split(","));
                sList.add(tmpList);
            }
        }else{
            System.out.println("SelectAllFromTable::table does not exists");
        }return null;
    }

    // SELECT args FROM TABLE name
    public List<List<String>> SelectArgsFromTable(String name, String[] args) throws SQLException{
        PreparedStatement pstmt = null;
        List<List<String>> sList = null;
        int n = args.length;
        String query = "SELECT ";
        for(int i = 0; i < n-1; i++){
            query += args[i] + ", ";
        }
        query += args[n-1] + " FROM " + name + ";";
        System.out.println(query);
        if(isTable(name)){
            ResultSet rs = null;
            pstmt = preparedStatement(query);
            sList = new ArrayList<List<String>>();
            // System.out.println(query);
            if (pstmt.execute(query)) {
                rs = pstmt.getResultSet();
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                String arr = "";
                List<String> tmpList = new ArrayList<String>();
                for(int i=1;i<columnCount;i++){
                    String str = rs.getString(i);
                    System.out.println(str);
                    if( i == 1 ){
                        arr += str;
                    }else{
                        arr += "," + str;
                    }
                }
                tmpList = Arrays.asList(arr.split(","));
                sList.add(tmpList);
            }
        }else{
            System.out.println("SelectAllFromTable::table does not exists");
        }return null;
    }

    // SELECT * FROM TABLE name
    public List<List<String>> SelectAllFromTable(String name) throws SQLException{
        PreparedStatement pstmt = null;
        List<List<String>> sList = null;
        String query = "SELECT * FROM " + name + ";";
        System.out.println(query);
        if(isTable(name)){
            ResultSet rs = null;
            pstmt = preparedStatement(query);
            sList = new ArrayList<List<String>>();
            // System.out.println(query);
            if (pstmt.execute(query)) {
                rs = pstmt.getResultSet();
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                String arr = "";
                List<String> tmpList = new ArrayList<String>();
                for(int i=1;i<columnCount;i++){
                    String str = rs.getString(i);
                    System.out.println(str);
                    if( i == 1 ){
                        arr += str;
                    }else{
                        arr += "," + str;
                    }
                }
                tmpList = Arrays.asList(arr.split(","));
                sList.add(tmpList);
            }
        }else{
            System.out.println("SelectAllFromTable::table does not exists");
        }
        return sList;
    }

    //returns List of n row of database
    public List<List<String>> getNRowFromTable(String name, int n) throws SQLException{
        Statement statement = null;
        List<List<String>> sList = null;

        if(isTable(name)){
            ResultSet rs = null;
            statement = createStatement();
            sList = new ArrayList<List<String>>();
            String query = "SELECT * FROM " + name + " LIMIT " + n + " ;";
            // System.out.println(query);
            if (statement.execute(query)) {
                rs = statement.getResultSet();
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                String arr = "";
                List<String> tmpList = new ArrayList<String>();
                for(int i=1;i<columnCount;i++){
                    String str = rs.getString(i);
                    System.out.println(str);
                    if( i == 1 ){
                        arr += str;
                    }else{
                        arr += "," + str;
                    }
                }
                tmpList = Arrays.asList(arr.split(","));
                sList.add(tmpList);
            }
        }else{
            System.out.println("getNStringFromTable::table does not exists");
        }
        return sList;
    }

    // Drops Table
    public void DropTable(String name) throws SQLException{
        Statement statement = null;
        if(isTable(name)){
            statement = createStatement();
            String query = "DROP TABLE " + name;
            // System.out.println(query);
            if (statement.executeUpdate(query) >= 0) {
                System.out.println("DROP");
            }else{
                // System.out.println("ERROR");
                throw new SQLException("DropTable::ERROR");
            }
        }else{
            System.out.println("DropTable::table does not exists");
        }
    }
    // public static void main(String[] args) {
    //     dbActions db = new dbActions();

    //     try{
    //         db.connect();
    //         Statement statement = null;
    //         ResultSet rs = null;
            
    //         // statement = db.createStatement();
    //         // if (statement.execute("SHOW TABLES;")) {
    //         // 	rs = statement.getResultSet();
    //         // }

    
    //         // executeUpdate 함수는 뷰를 업데이트 한다. (= DDL)
    //         // if(statement.executeUpdate("SHOW DATABASES"));
            
    //         // while (rs.next()) {
    //         // 	String str = rs.getNString(1);
    //         //     System.out.println(str);
    //         // }

    //         // if (statement.executeUpdate("") >= 0) {
    //         // 	rs = statement.getResultSet();
    //         // }
    //         // db.createTable("일별평균대기오염도_2018","측정일시,측정소명,이산화질소농도,오존농도,이산화탄소농도,아황산가스,미세먼지,초미세먼지".split(","));

    //         // System.out.println(db.conn.toString());
    //         db.conn.close();
    //     }catch(SQLException e){
    //         e.printStackTrace();
    //     }
    // }

    public static void main(String[] args) {
        dbActions db = new dbActions();
        List<String> header = new ArrayList<>();
        header.add("측정일시");
        header.add("측정소명");
        header.add("이산화질소농도(ppm)");
        header.add("오존농도(ppm)");
        header.add("이산화탄소농도(ppm)");
        header.add("아황산가스(ppm)");
        header.add("미세먼지(㎍/㎥)");
        header.add("초미세먼지(㎍/㎥)");
        System.out.println(header);

        try{
            if(db.connect()){
                // db.DropTable("일별평균대기오염도_2018");
                db.createTable("일별평균대기오염도_2018", header);
                db.insertIntoTable(CTRL.getFileName(), CTRL.getContents(), CTRL.getHeader().size());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class info{
        String origin;
        String parsed;

        info(String origin, String parsed){
            this.origin = origin;
            this.parsed = parsed;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "org:" + origin + " par:" + parsed;
        }
    }
}
