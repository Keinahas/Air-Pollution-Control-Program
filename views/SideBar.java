import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import views.MyButton;
import views.resource.*;

import java.awt.*;
import java.awt.event.*;

public class SideBar extends JPanel{
    private JButton btn[];
    private JPanel pan[];
    public SideBar(){
        this.setLayout(new GridLayout(0,1));
        this.setBorder(new LineBorder(Color.gray,5));
        pan[0].add(MyButton(new ImageIcon("views/resource/when.png"),CTRL.BTN_When));// CTRL.BTN_When 만들예정
        pan[0].add(MyButton(new ImageIcon("views/resource/where.png"),CTRL.BTN_Where));//이미지도 성분별로 만들어

        

    }
}