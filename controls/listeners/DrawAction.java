package controls.listeners;

import java.awt.event.ActionListener;
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
			if(txts != null){
				vars = new double[txts.length];

				//init vars
				for (int i = 0; i < txts.length; i++) {
					vars[i] = Double.parseDouble(txts[i].getText().trim());
				}
			}else{

				List<String> average = CTRL.getAverage();
				if(average == null){
					System.out.println("average NULL");
					return;
				}
				vars = new double[average.size()];
				for(int i=2;i<average.size();i++){
					vars[i]=Double.parseDouble(average.get(i));
					if(i<6){
						vars[i] *= 100;
					}
				}

			}

			panel.setGraphType(gType);
			// panel.setData(vars);
			panel.reset();
			panel.repaint();

		} catch (NumberFormatException n) {
			System.out.println("잘못된 숫자 포멧입니다.");

		}
	}
}