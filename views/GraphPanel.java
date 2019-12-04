package views;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import views.DrawingPanel;
import controls.listeners.DrawAction;

//그래프를 그릴 fame을 만들고 DrawingPanel을 불러오는 클래스
public class GraphPanel extends JPanel {
	private DrawingPanel drawpanel;

    private String locs[];
    private int vars[][];
	private int graphType;
	private List<DrawingPanel> paneList; //쪼개진 패널(쪼개진 배열을 하나 가지고 있는)들을 가지고 있음

	// 생성자
	public GraphPanel() {
		paneList = new ArrayList<>();
	
		// 그래프를 그릴 패널을 만든다.
		addGraph();
		JPanel panel = new JPanel();
		panel.add()
		// 막대 그래프를 표현할 그래프의 위치를 중앙에
		this.add(panel, BorderLayout.CENTER);

		// 패널생성

	}
	    // 지역이름 배열 선택
	public void setLocation(String...datas){
        int i = 0;
        String[] t = new String[datas.length];
        for (String d : datas) {
            t[i++] = d;
        }
        locs = t;
    }
    // 데이터 대입
    public void setData(String[] locs,double[][] datas) {
		this.locs = locs;
        for (int i=0;i<datas.length/6;i++) {
			for(int j = 0;j<6;i++)
            	vars[i][j] = (int) Math.rint(datas[i][j]);
        }
    }
    // 그래프 선택
    public void setGraphType(int n) {
        this.graphType = n;
	}
	
	public void addGraph(){ //리스트에 쪼개진 배열을 가지고 있는 쪼개진 패널을 넣고 add하는 함수
		paneList.add(new DrawingPanel());
		this.add(paneList.get(paneList.size()-1));
	}

	@Override
	public void paint(Graphics g) { //리스트를 리스트크기만큼 반복하여 패널을 그림
			for (DrawingPanel pane : paneList) {
			pane.repaint();
		}
	}

	public void reset(){
		for (int i = 0; i < locs.length/5; i++) {
			addGraph();
			List<String> strings = new ArrayList<>();
			List<List<Integer>> datas = new ArrayList<>();
			for(int j=0;j<5;j++)
				strings.add(locs[j]);
			for(int j=0;j<5;j++){
				List<Integer> tList = new ArrayList<>();
				for (int k = 0; k < 6; k++) {
					tList.add(vars[j][k]);
				}
				datas.add(tList);
			}
			paneList.get(i).setData((String[])strings.toArray(), (Integer[][])datas.toArray());
		}
		addGraph();
		List<String> strings = new ArrayList<>();
		List<List<Integer>> datas = new ArrayList<>();
		for(int j=0;j<locs.length%5;j++)
			strings.add(locs[j]);
		for(int j=0;j<locs.length%5;j++){
			List<Integer> tList = new ArrayList<>();
			for (int k = 0; k < 6; k++) {
				tList.add(vars[j][k]);
			}
			datas.add(tList);
		}
		paneList.get(locs.length/5).setData((String[])strings.toArray(), (Integer[][])datas.toArray());
	}
}
