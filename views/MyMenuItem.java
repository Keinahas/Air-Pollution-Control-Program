package views;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;

public class MyMenuItem extends JMenuItem{//메뉴아이템을 넣어준다.

    // 생성자
    public MyMenuItem(String str){
        super(str);
    }
    
    public MyMenuItem(String str, ActionListener l){
        super(str);
        this.addActionListener(l);
    }
}