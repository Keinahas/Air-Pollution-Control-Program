import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import views.MyMenubar;
import views.MyToolBar;
import views.MainFrame;
import views.MyMenuItem;
import views.OptionPanel;
import views.OptsAddable;
import views.NormGraph;
import views.MyButton;
import views.MyMenu;
import controls.db.dbActions;
import controls.triggers.trigger;

public class Merger{
    private MainFrame f;
    private MyMenubar mb;
    private MyToolBar tb;
    private OptsAddable p;
    private NormGraph g;
    
    public Merger() {
        f = new MainFrame(1000, 500);
        mb = new MyMenubar();
        tb = new MyToolBar();
        p = new OptionPanel();

        OptsAddable m1 = new MyMenu("File");
        OptsAddable m2 = new MyMenu("Edit");
        OptsAddable m3 = new MyMenu("View");
        OptsAddable m4 = new MyMenu("Option");
        OptsAddable m5 = new MyMenu("Help");

        mb.add((MyMenu)m1);
        mb.add((MyMenu)m2);
        mb.add((MyMenu)m3);
        mb.add((MyMenu)m4);
        mb.add((MyMenu)m5);

        m1.addOption("New");
        m1.addOption("Open CSV File", trigger.csvOpen);
        m1.addOption("Save CSV File", trigger.csvSave);
        m1.addOption("Save As...");
        m1.addOption("Save Graph As...");
        m1.addOption("Exit", e->{f.dispose();System.exit(0);});

        //
        
        m3.addOption("Draw Graph As ");
        m3.addOption("Draw Graph As ");
        m3.addOption("Draw Graph As ");
        m3.addOption("Draw Graph As ");
        m3.addOption("Draw Graph As ");
        m3.addOption("Show Concentration");

        // https://sleepyeyes.tistory.com/29
        addOption("사과", (ActionListener)null, p, m4);
        addOption("사과", (ActionListener)null, p, m4);
        addOption("사과", (ActionListener)null, p, m4);
        addOption("사과", (ActionListener)null, p, m4);
        addOption("사과", (ActionListener)null, p, m4);
        addOption("사과", (ActionListener)null, p, m4);

        m5.addOption("Welcome");
        m5.addOption("View License");
        m5.addOption("About");

        tb.addTool(new MyButton(new ImageIcon("./views/resource/open.png"), KeyEvent.VK_O, trigger.csvOpen));
        tb.addTool(new MyButton(new ImageIcon("./views/resource/save.png"), KeyEvent.VK_S, trigger.csvSave));
        //tb.addTool(new JButton("c"));
        //tb.addTool(new JButton("d"));
        //tb.addTool(new JTextField("e"));
        
        f.setLocation(260,450);
        f.addMenuBar(mb);
        f.addToolBar(tb);
        f.addOptionPanel((OptionPanel)p);
        f.setVisible(true);
    }

    private void addOption(String str, ActionListener l, OptsAddable...objs){
        for(OptsAddable obj: objs){
            obj.addOption(str, l);
        }
    }
 
    public static void main(String[] args) {
        Merger m = new Merger();
    }
}