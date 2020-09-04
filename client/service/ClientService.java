package client.service;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientService extends JFrame {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String myNick;

    JTextField textField = new JTextField();
    JTextArea chatArea = new JTextArea();

    Window chatWindow = new Window();

    public ClientService() {

        chatWindow.chatWindow(); //вызываем метод для создания графического окна
            onAuthClick();
    }

    public void start() {
            myNick = "";
            try {
                socket = new Socket("localhost", 8181);
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());
                setAuthorized(false);
                //long beginTime = System.currentTimeMillis();
                Thread t1 = new Thread(() -> {
                    try {
                        while (true) {                           // цикл авторизации
                            String strMsg = inputStream.readUTF();
                            if (strMsg.startsWith("/authOK")) {
                                setAuthorized(true);
                                myNick = strMsg.split("\\s")[1];
                                break;
                            }
                            chatArea.append(strMsg + "\n");
                        }
                        while (true) {                            // цикл проверки приходящего сообщения с сервера.
                            String strMsg = inputStream.readUTF();
                            if (strMsg.equals("/exit")) {           //Если возвоащается /exit, то выход из цикла и на переход на блок finally
                                break;
                            }
                            chatArea.append(strMsg + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            setAuthorized(false);
                            socket.close();
                            myNick = "";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t1.setDaemon(true);
                t1.start();
            } catch (IOException e) {
                chatArea.append("Failed server connection ");
                e.printStackTrace();
            }
        }

    private void sendMsg() {  // метод отправляет сообщения из строки ввода на сервер
        try {
            outputStream.writeUTF(textField.getText());
            textField.setText("");
        } catch (IOException e) {
            System.out.println("Ошибка отправки сообщения");   //Срабатывает, когда пользователь вышел из чата или сервер недоступен
        }
    }

    public void onAuthClick() {
        if (socket == null || socket.isClosed())
            start();
        sendMsg();
    }

       /* public void onAuthClick() {
            if (socket == null || socket.isClosed()) {
                start();
            }
             try {
                outputStream.writeUTF("/auth" + loginField.getText() + passField.getText());
                 loginField.setText("");
                 passField.setText("");
             } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        private void setAuthorized(boolean b) {
            if (b){
                System.out.println("Client is logged in");
            } else {
                System.out.println("Client isn't logged in");
            }
        }

}