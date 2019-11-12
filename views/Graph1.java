package sample;

import java.awt.*;
import javax.swing.*;

public class Graph1 {
	public static void main(String arg[]) {
		JFrame frame = new JFrame("누가누가 쥬지가 길까?");
		frame.setLocation(500, 300);
		// frame.setPreferredSize(new Dimension(400,300));
		frame.setSize(500, 400);

		// 컨테이너란 말 그대로 다른 컴포넌트를 포함할 수 있는 컴포넌트를 말한다.
		Container contentPane = frame.getContentPane();

		// 그래프를 그릴 패널을 만든다.
		DrawingPanel drawpanel = new DrawingPanel();

		// 막대 그래프를 표현할 그래프의 위치를 중앙에..
		contentPane.add(drawpanel, BorderLayout.CENTER);

		// 패널생성
		JPanel panel = new JPanel();

		JTextField txt1 = new JTextField(3);
		JTextField txt2 = new JTextField(3);
		JTextField txt3 = new JTextField(3);
		JTextField txt4 = new JTextField(3);

		JButton btn = new JButton("그래프그리기");

		// 패널 추가
		panel.add(new JLabel("수원"));
		panel.add(txt1);
		panel.add(new JLabel("승진"));
		panel.add(txt2);
		panel.add(new JLabel("성원"));
		panel.add(txt3);
		panel.add(new JLabel("승환"));
		panel.add(txt4);
		panel.add(btn);

		contentPane.add(panel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 버튼에 이벤트 등록
		btn.addActionListener(new DrawAction(txt1, txt2, txt3, txt4, drawpanel));

		frame.setVisible(true);
	}
}
