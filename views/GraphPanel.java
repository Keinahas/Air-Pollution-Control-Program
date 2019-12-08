package views;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import views.DrawingPanel;
import controls.CTRL;
import controls.listeners.DrawAction;

//그래프를 그릴 fame을 만들고 DrawingPanel을 불러오는 클래스
public class GraphPanel extends JPanel {
	// private DrawingPanel drawpanel;

	private JScrollPane scrollPane;
	private List<List<Object>> conts;// 안쪼개진거
	private int graphType;
	private List<DrawingPanel> paneList; //쪼개진 패널(쪼개진 배열을 하나 가지고 있는)들을 가지고 있음

	// 생성자
	public GraphPanel() {
		setBackground(Color.RED);
		paneList = new ArrayList<>();
		scrollPane = new JScrollPane();
		this.add(scrollPane);
		this.setSize(new Dimension(500, 500));
		this.setPreferredSize(new Dimension(500, 500));
		this.setBackground(Color.red);
		this.setForeground(Color.red);
	}

	// 지역이름 배열 선택
	// public void setLocation(String...datas){
    //     int i = 0;
    //     String[] t = new String[datas.length];
    //     for (String d : datas) {
    //         t[i++] = d;
    //     }
    //     locs = t;
	// }
	
    // 데이터 대입
    public void setData(List<List<Object>> conts) {
		this.conts = conts;
		// this.locs = locs;
        // for (int i=0;i<datas.length/6;i++) {
		// 	for(int j = 0;j<6;i++)
        //     	vars[i][j] = (int) Math.rint(datas[i][j]);
        // }
    }
    // 그래프 선택
    public void setGraphType(int n) {
        this.graphType = n;
	}
	
	public void addGraph(List<List<Object>> conts){ //리스트에 쪼개진 배열을 가지고 있는 쪼개진 패널을 넣고 add하는 함수
		paneList.add(new DrawingPanel(graphType));
		paneList.get(paneList.size()-1).setData(conts);
		scrollPane.add(paneList.get(paneList.size()-1));
	}

	@Override
	public void paint(Graphics g) { //리스트를 리스트크기만큼 반복하여 패널을 그림
		for (DrawingPanel pane : paneList) {
			pane.repaint();
		}
	}

	public void reset(){
		for (int i = 0; i < conts.size()/5; i++) {
			List<List<Object>> tempList = new ArrayList<>();
			tempList.add(conts.get(i*5));
			tempList.add(conts.get(i*5+1));
			tempList.add(conts.get(i*5+2));
			tempList.add(conts.get(i*5+3));
			tempList.add(conts.get(i*5+4));
			// paneList.get(i/5).setData(tempList);
			addGraph(tempList);
			paneList.get(i/5).setGraphType(graphType);
		}
		List<List<Object>> tempList = new ArrayList<>();
		for (int i=0;i<conts.size()%5;i++){
			tempList.add(conts.get((conts.size()/5 * 5 )+ i));
		}
		// paneList.get(conts.size()/5).setData(tempList);
		addGraph(tempList);
		paneList.get(conts.size()/5).setGraphType(graphType);
	}
}
