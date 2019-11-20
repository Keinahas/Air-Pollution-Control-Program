package views;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame{//프레임에서 하는 일을 여기서 다 해준다!

    public MainFrame(int x, int y){//프레임 생성자
    	this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        // this.setVisible(true); 
        // Watch out for placing setVisible().
        
    }

	public void addMenuBar(MyMenubar bar) {//프레임에 메뉴바를 추가하는 메소드
        this.setJMenuBar(bar);
    }
	public void addToolBar(MyToolBar bar) {//프레임에 툴바를 추가하는 메소드
        this.add(bar,BorderLayout.NORTH);
    }

    public void addGraph(Graph1 bar){//프레임에 그래프를 추가하는 메소드
        this.add(bar,BorderLayout.CENTER);
    }
    public void addOptionPanel(OptionPanel pan) {// 옵션패널을 추가하는 메소드
        this.add(pan,BorderLayout.EAST);
    }
}
