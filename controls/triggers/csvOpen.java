package controls.triggers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class csvOpen implements ActionListener{

    public csvOpen(){
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println(e.getSource());
    }
}