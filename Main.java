import lesson_2.DBConn;
import lesson_2.User;
import lesson_2.dao.UsersDAO;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws SQLException {

        UsersDAO usersDAO = new UsersDAO();
        DBConn.getInstance().connection().setAutoCommit(false);
        try {
            usersDAO.addUser(new User("Bob", "184", "bob"));
            usersDAO.addUser(new User("Lotr", "gh7", "lotr"));
            usersDAO.addUser(new User("Saj", "6d4", "saj"));
            DBConn.getInstance().connection().commit();
        } catch (Throwable e) {
            e.printStackTrace();
            DBConn.getInstance().connection().rollback();
        }
        System.out.println(usersDAO.getAllUsers());
       /* DBConn
                .getInstance()
                .connection()
                .prepareStatement("CREATE TABLE USER_T (\n" +
                        "    LOGIN VARCHAR(30) NOT NULL UNIQUE,\n" +
                        "    PASS VARCHAR(16) NOT NULL,\n" +
                        "    NICK VARCHAR(20) NOT NULL UNIQUE\n" +
                        " );").execute();*/
    }
}
