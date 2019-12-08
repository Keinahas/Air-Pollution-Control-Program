package controls.listeners;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import views.GraphPanel;


public class getImage implements ActionListener {
    JPanel panel;
    public getImage(JPanel panel){
        this.panel = panel;
    }
    

    public BufferedImage saveImage(JPanel panel) {
        BufferedImage image = new BufferedImage(panel.getWidth(),panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        panel.repaint();
        panel.setVisible(true);
        panel.printAll(image.getGraphics());
        
        
        //g.drawi
 
		return image;
    }
    
    public void actionPerformed(ActionEvent e) {
        saveImage(this.panel);

        try{
			ImageIO.write(saveImage(this.panel), "PNG", new File("image.PNG"));
		} catch (IOException e2) {
		}
    }
}
