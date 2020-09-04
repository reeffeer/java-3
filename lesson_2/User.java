package lesson_2;

public class User {

    private String login;
    private String password;
    private String nick;

    public User() {

    }

    public User(String login, String password, String nick) {
        this.login = login;
        this.password = password;
        this.nick = nick;
    }

    public String getLogin() {
        return login;
    }
    public String getPass() {
        return password;
    }

    public String getNick() {
        return nick;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pass='" + password + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}
