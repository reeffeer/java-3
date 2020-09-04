package lesson_2.dao;

import lesson_2.DBConn;
import lesson_2.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private PreparedStatement preparedStatement = null;

    public void addUser(User user) throws SQLException {
        DBConn
                .getInstance()
                .connection()
                .prepareStatement("INSERT INTO users_t (login, pass, nick) VALUE (?, ?, ?)");
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPass());
                preparedStatement.setString(3, user.getNick());
                boolean a= preparedStatement.execute();
    }

    public User getUserByNick(String nick) throws SQLException {
        DBConn
                .getInstance()
                .connection()
                .prepareStatement("SELECT * FROM user_t WHERE nick = ?");
        preparedStatement.setString(1, nick);
        ResultSet set = preparedStatement.executeQuery();

        User user = new User();
        if (set.next()) {
            user.setLogin(set.getString("LOGIN"));
            user.setPassword(set.getString("PASS"));
            user.setNick(set.getString("NICK"));
        }
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>(2);
         preparedStatement = DBConn
                 .getInstance()
                 .connection()
                 .prepareStatement("SELECT * FROM user_t");
         ResultSet set = preparedStatement.executeQuery();
         while (set.next()) {
             User user = new User();
             user.setLogin(set.getString("LOGIN"));
             user.setPassword(set.getString("PASS"));
             user.setNick(set.getString("NICK"));
             list.add(user);
         }
         return list;
    }
}
