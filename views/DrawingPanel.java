package views;

import java.awt.*;

import javax.swing.*;

//그래프가 그려지는 panel클래스
public class DrawingPanel extends JPanel {
	protected int vars[];
	protected int var1, var2, var3, var4;
	private int graphType;

	//처음 배경 그리는 메소드
	public void initPaint(Graphics g){
		// 패널 전체를 하얀색으로 표현
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawLine(50, 250, 450, 250); // 그래프의 가로 길이
		g.drawLine(50, 20, 50, 250); // 그래프의 세로 길이

		for (int cnt = 1; cnt <= 10; cnt++) {
			// 좌표값을 10의 간격으로 10~100까지 출력
			g.drawString(cnt * 10 + "", 25, 255 - 20 * cnt); // 스트링타입으로 변환시켜주기 위해 +"" 추가
			// 좌표값을 10의 간격의 라인으로 출력
			g.drawLine(50, 250 - 20 * cnt, 450, 250 - 20 * cnt);
		}
	}

	//막대그래프 그리는 메소드
	public void NormPaint(Graphics g){
		g.drawString("a", 100, 270);
		g.drawString("b", 200, 270);
		g.drawString("c", 300, 270);
		g.drawString("d", 400, 270);

		if (var1 > 0) {
			g.fillRect(110, 250 - var1 * 2, 10, var1 * 2);
		}

		if (var2 > 0) {
			g.fillRect(210, 250 - var2 * 2, 10, var2 * 2);
		}

		if (var3 > 0) {
			g.fillRect(310, 250 - var3 * 2, 10, var3 * 2);
		}

		if (var4 > 0) {
			g.fillRect(410, 250 - var4 * 2, 10, var4 * 2);
		}
	}

	//꺽은선 그래프 그리는 메소드
	public void paintLinear(Graphics g){
		g.drawString("a", 100, 270);
		g.drawString("b", 200, 270);
		g.drawString("c", 300, 270);
		g.drawString("d", 400, 270);

		if (var1 > 0 && var2 > 0) {
			g.drawLine(115, 250 - var1 * 2, 215, 250 - var2 * 2);
		}

		if (var2 > 0 && var3 > 0) {
			g.drawLine(215, 250 - var2 * 2, 315, 250 - var3 * 2);
		}

		if (var3 > 0 && var4 > 0) {
			g.drawLine(315, 250 - var3 * 2, 415, 250 - var4 * 2);
		}
	}

	// 페인트는 그래픽스 객체를 가지고 있는 메소드
	public void paint(Graphics g) {
			switch(graphType){
			case 0:
				initPaint(g);
				NormPaint(g);
				break;
			case 1:
				initPaint(g);
				paintLinear(g);
				break;
			default:
				break;
		}
	}

	// 데이터 대입
	public void setScore(int...datas) {
		vars = datas;
	}

	// 그래프 선택
	public void setGraphType(int n){
		this.graphType = n;
	}

}