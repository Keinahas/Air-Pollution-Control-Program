package views;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import controls.CTRL;

//그래프가 그려지는 panel클래스
public class DrawingPanel extends JPanel {
	private String cols[] = {"이산화질소농도(ppm)", "오존농도(ppm)", "이산화탄소농도(ppm)", "아황산가스(ppm)","미세먼지(㎍/㎥)","초미세먼지(㎍/㎥)"};
	private Color color[] = {Color.RED,Color.GREEN,Color.BLACK,Color.YELLOW,Color.DARK_GRAY,Color.gray};
	private List<List<Object>> conts;
	private int graphType;

	public DrawingPanel(int n){
		this.setPreferredSize(new Dimension(700, 300));
		this.graphType = n;
	}

	//처음 배경 그리는 메소드
	public void initPaint(Graphics g){
		// 패널 전체를 하얀색으로 표현
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawLine(150, 250, 900, 250); // 그래프의 가로 길이
		g.drawLine(150, 20, 150, 250); // 그래프의 세로 길이
	}

	//막대그래프 그리는 메소드
	public void NormPaint(Graphics g){
		//메인
		for(int i = 0;i<cols.length;i++){
            g.drawString(CTRL.parse(cols[i]), 100+100*(1 + i), 270);
		}
		int k=0;
		int j=0;
		for (List<Object> cont : conts) {
			g.setColor(color[k++]);
			for(int i=2;i<cont.size();i++){
				if((int)cont.get(i) > 0){
					g.fillRect(210+100*(i-2) + (11*j), 250 - (int)cont.get(i) * 2, 10, (int)cont.get(i)*2);
				}
			}
			j++;
		}
		//왼쪽 지역색깔 부분
		k = 0;
		g.setColor(Color.black);
		for(List<Object> cont : conts){ 
			g.drawString((String)cont.get(1), 70, 100+20*(1+ k));
			g.setColor(color[k]);
            g.fillRect(55, 90+20*(1 + k++), 10,10);
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