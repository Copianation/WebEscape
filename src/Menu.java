import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Menu {
	Config config;
	int[] map = {1,2,3};
	int currentMap;
	
	public Menu(Config config) {
		this.config = config;
		currentMap = 1;
	}
	
	public void drawMenu(Graphics2D g2d) {
		int width = config.SCREEN_WIDTH/map.length;
		int height = config.SCREEN_WIDTH/13;
		g2d.setColor(new Color(200, 160, 240));
		g2d.fillRect(0, 0, config.SCREEN_WIDTH, height);
		
		g2d.setColor(new Color(255, 230, 255));
		g2d.setStroke(new BasicStroke(4));
		g2d.setFont(new Font("Arial", Font.PLAIN, 30));
		FontMetrics fm = g2d.getFontMetrics();
		int textWidth = fm.stringWidth(""+1);
		int textHeight = fm.getHeight();
		int center;
		for (int i:map) {
			g2d.drawRect((i-1)*width, 0, width+3, height);
			center = (i-1)*width+(width+3)/2;
			g2d.drawString("" + i, center-textWidth/2, height/2+textHeight/3);
		}
		g2d.setColor(new Color(255, 230, 255, 100));
		g2d.fillRect((currentMap-1)*width, 0, width+3, height);
	}
	
	public int detectChoice(int x, int y) {
		int width = config.SCREEN_WIDTH/map.length;
		int height = config.SCREEN_WIDTH/13;
		
		if (y > height) {
			return -1;
		}
		
		return x/width + 1;
	}
	
	public void setCurrentMap(int i) {
		currentMap = i;
	}

}
