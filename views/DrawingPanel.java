package views;

import java.awt.*;

import javax.swing.*;

//그래프가 그려지는 panel클래스
public class DrawingPanel extends JPanel {
	protected int vars[];
	private int graphType;

	public DrawingPanel(){
		//
		this.graphType = -1;
	}

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

		for(int i=0;i<vars.length;i++){
			if(vars[i] > 0){
				g.fillRect(110+100*i, 250 - vars[i] * 2, 10, vars[i]*2);
			}
		}
	}

	//꺽은선 그래프 그리는 메소드
	public void paintLinear(Graphics g){
		g.drawString("a", 100, 270);
		g.drawString("b", 200, 270);
		g.drawString("c", 300, 270);
		g.drawString("d", 400, 270);

		for(int i=0;i<vars.length-1;i++){
			if(vars[i] > 0 && vars[i+1]>0){
				g.fillRect(115+100*i, 250 - vars[i] * 2, 215, 250 - vars[i+1] * 2);
			}
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