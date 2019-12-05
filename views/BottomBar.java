package views;

import javax.swing.*;
//import javax.swing.border.LineBorder;

//import views.MyButton;
//import views.resource;

//import java.awt.*;
//import java.awt.event.*;
//import java.awt.event.ActionListener;
//import java.util.List;
//import java.awt.Component;
import java.awt.EventQueue;

//import javax.swing.tree.DefaultMutableTreeNode;
//import javax.swing.tree.DefaultTreeModel;
//import javax.swing.tree.TreeCellEditor;
//import javax.swing.tree.TreeCellRenderer;

//import controls.CTRL;

public class BottomBar extends JPanel {

    public BottomBar(TablePanel tp) {
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.add(tp);
        //scrollPane.setSize(new Dimension(180, 680));
        //scrollPane.setPreferredSize(new Dimension(180, 680));
        this.add(scrollPane);


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                new SideBar();
            }
        });
    }

}