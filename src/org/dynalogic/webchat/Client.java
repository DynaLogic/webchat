package org.dynalogic.webchat;

import java.awt.*;

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
        Button butt = new Button("heh, butt");
        butt.setBounds(30,100,110,30);
        add(butt);
        setSize(300,300);
        setLayout(null);
        setVisible(true);
    }
}
