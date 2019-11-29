package controls.triggers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import views.DrawingPanel;

//그래프를 그리는 버튼 ActionListener
public class DrawAction implements ActionListener {
	private JTextField txts[];
	private JComboBox type;
	private DrawingPanel panel;

	public DrawAction(){

	}

	public DrawAction(DrawingPanel panel, JComboBox type, JTextField...txtFields) { 
		this.type = type;
		this.txts = txtFields;
		this.panel = panel;
	}

	public void init(DrawingPanel panel, JComboBox type, JTextField...txtFields){
		this.type = type;
		this.txts = txtFields;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			int[] vars = new int[txts.length];

			//init vars
			for (int i = 0; i < txts.length; i++) {
				vars[i] = Integer.parseInt(txts[i].getText().trim());
			}

			panel.setScore(vars);

			// 그래프를 그리는 패널의 paint()를 간접적으로 호출
			panel.setGraphType(type.getSelectedIndex());
			panel.repaint();

		} catch (NumberFormatException n) {
			System.out.println("잘못된 숫자 포멧입니다.");

		}
	}
}