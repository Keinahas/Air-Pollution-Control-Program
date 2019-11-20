package views;

import java.awt.*;
import javax.swing.*;

// 그래프 그리는 패널
public class Graph1 extends JPanel {

	// 생성자
	public Graph1() {
		setLayout(new BorderLayout());
		String[] type = { "막대", "선형" };
		JPanel toppanel = new JPanel();
		add(toppanel, BorderLayout.NORTH);
		JComboBox<String> typecb = new JComboBox<>(type);
		toppanel.add(typecb);


		// 그래프를 그릴 패널을 만든다.
		DrawingPanel drawpanel = new DrawingPanel();

		// 막대 그래프를 표현할 그래프의 위치를 중앙에..
		this.add(drawpanel, BorderLayout.CENTER);

		// 패널생성
		JPanel panel = new JPanel();

		JTextField txt1 = new JTextField(3);
		JTextField txt2 = new JTextField(3);
		JTextField txt3 = new JTextField(3);
		JTextField txt4 = new JTextField(3);

		JButton btn = new JButton("그래프그리기");

		// 패널 추가
		panel.add(new JLabel("a"));
		panel.add(txt1);
		panel.add(new JLabel("b"));
		panel.add(txt2);
		panel.add(new JLabel("c"));
		panel.add(txt3);
		panel.add(new JLabel("d"));
		panel.add(txt4);
		panel.add(btn);

		this.add(panel, BorderLayout.SOUTH);

		// 버튼에 이벤트 등록
		btn.addActionListener(new DrawAction(txt1, txt2, txt3, txt4, drawpanel, typecb));

		this.setVisible(true);
	}
}
