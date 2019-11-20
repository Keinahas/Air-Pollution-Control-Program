import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import views.MyMenubar;
import views.MyToolBar;
import views.MainFrame;
import views.OptionPanel;
import views.OptsAddable;
import views.NormGraph;
import views.MyButton;
import views.MyMenu;
import views.Graph1;

// import controls.db.dbActions;
import controls.triggers.trigger;

public class Merger{
    private MainFrame f;
    private MyMenubar mb;
    private MyToolBar tb;
    private OptsAddable p;
    private Graph1 g;
    
    public Merger() {
        f = new MainFrame(1000, 500);
        mb = new MyMenubar();
        tb = new MyToolBar();
        p = new OptionPanel();
        g = new Graph1();
        JPanel panel = new JPanel(new GridLayout(0,2));
        panel.setBorder(new TitledBorder(new LineBorder(Color.gray,5)));

        OptsAddable m1 = new MyMenu("File", KeyEvent.VK_F);
        OptsAddable m2 = new MyMenu("Edit", KeyEvent.VK_E);
        OptsAddable m3 = new MyMenu("View", KeyEvent.VK_V);
        OptsAddable m4 = new MyMenu("Option", KeyEvent.VK_O);
        OptsAddable m5 = new MyMenu("Help", KeyEvent.VK_H);

        mb.add((MyMenu) m1);
        mb.add((MyMenu) m2);
        mb.add((MyMenu) m3);
        mb.add((MyMenu) m4);
        mb.add((MyMenu) m5);

        m1.addOption("New", KeyEvent.VK_N);
        m1.addOption("Open CSV File", KeyEvent.VK_O, trigger.csvOpen);
        m1.addOption("Save CSV File", KeyEvent.VK_S, trigger.csvSave);
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

        m2.addOption("Undo");
        m2.addOption("Redo");
        m2.addOption("Cut");
        m2.addOption("Copy");
        m2.addOption("Paste");
        m2.addOption("Find");

        // https://sleepyeyes.tistory.com/29
        addOption("사과", (ActionListener)null, p, m4);
        panel.add(new JCheckBox());
        panel.add(new JLabel("사과"));
        addOption("사과", (ActionListener)null, p, m4);
        panel.add(new JCheckBox());
        panel.add(new JLabel("사과"));
        addOption("사과", (ActionListener)null, p, m4);
        panel.add(new JCheckBox());
        panel.add(new JLabel("사과"));
        addOption("사과", (ActionListener)null, p, m4);
        panel.add(new JCheckBox());
        panel.add(new JLabel("사과"));
        addOption("사과", (ActionListener)null, p, m4);
        panel.add(new JCheckBox());
        panel.add(new JLabel("사과"));
        addOption("사과", (ActionListener)null, p, m4);
        panel.add(new JCheckBox());
        panel.add(new JLabel("사과"));

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
        f.addGraph(g);
        f.add(panel,BorderLayout.EAST);
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
