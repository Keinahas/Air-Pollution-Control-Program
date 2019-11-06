package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MainFrame extends JFrame{

    public MainFrame(int x, int y){
    	this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        // this.setVisible(true); 
        // Watch out for placing setVisible().
        
    }

	public void addMenuBar(MyMenubar bar) {
        this.setJMenuBar(bar);
    }
	public void addToolBar(MyToolBar bar) {
        this.add(bar,BorderLayout.NORTH);
    }
}