package server.service;

import lesson_2.dao.UsersDAO;
import server.handler.ClientHandler;
import server.intrface.AuthService;

import java.util.LinkedList;
import java.util.List;

public class AuthServiceImpl implements AuthService {

    private List<UsersDAO> usersList;

    public AuthServiceImpl() {  // Конструктор класса, в котором создаются и хранятся конкретные пользователи
        this.usersList = new LinkedList();
        this.usersList.add(new UsersDAO());
        usersList.add("SELECT * FROM TABLE user_t ")


    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public String getNick(String login, String password) {
        for (UserEntity u : usersList) {

            if (u.login.equals(login) && u.password.equals(password)) {
                return u.nick;
            }

        }
        return null;
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");

    }

    private static class UserEntity { // класс создания записи пользователя
        private String login;
        private String password;
        private String nick;

        public UserEntity(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }
    }
}
