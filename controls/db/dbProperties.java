package controls.db;

import java.util.Properties;

public class dbProperties extends Properties{
    private String userName = "root";           // username
    private String password = "toor";           // password
    private String hostAddr = "localhost";      // hostAddr
    private String dbName = "aoop";             // database
    private int portNumber = 3306;              // port

    // constructor
    public dbProperties(){
        this.put("user", this.userName);
        this.put("password", this.password);
    }

    // returns dbname
    public String getDBName(){
        return dbName;
    }

    // return Database URL into String
    public String getServerInfo(){
        return "jdbc:mysql://"+this.hostAddr +":"+ this.portNumber + "/" + this.dbName+"?characterEncoding=UTF-8&serverTimezone=UTC";
        // return "jdbc:mysql://"+this.serverName +":"+ this.portNumber + "/" + this.dbName+"?characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    }
}