import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import views.MyMenubar;
import views.MyToolBar;
import views.MainFrame;
import views.MyMenuItem;
import views.OptionPanel;
import views.NormGraph;
import views.MyButton;

import controls.db.dbActions;
import controls.triggers.trigger;

public class Merger{
    private MainFrame f;
    private MyMenubar mb;
    private MyToolBar tb;
    private OptionPanel p;
    private NormGraph g;
    
    public Merger() {
        f = new MainFrame(1000, 500);
        mb = new MyMenubar();
        tb = new MyToolBar();
        p = new OptionPanel();

        p.addOption("사과");
        p.addOption("사과");
        p.addOption("사과");
        p.addOption("사과");
        p.addOption("사과");
        p.addOption("사과");

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

        m1.add(new MyMenuItem("New"));
        m1.add(new MyMenuItem("Open CSV File", trigger.csvOpen));
        m1.add(new MyMenuItem("Save CSV File", trigger.csvSave));
        m1.add(new MyMenuItem("Save As..."));
        m1.add(new MyMenuItem("Save Graph As..."));
        m1.add(new MyMenuItem("Exit", e->{f.dispose();System.exit(0);}));

        //
        
        m3.add(new MyMenuItem("Draw Graph As "));
        m3.add(new MyMenuItem("Draw Graph As "));
        m3.add(new MyMenuItem("Draw Graph As "));
        m3.add(new MyMenuItem("Draw Graph As "));
        m3.add(new MyMenuItem("Draw Graph As "));
        m3.add(new MyMenuItem("Show Concentration"));

        //
        m4.add(new MyMenuItem("사과"));
        m4.add(new MyMenuItem("사과"));
        m4.add(new MyMenuItem("사과"));
        m4.add(new MyMenuItem("사과"));
        m4.add(new MyMenuItem("사과"));
        m4.add(new MyMenuItem("사과"));

        m5.add(new MyMenuItem("Welcome"));
        m5.add(new MyMenuItem("View License"));
        m5.add(new MyMenuItem("About"));

        tb.addTool(new MyButton(new ImageIcon("./views/resource/open.png"), KeyEvent.VK_O, trigger.csvOpen));
        tb.addTool(new MyButton(new ImageIcon("./views/resource/save.png"), KeyEvent.VK_S, trigger.csvSave));
        //tb.addTool(new JButton("c"));
        //tb.addTool(new JButton("d"));
        //tb.addTool(new JTextField("e"));
        
        f.setLocation(260,450);
        f.addMenuBar(mb);
        f.addToolBar(tb);
        f.addOptionPanel(p);
        f.setVisible(true);
    }
 
    public static void main(String[] args) {
        Merger m = new Merger();
    }
}
