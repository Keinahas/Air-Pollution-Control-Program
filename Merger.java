import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import views.MyMenuBar;
import views.MyToolBar;
import views.MainFrame;
import views.OptsAddable;
import views.SideBar;
import views.MyButton;
import views.MyMenu;
import views.GraphPanel;
import views.FirstScreen;
import controls.CTRL;

// Main
public class Merger{
    
    // constructor
    public Merger() {
        FirstScreen fs=new FirstScreen();
        MainFrame frame = CTRL.frame;
        MyMenuBar mb = CTRL.menuBar;
        MyToolBar tb = CTRL.toolBar;
        SideBar sb = CTRL.sideBar;
        GraphPanel gPanel = CTRL.gPanel;
        Timer t = new Timer();
        TimerTask task;

    
        OptsAddable m1 = new MyMenu("File", KeyEvent.VK_F);
        OptsAddable m2 = new MyMenu("Edit", KeyEvent.VK_E);
        OptsAddable m3 = new MyMenu("View", KeyEvent.VK_V);
        // OptsAddable m4 = new MyMenu("Option", KeyEvent.VK_O);
        // OptsAddable m5 = new MyMenu("Help", KeyEvent.VK_H);

        mb.add((MyMenu) m1);
        mb.add((MyMenu) m2);
        mb.add((MyMenu) m3);
        // mb.add((MyMenu) m4);
        // mb.add((MyMenu) m5);

        // m1.addOption("New", KeyEvent.VK_N);
        m1.addOption("Open CSV File", KeyEvent.VK_O, CTRL.CSV_Open);
        m1.addOption("Upload Data", CTRL.DB_Insert);
        m1.addOption("Save CSV File", KeyEvent.VK_S, CTRL.CSV_Save);
        m1.addOption("Save Graph As...", CTRL.Image);
        m1.addOption("Exit", e->{frame.dispose();System.exit(0);});

        m2.addOption("Show New DataTable",CTRL.T_New_Show);
        // m2.addOption("Show DataTable Below", CTRL.T_Blw_Show);

        m3.addOption("DrawBarGraph", CTRL.DrawBarGraph);
        // m3.addOption("DrawLineGraph", CTRL.DrawLineGraph);
    
        // m5.addOption("Welcome");
        // m5.addOption("View License");
        // m5.addOption("About");

        tb.addTool(new MyButton(new ImageIcon("./views/resource/open.png"), KeyEvent.VK_O, CTRL.CSV_Open));
        tb.addTool(new MyButton(new ImageIcon("./views/resource/save.png"), KeyEvent.VK_S, CTRL.CSV_Save));
        
        frame.setLocation(100,100);


        frame.addMenuBar(mb);
        frame.addToolBar(tb);
        frame.addSideBar(sb);
        frame.addGraph(gPanel);
        mb.setVisible(false);
        tb.setVisible(false);
        sb.setVisible(false);
        gPanel.setVisible(false);
        task = new TimerTask(){
        
            @Override
            public void run() {
                // TODO Auto-generated method stub
                mb.setVisible(true);
                tb.setVisible(true);
                sb.setVisible(true);
                gPanel.setVisible(true);
            }
        };

        t.schedule(task, 1000);

        
        // frame.add(frame.p2);
    }

    // call addoptions for objs
    private void addOption(String str, ActionListener l, OptsAddable...objs){
        for(OptsAddable obj: objs){
            obj.addOption(str, l);
        }
    }

    class Init extends Thread {
        public void run(){
            try{
                CTRL.setConnected(CTRL.DB.connect());
                if(CTRL.isConnected()){
                    System.out.println("CONNECTED");
                }else{
                    System.out.println("NOT CONNECTED");
                }
            }catch(SQLException exception){
                exception.printStackTrace();
            }
        } 
    }
 
    public static void main(String[] args) {
        Merger m = new Merger();
        Merger.Init init = m.new Init();
        init.start();
    }
}
