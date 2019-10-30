package views;

import javax.swing.JMenu;
// import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenubar extends JMenuBar {
    private static final long serialVersionUID = 1L;

    public MyMenubar() {
        JMenu m1 = new JMenu("sangos");
        //   m1.setText("sangos");
        this.add(m1);
        JMenuItem mi1 = new JMenuItem("killer Jo");
        m1.add(mi1);
    }

    public void addMenu(JMenu menu){
        this.add(menu);
    } 
}