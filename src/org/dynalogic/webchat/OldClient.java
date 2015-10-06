package org.dynalogic.webchat;
//TODO fix .gitignore
import java.awt.*;

/**
 * Created by JohnSmith0508 on 28/9/15 at 10:10 PM.
 */
public class OldClient extends Frame
{
    public static void main(String[] args)
    {
        OldClient oldClient = new OldClient();
    }
    public OldClient() throws HeadlessException
    {
        init();
        setSize(500,300);
        setLayout(null);
        setVisible(true);
    }
    private void init()
    {
        Button exit         = new Button("Exit");
        TextArea mainOut    = new TextArea("melm");
        TextField name      = new TextField("Name");

        name.setBounds(83,20,100,30);
        mainOut.setEditable(false);
        mainOut.setBounds(1,55,498,150);
        exit.setBounds(0,20,80,30);

        exit.addActionListener(e -> System.exit(0));

        add(exit);
        add(name);
        add(mainOut);
    }
}
