package lesson_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class DBConn { //Singleton
    private static DBConn instance;
    private Connection conn;

    private DBConn () throws SQLException {
        /*ResourceBundle rb = ResourceBundle.getBundle("db");
        String host = rb.getString("host");
        String port = rb.getString("port");
        String db = rb.getString("db");*/
        String user = "root";
        String password = "root";

        String url = "jdbc:mysql://127.0.0.1:3306/test";

       /* String jdbcURL = MessageFormat.format("jdbc:mysql://{0}:{1}/{2}", host, port, db);*/

        conn = DriverManager.getConnection(url, user, password);
    }

    public static DBConn getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConn();
        }
        return instance;
    }

    public Connection connection() {
        return conn;
    }
}
