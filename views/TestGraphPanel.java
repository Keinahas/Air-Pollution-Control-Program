package views;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import views.Test;
import controls.listeners.DrawAction;

//그래프를 그릴 fame을 만들고 DrawingPanel을 불러오는 클래스
public class TestGraphPanel extends JPanel {

	private JScrollPane scrollPane;
	private List<List<Object>> conts;// 안쪼개진거
	private int graphType;
    private List<Test> paneList; //쪼개진 패널(쪼개진 배열을 하나 가지고 있는)들을 가지고 있음
    private String cols[] = {"이산화질소농도(ppm)", "오존농도(ppm)", "이산화탄소농도(ppm)", "아황산가스(ppm)","미세먼지(㎍/㎥)","초미세먼지(㎍/㎥)"};
	private Color color[] = {Color.RED,Color.GREEN,Color.BLACK,Color.YELLOW,Color.DARK_GRAY,Color.gray};


	// 생성자
	public TestGraphPanel() {
		paneList = new ArrayList<>();
		scrollPane = new JScrollPane();
		this.add(scrollPane);
        
        
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
        this.repaint();
	}
	
	public void addGraph(){ //리스트에 쪼개진 배열을 가지고 있는 쪼개진 패널을 넣고 add하는 함수
		paneList.add(new Test());
		scrollPane.add(paneList.get(paneList.size()-1));
	}

	@Override
	public void paint(Graphics g) { //리스트를 리스트크기만큼 반복하여 패널을 그림
		for (Test pane : paneList) {
			pane.repaint();
		}
	}

	public void reset(){
		for (int i = 0; i < conts.size()/5; i++) {
			List<List<Object>> tempList = new ArrayList<>();
			tempList.add(conts.get(i));
			tempList.add(conts.get(i+1));
			tempList.add(conts.get(i+2));
			tempList.add(conts.get(i+3));
			tempList.add(conts.get(i+4));
			addGraph();
			paneList.get(i).setData(tempList);
		}
		addGraph();
		List<List<Object>> tempList = new ArrayList<>();
		for (int i=0;i<conts.size()%5;i++){
			tempList.add(conts.get(i));
		}
		paneList.get(conts.size()/5).setData(tempList);
	}

    
    
    
    public  List<List<Object>> av(){
        List<List<Object>> parsedAvg = new ArrayList<>();
        List<Object> a = new ArrayList<>();
        List<Object> b = new ArrayList<>();
        List<Object> c = new ArrayList<>();
        List<Object> d = new ArrayList<>();
        List<Object> e = new ArrayList<>();
        List<Object> f = new ArrayList<>();

        for(int i = 0;i<2;i++){
            a.add("성북구");
            b.add("노원구");
            c.add("성동구");
            d.add("도봉구");
            e.add("구로구");
            f.add("강동구");
        }
        for(int i = 0;i<6;i++){
            a.add(i*10);
            b.add(i*11);
            c.add(i*12);
            d.add(i*13);
            e.add(i*14);
            f.add(i*15);
        }
        parsedAvg.add(a);
        parsedAvg.add(b);
        parsedAvg.add(c);
        parsedAvg.add(d);
        parsedAvg.add(e);
        parsedAvg.add(f);

        return parsedAvg;
    }

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

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        //--------------------------------------------------center
        TestGraphPanel panel = new TestGraphPanel();
        panel.conts = av();
        //------------------------------------------------------------------------------------------- 임시 db 와 actionperformed
     
       
        //--------------------------------------------------north
        JPanel north = new JPanel();
        JButton b1 = new JButton("bar");
        JButton b2 = new JButton("lin");

        ActionListener listener1 = e1 -> {
            if (e1.getSource() == b1) {
               panel.setGraphType(0);
            }else{
                panel.setGraphType(1);
            }
        };
    
        b1.addActionListener(listener1);
        b2.addActionListener(listener1);

        north.add(b1);
        north.add(b2);
        //----------------------------------------------------------------do
        frame.add(north, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setLocation(100,100);
        frame.setVisible(true);

    }

}
