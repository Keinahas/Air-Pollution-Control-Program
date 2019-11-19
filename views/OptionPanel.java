package views;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
public class OptionPanel extends JPanel{
    public OptionPanel(){
        setBorder(new TitledBorder(new LineBorder(Color.gray,10),"옵션"));
        setLayout(new GridLayout(0,2));
    }
    public void addOption(String a){
        this.add(new JCheckBox());//액션리스너 만들고 수정
        this.add(new JLabel("\t"+a+"\t"));
    }
}