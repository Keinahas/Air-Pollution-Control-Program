package views;

import javax.swing.JFrame;


public class mainFrame extends JFrame{
    public mainFrame(int x, int y){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        this.setVisible(true);
    }
}