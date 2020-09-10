package server.service;

import lesson_2.DBConn;
import lesson_2.dao.UsersDAO;
import server.handler.ClientHandler;
import server.intrface.AuthService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AuthServiceImpl implements AuthService {


    private PreparedStatement preparedStatement;
    private String nick;

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public String getNick(String login, String password) throws SQLException {
        String str = "SELECT NICK FROM TABLE USER_T WHERE LOGIN =? AND PASS =?";
        try {
            preparedStatement = DBConn
                    .getInstance()
                    .connection()
                    .prepareStatement(str);
            preparedStatement.setString(1, nick);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()) {
            if (nick != null) {
                return str;
            } else {
                return null;
            }
        }
        return str;
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }


}
