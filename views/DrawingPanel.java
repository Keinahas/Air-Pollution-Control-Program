package views;

import java.awt.*;
import java.util.List;

import javax.swing.*;

//그래프가 그려지는 panel클래스
public class DrawingPanel extends JPanel {
	private String cols[] = {"이산화질소농도(ppm)", "오존농도(ppm)", "이산화탄소농도(ppm)", "아황산가스(ppm)","미세먼지(㎍/㎥)","초미세먼지(㎍/㎥)"};
	private Color color[] = {Color.RED,Color.GREEN,Color.BLACK,Color.YELLOW,Color.DARK_GRAY,Color.gray};
	private List<List<Object>> conts;
	private int graphType;

	public DrawingPanel(){
		//
		this.setSize(new Dimension(300, 500));
		this.setPreferredSize(new Dimension(300, 500));
		// this.graphType = -1;
	}

	public DrawingPanel(int n){
		//
		this.setSize(new Dimension(300, 500));
		this.setPreferredSize(new Dimension(300, 500));
		this.graphType = n;
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
		//메인
		for(int i = 0;i<cols.length;i++){
            g.drawString(cols[i], 300+100*(1 + i++), 270);
		}
		for (List<Object> cont : conts) {
			for(int i=2;i<cont.size();i++){
				for(int j = 0;j < 6; j++){
					if((int)cont.get(i) > 0){
						g.fillRect(410+100*(i-2) + (10*(j + 1)), 250 - (int)cont.get(i) * 2, 10, (int)cont.get(i)*2);
					}
				}
			}
		}
		//왼쪽 지역색깔 부분
		int k = 0;
		for(List<Object> cont : conts){ 
			g.drawString((String)cont.get(1), 100, 100+20*(k+1));
			g.setColor(color[k]);
            g.fillRect(85, 90+20*(k+1), 10,10);
            g.setColor(Color.black);
        }
        
	}

	//꺽은선 그래프 그리는 메소드
	public void paintLinear(Graphics g){
		
		// for(int i=0;i<vars.length-1;i++){
		// 	if(vars[0][i] > 0 && vars[0][i+1]>0){
		// 		g.fillRect(115+100*i, 250 - vars[0][i] * 2, 215, 250 - vars[0][i+1] * 2);
		// 	}
		// }
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
	public void setData(List<List<Object>> conts) {
		this.conts = conts;
		System.out.println(conts);
	}

	// 그래프 선택
	public void setGraphType(int n){
		this.graphType = n;
	}

}