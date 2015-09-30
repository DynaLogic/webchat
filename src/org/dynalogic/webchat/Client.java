package org.dynalogic.webchat;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JohnSmith0508 on 28/9/15 at 11:11 PM.
 */
public class Client {
    JButton exit = new JButton("Exit");
    JButton connect = new JButton("Connect!");
    JButton changeServer = new JButton("Change Server");
    JTextArea output = new JTextArea();

    static String serverName = "http://logan.waldman.ro/Chat/ServerLogic.php";
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public void init(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        output.setEditable(false);
        JButton button = new JButton("Ass");
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            constraints.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            constraints.weightx = 0.5;
        }
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        pane.add(connect, constraints);


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 0;
        pane.add(changeServer, constraints);

        button = new JButton("Button 3");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 2;
        constraints.gridy = 0;
        pane.add(button, constraints);

        //button = new JButton("Long-Named Button 4");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 40;      //make this component tall
        constraints.weightx = 0.0;
        constraints.gridwidth = 5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(output, constraints);


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 0;       //reset to default
        constraints.weighty = 1.0;   //request any extra vertical space
        constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
        constraints.insets = new Insets(10, 0, 0, 0);  //top padding
        constraints.gridx = 1;       //aligned with button 2
        constraints.gridwidth = 2;   //2 columns wide
        constraints.gridy = 2;       //third row
        pane.add(exit, constraints);
    }

    public Client() {
        JFrame frame = new JFrame("Web Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init(frame.getContentPane());
        addEventListners(frame);
        frame.pack();
        frame.setVisible(true);
        while(true)
        {
        	update(frame);
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public static void main(String[] args) {
        new Client();
    }

    private void addEventListners(Container frame) {
        exit.addActionListener(e -> {
            int output = JOptionPane.showConfirmDialog(frame, "Are You sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
            if (output == 0)System.exit(0);
        });

        changeServer.addActionListener(e -> {
            String output = JOptionPane.showInputDialog(frame,"Enter New Server Name:","Change Server",JOptionPane.PLAIN_MESSAGE,null,null,serverName).toString();
            if ((output != null) && (output.length() > 0))
            {
                serverName = output;
                System.out.println("New Server: "+output);
            }
        });
    }
    
    private void update(Container frame)
    {
    	frame.revalidate();
    	frame.repaint();
    }
}