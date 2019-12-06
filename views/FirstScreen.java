package views;

import javax.swing.*;

import controls.CTRL;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class FirstScreen extends JPanel{
    public FirstScreen(){
        setLayout(null);
        setBackground(Color.pink);
        setLocation(100,100);

        JLabel l1=new JLabel("미세먼지 523배!!");
        l1.setFont(new Font("Serif",Font.BOLD,60));
        l1.setForeground(Color.CYAN);
        l1.setBounds(500,100,500,300);

        add(l1);
    }
}