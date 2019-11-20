package views;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class MyToolBar extends JToolBar{//툴바에 대한것을 포함하는 클래스
	public MyToolBar() {//생성자, 툴바 색과 툴바 이동가능성을 조절한다.
		this.setBackground(Color.WHITE);
		this.setFloatable(false);
    }
	public void addTool(JButton jb) {//툴바에 버튼을 추가한다.
		jb.setBackground(Color.WHITE);
		this.add(jb);
	}
	public void addTool(JTextField jt) {//툴바에 텍스트필드를 추가한다.
		jt.setBackground(Color.WHITE);
		this.add(jt);
	}
	public void addTool(JComboBox<String> jc) {//툴바에 콤보박스를 추가한다.
		jc.setBackground(Color.WHITE);
		this.add(jc);
	}
}
