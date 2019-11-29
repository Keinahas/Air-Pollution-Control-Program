package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class OptionPanel extends JPanel implements OptsAddable{//옵션을 하나하나 보여주는 패널을 특정해서 클래스로 만들어줍니다.
    public OptionPanel(){//생성자 패널 테두리 설정, 그리드레이아웃으로 만듬
        setBorder(new TitledBorder(new LineBorder(Color.gray,5)));
        setLayout(new GridLayout(0,2));
    }

    // 체크박스와 레이블을 추가한다. 여러가지 파라메터를 줄 수 있다.
    @Override
    public void addOption(String str) {//옵션 추가하는 것
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