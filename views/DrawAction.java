package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawAction implements ActionListener {
	private DrawingPanel drawPanel;
	JTextField txt1, txt2, txt3, txt4;

	DrawAction(JTextField txt1, JTextField txt2, JTextField txt3, JTextField txt4, DrawingPanel drawPanel) {
		this.txt1 = txt1;
		this.txt2 = txt2;
		this.txt3 = txt3;
		this.txt4 = txt4;
		this.drawPanel = drawPanel;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			int var1 = Integer.parseInt(txt1.getText());
			int var2 = Integer.parseInt(txt2.getText());
			int var3 = Integer.parseInt(txt3.getText());
			int var4 = Integer.parseInt(txt4.getText());

			drawPanel.setScore(var1, var2, var3, var4);

			// 그래프를 그리는 패널의 paint()를 간접적으로 호출
			drawPanel.setGraphType(1);
			drawPanel.repaint();

		} catch (NumberFormatException n) {
			System.out.println("잘못된 숫자 포멧입니다.");

		}
	}
}