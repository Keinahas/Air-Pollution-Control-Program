package views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenubar extends JMenuBar {//메뉴바 클래스
    public MyMenubar() {//생성자
        
    }

    public void addMenu(JMenu menu){//메뉴바에 메뉴를 추가해준다
        this.add(menu);
    } 
}