package client.service;

import jdk.tools.jlink.internal.Platform;

import javax.swing.*;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.management.PlatformManagedObject;
import java.net.Socket;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClientService extends JFrame {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String myNick;
    private ArrayList<String> history;
    private boolean isClosed = false;

    JTextField textField = new JTextField();
    JTextArea chatArea = new JTextArea();

    Window chatWindow = new Window();
    private DataInputStream dis;
    private DataOutputStream dos;

    public ClientService() {

        chatWindow.chatWindow(); //вызываем метод для создания графического окна
        onAuthClick();
    }

    public void start() {
        myNick = "";
        try {
            socket = new Socket("localhost", 8181);
            inputStream = new DataInputStream(socket.getInputStream());//входящий поток считывания
            outputStream = new DataOutputStream(socket.getOutputStream());//исходящий поток
            setAuthorized(false);
            //long beginTime = System.currentTimeMillis();
            Thread t1 = new Thread(() -> {
                try {
                    while (true) {                           // цикл авторизации
                        String strMsg = inputStream.readUTF();
                        if (strMsg.startsWith("/authOK")) {
                            setAuthorized(true);
                            history = new ArrayList<>();
                            BufferedReader reader = new BufferedReader(new FileReader("src/client/resources/History.txt"));
                            while (reader.ready()) {
                                history.add(reader.readLine());
                            }
                            if (history.size() > 100) {
                                chatArea.setText(String.join("\n", history.subList(history.size() - 100, history.size() - 1)) + "\n\n" + "The end" + "\n\n");
                            } else {
                                chatArea.setText(String.join("\n", history.subList(0, history.size() - 1)) + "\n\n" + "End of history" + "\n\n");
                            }
                            myNick = strMsg.split("\\s")[1];
                            break;
                        } else if (strMsg.equals("/disconnect")) {
                            closeConnection();
                            return;
                        }
                        chatArea.append(strMsg + "\n");
                    }
                    while (true) {                           // цикл проверки приходящего сообщения с сервера.
                        String strMsg = inputStream.readUTF();
                        if (strMsg.equals("/exit")) {           //Если возвоащается /exit, то выход из цикла и на переход на блок finally
                            break;
                        }
                    }
                    String strMsg = inputStream.readUTF();
                    chatArea.append(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")) + " " + strMsg + "\n");
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/client/resources/History.txt", true));
                    writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss"))+" "+ strMsg +"\n");

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
        if (b) {
            System.out.println("Client is logged in");
        } else {
            System.out.println("Client isn't logged in");
        }
    }

    private void closeConnection() {
        isClosed = true;
        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}