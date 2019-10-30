package views;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
    public MainFrame(int x, int y){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        // this.setVisible(true); 
        // Watch out for placing setVisible().
        
    }

    public void addMenuBar(MyMenubar bar) {
        this.setJMenuBar(bar);
    }
}