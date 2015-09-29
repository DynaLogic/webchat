package org.dynalogic.webchat;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JohnSmith0508 on 28/9/15 at 11:11 PM.
 */
public class Client extends JApplet
{
    public Client()
    {
        JButton exit = new JButton("Exit");
        JTextArea outputStream = new JTextArea("hello this is a test made for testing porpuses");
        JTextField username = new JTextField("Name");
        JTextField server = new JTextField("logan.waldman.ro");

        outputStream.setColumns(50);
        outputStream.setRows(35);
        outputStream.setEditable(false);
        exit.addActionListener(e -> System.exit(0));
        server.setHorizontalAlignment(JLabel.LEFT);
        username.setHorizontalAlignment(JLabel.RIGHT);

        getContentPane().add(username,BorderLayout.PAGE_START);
        getContentPane().add(server,BorderLayout.PAGE_START);
        getContentPane().add(outputStream);
        getContentPane().add(exit, BorderLayout.PAGE_END);
    }
    public void init()
    {
        JFrame window = new JFrame("Web Chat");
        Client content = new Client();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Client().init();
    }
}
