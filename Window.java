package client.service;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

public class Window extends JFrame {

    private int width = 600;
    private int sizeX = 300;
    private int sizeY = 300;
    private DataOutputStream outputStream;

    public void chatWindow() {
        setTitle("Reef's Chat Demo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 200, width, sizeY);
        setLayout(new BorderLayout());

        //создаем панели
        JPanel dialogPanel = new JPanel();
        JPanel messagePanel = new JPanel();

        //создаем поле отображения диалогов
        JTextArea dialogArea = new JTextArea();
        dialogArea.setBackground(new Color (200, 250, 250));
        dialogArea.setEditable(false);
        dialogArea.setLineWrap(true);
        dialogArea.setWrapStyleWord(true);
        dialogArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JScrollPane scrollPane = new JScrollPane(dialogArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //создаем поле ввода сообщения
        JTextField textField = new JTextField();
        //textField.addActionListener(e -> sendMessage(textField, dialogArea));


        //Создаем кнопку для отправки сообщения
        JButton send = new JButton("Send your message!");
        send.addActionListener(e -> sendMsg(textField));



        //размещаем элементы на панелях
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.X_AXIS));
        dialogPanel.add(scrollPane);
        dialogPanel.setSize(sizeX, sizeY);

        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));
        messagePanel.add(textField);
        messagePanel.add(send);
        messagePanel.setSize(width - sizeX, sizeY);

        getContentPane().add(messagePanel, BorderLayout.SOUTH);
        getContentPane().add(dialogPanel, BorderLayout.CENTER);

        setVisible(true);

    }
    private void sendMsg(JTextField textField) {  // метод отправляет сообщения из строки ввода на сервер
        try {
            outputStream.writeUTF(textField.getText());
           // textField.addActionListener((ActionListener) this.outputStream);
            textField.setText("");

        } catch (IOException e) {
            System.out.println("Ошибка отправки сообщения");   //Срабатывает, когда пользователь вышел из чата или сервер недоступен
        }
    }
    private  void sendMessage(JTextField textField, JTextArea dialogArea) {
        if (textField.getText().equals("")) {
            return;
        }
        dialogArea.append("\n" + textField.getText());
        textField.setText("");
    }
}
