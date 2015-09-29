package org.dynalogic.webchat;
//TODO fix .gitignore
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by JohnSmith0508 on 28/9/15 at 10:10 PM.
 */
public class Client extends Frame
{
    public static void main(String[] args)
    {
        Client client = new Client();
    }
    public Client() throws HeadlessException
    {
        init();
        setSize(300,300);
        setLayout(null);
        setVisible(true);
    }
    private void init()
    {
        Button exit = new Button("Exit");
        exit.setBounds(0,20,80,30);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exit);
    }
}
