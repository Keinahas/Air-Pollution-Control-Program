package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstScreen extends JPanel{
    public FirstScreen(){
        setLayout(null);
        //setPreferredSize(new Dimension(1500,800));
        setBackground(Color.pink);
        setLocation(100,100);

        //JPanel p1=new JPanel();
        
        
        //p1.setLayout(new BorderLayout());
        //p1.setBackground(Color.pink);

        JLabel l1=new JLabel("미세먼지 523배!!");
        l1.setFont(new Font("Serif",Font.BOLD,60));
        l1.setForeground(Color.CYAN);
        l1.setBounds(500,100,500,300);

        JButton b1=new JButton("노무현");
        b1.setBounds(600,400,250,100);

        ActionListener listener =new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        };
        b1.addActionListener(listener);

        add(l1);
        add(b1);
    }
}