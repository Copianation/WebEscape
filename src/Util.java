import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Util {
	final static int GAME_OVER = 1;
	final static int GAME_WIN = 2;
	
	private Util() {
	}
	
	public static void drawCenteredText(Config config, Graphics2D g2d, String text) {
		FontMetrics fm = g2d.getFontMetrics();
		int textWidth = fm.stringWidth(text);
		int textHeight = fm.getHeight();
		int x = (config.SCREEN_WIDTH-textWidth)/2;
		int y = (config.SCREEN_HEIGHT+textHeight)/2;
		
		g2d.drawString(text, x, y);
	}
	
	public static void drawCenteredText(Config config, Graphics2D g2d, String text, double completeness) {
		FontMetrics fm = g2d.getFontMetrics();
		int textWidth = fm.stringWidth(text);
		int textHeight = fm.getHeight();
		int conterX = (config.SCREEN_WIDTH-textWidth)/2;
		int conterY = (config.SCREEN_HEIGHT+textHeight)/2;
		
		int x = Util.smoothTransition(-textWidth, conterX, completeness);
		
		g2d.drawString(text, x, conterY);
	}
	
	public static int smoothTransition(int start, int end, double completeness) {
		completeness = Math.min(completeness, 1);
		int result = (int) (start + (end-start)*Math.sin(completeness*Math.PI/2));
		return result;
	}
	
	public static void drawMessage(Config config, Graphics2D g2d, int messageType) {
		g2d.setColor(new Color(255,255,255,190));
		g2d.fillRect(0, config.SCREEN_HEIGHT/2-100, config.SCREEN_WIDTH, 200);
		
		switch (messageType) {
		case GAME_OVER:
    		g2d.setPaint(new Color(220, 160, 220));
    		g2d.setFont(new Font("Arial", Font.BOLD, 40));
    		Util.drawCenteredText(config, g2d, "GAME OVER");
    		break;
		case GAME_WIN:
    		g2d.setColor(new Color(140,255,140));
    		g2d.setFont(new Font("Arial", Font.BOLD, 40));
    		Util.drawCenteredText(config, g2d, "YOU ESCAPED");
    		break;
		}
	}
	
	public static void drawKey(Config config, Graphics2D g2d, int x, int y) {
		g2d.setColor(new Color(240,240,50));
		int size = config.getDotSize();
		int rectHeight = (int) (size * 0.7);
		int rectWidth = (int) (size * 0.1);
		int circleWidth = (int) (size * 0.35);
		int bitPosition = (int) (rectHeight * 0.2);
		int bitWidth = (int) (size * 0.15);
		int bitHeight = (int) (size * 0.15);
		
		g2d.fillRect(x - rectWidth/2, y - rectHeight/2, rectWidth, rectHeight);
		g2d.fillOval(x - circleWidth/2, y - rectHeight/2, circleWidth, circleWidth);
		g2d.fillRect(x + rectWidth/2, y + bitPosition , bitWidth, bitHeight);
	}
	
	public static void drawLock(Config config, Graphics2D g2d, int x, int y) {
		g2d.setColor(new Color(100,200,100));
		int size = config.getDotSize();
		int rectHeight = (int) (size * 0.7);
		int rectWidth = (int) (size * 0.1);
		int circleWidth = (int) (size * 0.35);
		int bitPosition = (int) (rectHeight * 0.2);
		int bitWidth = (int) (size * 0.15);
		int bitHeight = (int) (size * 0.15);
		
		g2d.fillRect(x - rectWidth/2, y - rectHeight/2, rectWidth, rectHeight);
		g2d.fillOval(x - circleWidth/2, y - rectHeight/2, circleWidth, circleWidth);
		g2d.fillRect(x + rectWidth/2, y + bitPosition , bitWidth, bitHeight);
	}

}
