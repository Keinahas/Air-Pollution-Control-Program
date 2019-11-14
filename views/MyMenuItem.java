package views;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;

public class MyMenuItem extends JMenuItem{
    public MyMenuItem(String arg0){
        super(arg0);
    }
    
    public MyMenuItem(String arg0, ActionListener arg1){
        super(arg0);
        this.addActionListener(arg1);
    }
}