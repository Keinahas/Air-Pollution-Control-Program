package controls.listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import controls.CTRL;
import views.TablePanel;

public class tableNew implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JFrame f = new JFrame("DATA TABLE");
        TablePanel tp = new TablePanel();
        tp.setWholeSize(new Dimension(800, 500));
        tp.setData(CTRL.getHeader(), CTRL.getContents());
        f.add(tp);
        f.pack();
        f.setVisible(true);
    }
}