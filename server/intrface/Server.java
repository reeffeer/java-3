package server.intrface;

import server.handler.ClientHandler;

public interface Server {
    int PORT = 8181;

    boolean isNickBusy(String nick);

    void broadcastMsg(String msg); //от кого и кому идет сообщение

    void subscribe(ClientHandler client); //пользователь подписывается на получение уведомлений

    void unsubscribe(ClientHandler client);

    AuthService getAuthService();

    void sendPrivateMsg(ClientHandler fromClient, String toClient, String msg);

    //void sendMsgToClient(ClientHandler from, String to, String msg);

    //отправка сообщения
    void sendMsgToClient(ClientHandler from, String toNick, String msg);

    default void broadcastClientList() {

    }


}
