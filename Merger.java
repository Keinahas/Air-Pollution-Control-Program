import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import views.MyMenubar;
import views.MyToolBar;
import views.MainFrame;
import views.OptionPanel;
import views.OptsAddable;
import views.SideBar;
import views.MyButton;
import views.MyMenu;
import views.Graph1;

import controls.CTRL;

// Main
public class Merger{
    private MainFrame f;
    private MyMenubar mb;
    private MyToolBar tb;
    private OptsAddable p;
    private Graph1 g;
    private SideBar sb;
    
    // constructor
    public Merger() {
        f = new MainFrame(1000, 500);
        mb = new MyMenubar();
        tb = new MyToolBar();
        p = new OptionPanel();
        g = new Graph1();
        sb = new SideBar();
      
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
        m1.addOption("Open CSV File", KeyEvent.VK_O, CTRL.CSV_Open);
        m1.addOption("Upload Data", CTRL.DB_Insert);
        m1.addOption("Save CSV File", KeyEvent.VK_S, CTRL.CSV_Save);
        m1.addOption("Save Graph As...");
        m1.addOption("Exit", e->{f.dispose();System.exit(0);});

        m2.addOption("Undo");
        m2.addOption("Redo");
        m2.addOption("Cut");
        m2.addOption("Copy");
        m2.addOption("Paste");
        m2.addOption("Find");

        // https://sleepyeyes.tistory.com/29
        /*
        SideBar sbp = new SideBar();
        OptionPanel p1=new OptionPanel();
        OptionPanel p2=new OptionPanel();
        OptionPanel p3=new OptionPanel();
        OptionPanel p4=new OptionPanel();
        OptionPanel p5=new OptionPanel();
        //addOption("측정일시", (ActionListener)null, p, m4);
        panel.add(new JButton("측정일시"));
        //addOption("측정소명", (ActionListener)null, p, m4);
        panel.add(new JButton("측정소명"));
        p1.addOption("이산화질소농도(ppm)",(ActionListener)null);
        p2.addOption("오존농도(ppm)",(ActionListener)null);
        p3.addOption("이산화탄소농도(ppm)",(ActionListener)null);
        p4.addOption("아황산가스(ppm)",(ActionListener)null);
        p5.addOption("미세먼지(㎍/㎥)",(ActionListener)null);
        sbp.add(panel);
        sbp.add(p1);
        sbp.add(p2);
        sbp.add(p3);
        sbp.add(p4);
        sbp.add(p5);*/
      
        m5.addOption("Welcome");
        m5.addOption("View License");
        m5.addOption("About");

        tb.addTool(new MyButton(new ImageIcon("./views/resource/open.png"), KeyEvent.VK_O, CTRL.CSV_Open));
        tb.addTool(new MyButton(new ImageIcon("./views/resource/save.png"), KeyEvent.VK_S, CTRL.CSV_Save));
        //tb.addTool(new JButton("c"));
        //tb.addTool(new JButton("d"));
        //tb.addTool(new JTextField("e"));
        
        f.setLocation(260,450);
        f.addMenuBar(mb);
        f.addToolBar(tb);
        f.addGraph(g);
        f.addSideBar(sb);
        f.setVisible(true);

        
    }

    // call addoptions for objs
    private void addOption(String str, ActionListener l, OptsAddable...objs){
        for(OptsAddable obj: objs){
            obj.addOption(str, l);
        }
    }
 
    public static void main(String[] args) {
        Merger m = new Merger();
    }
}
