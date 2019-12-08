package views;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame{

    public MainFrame(int x, int y){
    	setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        setVisible(true); 
        setLocation(100,100);
        // Watch out for placing setVisible().
        
    }

    @Override
    public void repaint() {
        // TODO Auto-generated method stub
        for (Component Comp : this.getComponents()) {
            Comp.repaint();
        }
    }

    public void change() {
        // TODO Auto-generated method stub
        for (Component Comp : this.getComponents()) {
            Comp.setVisible(!Comp.isVisible());
        }
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
        this.add(new JScrollPane(bar), BorderLayout.CENTER);
    }
    
    public void addBottomBar(BottomBar bar){
        this.add(bar,BorderLayout.SOUTH);
    }
}
