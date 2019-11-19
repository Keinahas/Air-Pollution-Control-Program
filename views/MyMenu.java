package views;

import javax.swing.JMenu;
import java.awt.event.ActionListener;

public class MyMenu extends JMenu implements OptsAddable{

    public MyMenu(String str){
        super(str);
    }

    public MyMenu(String str, int mnemonic){
        super(str);
        this.setMnemonic(mnemonic);
    }

    @Override
    public void addOption(String str){
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