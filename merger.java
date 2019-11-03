// import java.awt.BorderLayout;
// import java.awt.GridLayout;

import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JMenu;
// import javax.swing.JPanel;
import javax.swing.JTextField;
// import javax.swing.JToolBar;

// import views.*;
import views.MyMenubar;
import views.MyToolBar;
import views.MainFrame;

import controls.db.*;


public class Merger{
    private MainFrame f;
    private MyMenubar mb;
    private MyToolBar tb;
    
    public Merger() {
        f = new MainFrame(1000, 500);
        mb = new MyMenubar();
        tb =new MyToolBar();
        
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Edit");  
        mb.add(m1);
        mb.add(m2);
        
        tb.addTool(new JButton("박수원"));
        tb.addTool(new JButton("조성원"));
        tb.addTool(new JButton("임승진"));
        tb.addTool(new JButton("현승환"));
        tb.addTool(new JTextField("노무현"));
        
        f.addMenuBar(mb);
        f.addToolBar(tb);
        f.setVisible(true);
    }
 
    public static void main(String[] args) {
        // Merger m = new Merger();
        new Merger();
    }
}