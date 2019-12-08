package controls.listeners;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controls.CTRL;
import controls.db.dbActions;
import views.DrawingPanel;
import views.GraphPanel;

//그래프를 그리는 버튼 ActionListener
public class DrawAction implements ActionListener {
	private double[] vars;
	private int gType;
	private JTextField txts[];
	private GraphPanel panel;

	public DrawAction(GraphPanel panel, int gType){
		this.panel = panel;
		this.gType = gType;
	}

	public void actionPerformed(ActionEvent e) {
		try {

			List<List<String>> average = CTRL.getAverage();
			List<List<Object>> parsedAvg = new ArrayList<>();
			if(average == null){
				System.out.println("average NULL");
				return;
			}
			for (List<String> avg : average) {
				List<Object> parsed = new ArrayList<>();
				for (int i =0 ;i < avg.size();i++){
					switch(i){
						case 0: case 1:
							parsed.add(avg.get(i));
							break;
						case 2: case 3: case 5:
							parsed.add((int)Math.round(Double.parseDouble(avg.get(i))*1000));
							break;
						case 4: 
							parsed.add((int)Math.round(Double.parseDouble(avg.get(i))*100));
							break;
						default:
							parsed.add((int)Math.round(Double.parseDouble(avg.get(i))));
							break;
					}
				}
				System.out.println("DRAWACTION::"+avg+" : "+parsed);
				parsedAvg.add(parsed);
			}
			panel.setGraphType(gType);
			panel.setData(parsedAvg);
			panel.reset();
			panel.repaint();

		} catch (NumberFormatException n) {
			System.out.println("잘못된 숫자 포멧입니다.");

		}
	}
}