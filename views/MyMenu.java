package views;

import javax.swing.JMenu;
import java.awt.event.ActionListener;

public class MyMenu extends JMenu implements OptsAddable{

    public MyMenu(String str){
        super(str);
    }

    @Override
    public void addOption(String str){
        this.add(new MyMenuItem(str));
    }

    @Override
    public void addOption(String str, ActionListener l){
        this.add(new MyMenuItem(str, l));
    }

}