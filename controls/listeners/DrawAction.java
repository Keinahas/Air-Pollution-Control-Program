package controls.listeners;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

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

	public void init(GraphPanel panel, int gType){
		this.panel = panel;
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
				for (String str : avg) {
					try{
						Object parse = Math.rint(Double.parseDouble(str));
						parsed.add(parse);
					}catch (NumberFormatException exception){
						parsed.add(str);
					}catch (NullPointerException exception){
						parsed.add(0);
					}
				}
				System.out.println("DRAWACTION::"+parsed);
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