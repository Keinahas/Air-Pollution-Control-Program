package views;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame{

    public MainFrame(int x, int y){
    	this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        // this.setVisible(true); 
        // Watch out for placing setVisible().
        
    }

	public void addMenuBar(MyMenuBar bar) {
        this.setJMenuBar(bar);
    }
	public void addToolBar(MyToolBar bar) {
        this.add(bar,BorderLayout.NORTH);
    }

    public void addSideBar(SideBar bar){
        this.add(bar,BorderLayout.WEST);
    }
    public void addGraph(GraphPanel bar){
        this.add(bar,BorderLayout.CENTER);
    }

    public void addGraph(DrawingPanel bar){
        this.add(bar,BorderLayout.CENTER);
    }
}
