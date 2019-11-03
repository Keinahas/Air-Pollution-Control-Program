package controls.db;

import controls.db.dbProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbActions{

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        dbProperties connectionProps = new dbProperties();

        conn = DriverManager.getConnection(connectionProps.getServerInfo() ,connectionProps);
        return conn;
    }
}