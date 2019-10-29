package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JTextField;

import views.menuItem;

public class frame extends JFrame{

    frame(){
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void createMenubar(){
        JMenuBar mb = new JMenuBar(); 
		JMenu screenMenu = new JMenu("Screen");
		
		screenMenu.add(new JMenuItem("Load"));
		screenMenu.add(new JMenuItem("Hide"));
		screenMenu.add(new JMenuItem("ReShow"));
		screenMenu.addSeparator();
		screenMenu.add(new JMenuItem("Exit"));

		mb.add(screenMenu);
		mb.add(new JMenu("Edit"));
		mb.add(new JMenu("Source"));
		mb.add(new JMenu("Project"));
        mb.add(new JMenu("Run"));
		setJMenuBar(mb);
    }

    void createToolbar(){
        JToolBar tb = new JToolBar();
        tb.setBackground(Color.LIGHT_GRAY);

        tb.add(new JButton("New"));
        tb.addSeparator();
        tb.add(new JLabel("searchs"));
        tb.add(new JTextField("text field"));
        JComboBox comboBox = new JComboBox<>();
        comboBox.addItem("Java");
        comboBox.addItem("C#");
        comboBox.addItem("C++");
        comboBox.addItem("C");
        tb.add(comboBox);

        add(tb, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        frame f = new frame();
        f.createMenubar();
        f.createToolbar();
        f.setVisible(true);
    }
}