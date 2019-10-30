package controls.db;

import controls.db.dbProperties;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class dbActions{

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        conn = DriverManager.getConnection("jdbc:mysql://localhost:" + this.portNumber + "/" + this.dbName,
                connectionProps);
        return conn;
    }
}