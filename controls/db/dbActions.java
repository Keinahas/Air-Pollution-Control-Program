package controls.db;

import controls.db.dbProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbActions{
    private Connection conn = null;
    dbProperties connectionProps = null;

    public dbActions(){
        connectionProps = new dbProperties();
    }

    public Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(connectionProps.getServerInfo() ,connectionProps);
        return conn;
    }

    public static void main(String[] args) {
        dbActions db = new dbActions();
        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = db.getConnection();

            Statement statement = null;
            ResultSet rs = null;
            
            statement = conn.createStatement();
            if (statement.execute("SHOW DATABASES")) {
				rs = statement.getResultSet();
            }
            
            while (rs.next()) {

				String str = rs.getNString(1);

				System.out.println(str);

            }
        } catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException.getLocalizedMessage());
        } catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());

		}
    }
}
