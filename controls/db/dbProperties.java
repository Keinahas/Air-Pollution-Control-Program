package controls.db;

import java.util.Properties;

public class dbProperties extends Properties{
    private String userName = "root";
    private String password = "toor";
    private String serverName = "localhost";
    private String dbName = "aoop";
    private int portNumber = 3306;

    public dbProperties initProperties() {
        dbProperties prop = new dbProperties();
        prop.put("user", this.userName);
        prop.put("password", this.password);
        return this;
    }
    public String getServerInfo(){
        return "jdbc:mysql://localhost:" + this.portNumber + "/" + this.dbName;
    }
}