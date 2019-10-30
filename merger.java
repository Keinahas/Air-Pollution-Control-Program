import javax.swing.JMenu;

import views.*;
import views.MyMenubar;
import views.MainFrame;


public class Merger{
    private MainFrame f;
    private MyMenubar mb;
    
    public Merger() {
        f = new MainFrame(1000, 500);
        mb = new MyMenubar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Edit");
        mb.add(m1);
        mb.add(m2);
        f.addMenuBar(mb);
        f.setVisible(true);
    }
 
    public static void main(String[] args) {
        Merger m = new Merger();
    }
}