package views;

import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class MyButton extends JButton{
    public MyButton(Icon icon, ActionListener actionListener){
        super(icon);
        this.addActionListener(actionListener);
    }
}