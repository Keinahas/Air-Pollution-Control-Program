package views;

// import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
// import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class MyToolBar extends JToolBar{
	public MyToolBar() {
		this.setBackground(Color.WHITE);
		this.setFloatable(false);
    }
	public void addTool(JButton jb) {
		jb.setBackground(Color.WHITE);
		this.add(jb);
	}
	public void addTool(JTextField jt) {
		jt.setBackground(Color.WHITE);
		this.add(jt);
	}
	public void addTool(JComboBox<String> jc) {
		jc.setBackground(Color.WHITE);
		this.add(jc);
	}
}
