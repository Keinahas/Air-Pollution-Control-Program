package views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

//그래프가 그려지는 panel클래스
public class Test extends JPanel {
    private String cols[] = { "이산화질소농도(ppm)", "오존농도(ppm)", "이산화탄소농도(ppm)", "아황산가스(ppm)", "미세먼지(㎍/㎥)", "초미세먼지(㎍/㎥)" };
    private String locs[] = {};
    private Color color[] = {Color.RED,Color.GREEN,Color.BLACK,Color.YELLOW,Color.DARK_GRAY,Color.gray};
    private int vars[];
    private int graphType;
    private int place;
    private int time;
    private int dust;

    public Test() {
        //
        this.graphType = -1;
    }

    // 처음 배경 그리는 메소드
    public void initPaint(Graphics g) {
        // 패널 전체를 하얀색으로 표현
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawLine(350, 250, 950, 250); // 그래프의 가로 길이
        g.drawLine(350, 20, 350, 250); // 그래프의 세로 길이
        /*
        for (int cnt = 1; cnt <= 10; cnt++) {
            // 좌표값을 10의 간격으로 10~100까지 출력
            g.drawString(cnt * 10 + "", 25, 255 - 20 * cnt); // 스트링타입으로 변환시켜주기 위해 +"" 추가
            // 좌표값을 10의 간격의 라인으로 출력
            g.drawLine(50, 250 - 20 * cnt, 450, 250 - 20 * cnt);
        }*/
    }

    // 막대그래프 그리는 메소드
    public void NormPaint(Graphics g) {
        for(int i = 0;i<vars.length;i++){
            g.drawString(cols[i], 300+100*(i+1), 270);
        }

        for (int i = 0; i <vars.length; i++) {
            if (vars[i] > 0) {
                g.fillRect(410 + 100 * i, 250 - vars[i] * 2, 10, vars[i] * 2);
            }
        }
        //왼쪽 지역색깔 부분
        for(int i = 0;i<locs.length;i++){
            g.drawString(locs[i], 100, 100+20*(i+1));
        }
        for(int i = 0;i<locs.length;i++){
            g.setColor(color[i]);
            g.fillRect(85, 90+20*(i+1), 10,10);
            g.setColor(Color.black);
        }

    }

    // 꺽은선 그래프 그리는 메소드
    public void paintLinear(Graphics g) {
        for(int i = 0;i<vars.length;i++){
            g.drawString(cols[i], 300+100*(i+1), 270);
        }

        for (int i = 0; i <vars.length-1; i++) {
            if (vars[i] > 0 && vars[i + 1] > 0) {
                g.drawLine(415 + 100 * i, 250 - vars[i] * 2, 415 + 100 * (i+1), 250 - vars[i + 1] * 2);
            }
        }

        //왼쪽 지역색깔 부분
        for(int i = 0;i<locs.length;i++){
            g.drawString(locs[i], 100, 100+20*(i+1));
        }
        for(int i = 0;i<locs.length;i++){
            g.setColor(color[i]);
            g.fillRect(85, 90+20*(i+1), 10,10);
            g.setColor(Color.black);
        }
    }

    // 페인트는 그래픽스 객체를 가지고 있는 메소드
    public void paint(Graphics g) {
        switch (graphType) {
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
//----------------------------------------------------------------------------외부호출
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
    public void setScore(double... datas) {
        int i = 0;
        int[] t = new int[datas.length];
        for (double d : datas) {
            t[i++] = (int) Math.rint(d);
        }
        vars = t;
    }

    // 그래프 선택
    public void setGraphType(int n) {
        this.graphType = n;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        
        //--------------------------------------------------center
        Test panel = new Test();
        double[] d = {10.1,60.1,30.1,40.1,70.1,60.1};                      // 외부 입력
        panel.setScore(d);
        String[] dd = {"성북구","노원구","성동구","강남구","도봉구"};
        panel.setLocation(dd);

       
        //--------------------------------------------------north
        JPanel north = new JPanel();
        JButton b1 = new JButton("bar");
        JButton b2 = new JButton("lin");

        ActionListener listener1 = e1 -> {
            if (e1.getSource() == b1) {
                panel.setGraphType(0);
                panel.repaint();
            }
        };
        ActionListener listener2 = e2 -> {
            panel.setGraphType(1);
            panel.repaint();
        };
        b1.addActionListener(listener1);
        b2.addActionListener(listener2);

        north.add(b1);
        north.add(b2);
        //-------------------------------------------------------------south
        JPanel south = new JPanel();
        

        //----------------------------------------------------------------do
        frame.add(north, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(south, BorderLayout.SOUTH);

        frame.setLocation(100,100);
        frame.setVisible(true);

    }
}
