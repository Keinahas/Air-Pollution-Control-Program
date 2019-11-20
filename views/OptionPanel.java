package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class OptionPanel extends JPanel implements OptsAddable{
    public OptionPanel(){
        setBorder(new TitledBorder(new LineBorder(Color.gray,10),"옵션"));
        setLayout(new GridLayout(0,2));
    }


    // 체크박스와 레이블을 추가한다. 여러가지 파라메터를 줄 수 있다.
    @Override
    public void addOption(String str) {
        // TODO Auto-generated method stub
        this.add(new JCheckBox());
        this.add(new JLabel("\t" + str + "\t"));
    }

    @Override
    public void addOption(String str, ActionListener l){
        JCheckBox checkBox = new JCheckBox();
        checkBox.addActionListener(l);
        this.add(checkBox);
        this.add(new JLabel("\t" + str + "\t"));
    }

	@Override
	public void addOption(String str, int mnemonic) {
		// TODO Auto-generated method stub
        JCheckBox checkBox = new JCheckBox();
        checkBox.setMnemonic(mnemonic);
        this.add(checkBox);
        this.add(new JLabel("\t" + str + "\t"));
	}

	@Override
	public void addOption(String str, int mnemonic, ActionListener l) {
		// TODO Auto-generated method stub
        JCheckBox checkBox = new JCheckBox();
        checkBox.setMnemonic(mnemonic);
        checkBox.addActionListener(l);
        this.add(checkBox);
        this.add(new JLabel("\t" + str + "\t"));
	}


}