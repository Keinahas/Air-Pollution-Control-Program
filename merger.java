import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import views.MyMenubar;
import views.MyToolBar;
import views.MainFrame;

import controls.db.dbActions;
import controls.csvio.CSVI;


public class Merger{
    private MainFrame f;
    private MyMenubar mb;
    private MyToolBar tb;
    
    public Merger() {
        f = new MainFrame(1000, 500);
        mb = new MyMenubar();
        tb = new MyToolBar();
        
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Edit");  
        mb.add(m1);
        mb.add(m2);
        m1.add(new JMenuItem("New"));
        m1.add(new JMenuItem("Open File"));
        m1.add(new JMenuItem("Save"));
        m1.add(new JMenuItem("Save As..."));
        m1.add(new JMenuItem("Restart"));
        m1.add(new JMenuItem("Exit"));
        
        tb.addTool(new JButton("A"));
        tb.addTool(new JButton("B"));
        tb.addTool(new JButton("C"));
        tb.addTool(new JButton("D"));
        tb.addTool(new JTextField("E"));
        
        f.addMenuBar(mb);
        f.addToolBar(tb);
        f.setVisible(true);
    }
 
    public static void main(String[] args) {
        Merger m = new Merger();
    }
}