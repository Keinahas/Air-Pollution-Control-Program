package controls.triggers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import views.DrawingPanel;

//그래프를 그리는 버튼 ActionListener
public class DrawAction implements ActionListener {
	private int num;
	private JTextField txts[];
	private JComboBox type;

	public DrawAction(){

	}

	public DrawAction(JComboBox type, JTextField...txtFields) { 
		this.num = 0;
		this.type = type;
		this.txts = txtFields;
	}

	public void init(JComboBox type, JTextField...txtFields){
		this.num = 0;
		this.type = type;
		this.txts = txtFields;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			int i = 0;
			int[] vars = new int[num];
			for (JTextField field : txts) {
				vars[i++] = Integer.parseInt(field.getText().trim());
			}

			((DrawingPanel)(e.getSource())).setScore(vars);
			// drawPanel.setScore(var1, var2, var3, var4);

			// 그래프를 그리는 패널의 paint()를 간접적으로 호출
			((DrawingPanel)(e.getSource())).setGraphType(type.getSelectedIndex());
			((DrawingPanel)(e.getSource())).repaint();

		} catch (NumberFormatException n) {
			System.out.println("잘못된 숫자 포멧입니다.");

		}
	}
}