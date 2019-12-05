package views;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame{
    public JPanel p2=new JPanel(new BorderLayout());
    public MainFrame(int x, int y){
        
    	setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        add(p2);
        //setVisible(true); 
        // Watch out for placing setVisible().
        
    }

	public void addMenuBar(MyMenuBar bar) {
        this.setJMenuBar(bar);
    }

	public void addToolBar(MyToolBar bar) {
        p2.add(bar,BorderLayout.NORTH);
    }

    public void addSideBar(SideBar bar){
        p2.add(bar,BorderLayout.WEST);
    }

    public void addGraph(GraphPanel bar){
        p2.add(bar,BorderLayout.CENTER);
    }
    
    public void addBottomBar(BottomBar bar){
        p2.add(bar,BorderLayout.SOUTH);
    }
}
