package views;

import java.awt.*;
import javax.swing.*;

//그래프를 그리는 클래스
public class DrawingPanel extends JPanel {
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
		g.drawString("수원", 100, 270);
		g.drawString("승진", 200, 270);
		g.drawString("성원", 300, 270);
		g.drawString("승환", 400, 270);

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
		g.drawString("수원", 100, 270);
		g.drawString("승진", 200, 270);
		g.drawString("성원", 300, 270);
		g.drawString("승환", 400, 270);

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

	
	public void paint(Graphics g) {// 페인트는 그래픽스 객체를 가지고 있는 메소드
		initPaint(g);
			switch(graphType){
			case 0:
				NormPaint(g);
				break;
			case 1:
				paintLinear(g);
				break;
			default:
				break;
		}
	}

	void setScore(int var1, int var2, int var3, int var4) {
		this.var1 = var1;
		this.var2 = var2;
		this.var3 = var3;
		this.var4 = var4;
	}

	void setGraphType(int n){
		this.graphType = n;
	}

}