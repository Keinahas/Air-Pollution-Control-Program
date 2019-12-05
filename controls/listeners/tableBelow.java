package controls.listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import controls.CTRL;
import views.BottomBar;
import views.MainFrame;
import views.TablePanel;

public class tableBelow implements ActionListener{

    private TablePanel tp;
    private BottomBar bp;
    private boolean shown;

    public tableBelow(){
        shown = false;
        tp = new TablePanel();
        bp = new BottomBar(tp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(shown){
            CTRL.frame.remove(bp);
            bp = null;
            shown = false;
            return;
        }
        bp.setSize(new Dimension(800, 500));
        bp.setPreferredSize(new Dimension(800, 500));
        tp.setData(CTRL.getHeader(), CTRL.getContents());
        CTRL.frame.addBottomBar(bp);
        shown = true;
    }
}