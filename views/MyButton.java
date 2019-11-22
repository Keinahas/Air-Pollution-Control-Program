package views;

import javax.swing.Icon;
import javax.swing.JButton;

import java.awt.event.ActionListener;

public class MyButton extends JButton{//버튼이 생성되고나서 버튼의 역할을 주어지게 하는 클래스

    public MyButton(Icon icon, ActionListener actionListener){//아이콘을 버튼으로 만들어주는 생성자, 액션리스너도 추가해준다.
        super(icon);
        this.addActionListener(actionListener);
    }

    public MyButton(Icon icon, int mnemonic, ActionListener actionListener){//아이콘을 버튼으로 만들어주는 생성자, 단축키와 액션리스너도 추가해준다.
        super(icon);
        this.setMnemonic(mnemonic);
        this.addActionListener(actionListener);
    }
}