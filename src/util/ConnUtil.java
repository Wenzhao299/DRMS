package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
    //JDBC 驱动器名称和数据库的 URL
    static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    static final String DB_USER_URL="jdbc:mysql://101.132.138.214:3306/USER?serverTimezone=GMT&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    static final String DB_DRMS_URL="jdbc:mysql://101.132.138.214:3306/DRMS?serverTimezone=GMT&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    //数据库用户名和密码
    static final String USER = "idrs";
    static final String PASS = "212194";
    public static Connection getUSERConn() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_USER_URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    public static Connection getDRMSConn() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_DRMS_URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
}
