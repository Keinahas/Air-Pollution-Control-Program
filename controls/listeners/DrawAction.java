package controls.listeners;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import controls.CTRL;
import controls.db.dbActions;
import views.DrawingPanel;

//그래프를 그리는 버튼 ActionListener
public class DrawAction implements ActionListener {
	private double[] vars;
	private int gType;
	private JTextField txts[];
	private JComboBox type;
	private DrawingPanel panel;

	public DrawAction(){

	}

	public DrawAction(DrawingPanel panel, int gType){
		this.panel = panel;
		this.gType = gType;
	}


	public DrawAction(DrawingPanel panel, JComboBox type) { 
		this.panel = panel;
		this.type = type;
	}
	
	public DrawAction(DrawingPanel panel, JComboBox type, JTextField...txtFields) { 
		this.type = type;
		this.txts = txtFields;
		this.panel = panel;
	}
	public void init(DrawingPanel panel, int gType){
		this.panel = panel;
		this.gType = gType;
	}

	public void init(DrawingPanel panel, JComboBox type, double...vars){
		this.type = type;
		this.vars = vars;
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

			panel.setScore(vars);

			// 그래프를 그리는 패널의 paint()를 간접적으로 호출
			if(type != null){
				panel.setGraphType(type.getSelectedIndex());
			}else{
				panel.setGraphType(gType);
			}
			panel.repaint();

		} catch (NumberFormatException n) {
			System.out.println("잘못된 숫자 포멧입니다.");

		}
	}
}