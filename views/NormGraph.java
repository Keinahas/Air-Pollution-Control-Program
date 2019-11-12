package views;

import java.awt.Graphics;
import views.DrawingPanel;

public class NormGraph extends DrawingPanel{
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        g.drawString("수원", 100, 270);
		g.drawString("승진", 200, 270);
		g.drawString("성원", 300, 270);
		g.drawString("승환", 400, 270);

		if (var1 > 0) {
			g.fillRect(110, 250 - var1 * 2, 10, var1 * 2);
		}

		if (var2 > 0) {
			g.fillRect(210, 250 - var2 * 2, 10, var2 * 2);
		}

		if (var3 > 0) {
			g.fillRect(310, 250 - var3 * 2, 10, var3 * 2);
		}

		if (var4 > 0) {
			g.fillRect(410, 250 - var4 * 2, 10, var4 * 2);
		}
    }
}