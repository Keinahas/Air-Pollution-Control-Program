package views;

import javax.swing.JMenu;
// import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenubar extends JMenuBar {
    private static final long serialVersionUID = 1L;

    public MyMenubar() {
        
    }

    public void addMenu(JMenu menu){
        this.add(menu);
    } 
}