package views;

import javax.swing.JMenu;
import java.awt.event.ActionListener;

public class MyMenu extends JMenu implements OptsAddable{//메뉴를 메뉴바에 넣기위한 클래스

    public MyMenu(String str){//메뉴 텍스트를 넣어주는 생성자.
        super(str);
    }

    public MyMenu(String str, int mnemonic){//메뉴 텍스트와 단축키를 넣어주는 생성자.
        super(str);
        this.setMnemonic(mnemonic);
    }

    @Override
    public void addOption(String str){//옵션을 추가한다. 여러가지 파라메터를 줄 수 있다.
        MyMenuItem mItem = new MyMenuItem(str);
        this.add(mItem);
    }

    @Override
    public void addOption(String str, ActionListener l){
        MyMenuItem mItem = new MyMenuItem(str);
        mItem.addActionListener(l);
        this.add(mItem);
    }

    @Override
    public void addOption(String str, int mnemonic) {
        // TODO Auto-generated method stub
        MyMenuItem mItem = new MyMenuItem(str);
        mItem.setMnemonic(mnemonic);
        this.add(mItem);
    }

    @Override
    public void addOption(String str, int mnemonic, ActionListener l) {
        // TODO Auto-generated method stub
        MyMenuItem mItem = new MyMenuItem(str);
        mItem.setMnemonic(mnemonic);
        mItem.addActionListener(l);
        this.add(mItem);
    }

}