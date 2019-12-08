package views;

import javax.swing.*;
import java.awt.EventQueue;

public class BottomBar extends JPanel {

    public BottomBar(TablePanel tp) {
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.add(tp);
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