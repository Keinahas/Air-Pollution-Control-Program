package views;

import javax.swing.JFrame;

public class debugging{

    public JFrame createFrame(){
        JFrame tempFrame = new JFrame();
        tempFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tempFrame.setVisible(true);
        return tempFrame;
    }
}