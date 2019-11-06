package controls.db;

import java.util.Properties;

public class dbProperties extends Properties{
    private String userName = "root";
    private String password = "toor";
    private String serverName = "localhost";
    private String dbName = "aoop";
    private int portNumber = 3306;

    dbProperties(){
        this.put("user", this.userName);
        this.put("password", this.password);
    }

    public String getServerInfo(){
        return "jdbc:mysql://"+this.serverName + this.portNumber + "/" + this.dbName+"?characterEncoding=UTF-8&serverTimezone=UTC";
    }
}