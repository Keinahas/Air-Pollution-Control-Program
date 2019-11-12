import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import views.MyMenubar;
import views.MyToolBar;
import views.MainFrame;

import controls.db.dbActions;
import controls.csvio.CSVI;
import controls.triggers.csvOpen;


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
        JMenu m3 = new JMenu("View");
        JMenu m4 = new JMenu("Option");
        JMenu m5 = new JMenu("Help");

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        mb.add(m5);

        m1.add(new JMenuItem("New"));
        m1.add(new JMenuItem("Open CSV File"));
        m1.add(new JMenuItem("Save CSV File"));
        m1.add(new JMenuItem("Save As..."));
        m1.add(new JMenuItem("Save Graph As..."));
        m1.add(new JMenuItem("Exit"));

        //
        
        m3.add(new JMenuItem("Draw Graph As "));
        m3.add(new JMenuItem("Draw Graph As "));
        m3.add(new JMenuItem("Draw Graph As "));
        m3.add(new JMenuItem("Draw Graph As "));
        m3.add(new JMenuItem("Draw Graph As "));
        m3.add(new JMenuItem("Show Concentration"));

        //

        m5.add(new JMenuItem("Welcome"));
        m5.add(new JMenuItem("View License"));
        m5.add(new JMenuItem("About"));

        m1.addActionListener(new csvOpen());

        tb.addTool(new JButton("a"));
        tb.addTool(new JButton("b"));
        tb.addTool(new JButton("c"));
        tb.addTool(new JButton("d"));
        tb.addTool(new JTextField("e"));
        
        f.addMenuBar(mb);
        f.addToolBar(tb);
        f.setVisible(true);
    }
 
    public static void main(String[] args) {
        Merger m = new Merger();
    }
}