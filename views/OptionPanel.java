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


}