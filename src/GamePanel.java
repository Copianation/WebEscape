import javax.swing.JPanel;
import javax.swing.Timer;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class GamePanel extends JPanel implements ActionListener {
	
	private Config config;
	
	private Menu menu;
	private Game game;
	private Map map;
	
	private int defaultMap = 1;
	
	private Timer timer;
	public boolean isAnimating = false;
	public long animationStartTime;
	

    public GamePanel(Config configuration) {
    	this.config = configuration;
    	
    	this.setBackground(new Color(200, 200, 255));
    	this.setPreferredSize(new Dimension(config.SCREEN_HEIGHT,config.SCREEN_WIDTH));
    	this.setFocusable(true);
    	addMouseListener(new mouseClickDetection());
    	
    	menu = new Menu(config);
    	initializeGame(defaultMap);

    	timer = new Timer(config.FRAME_RATE ,this);
    	timer.start();
    	
    }
    
    public void startNextAnimation() {
    	isAnimating = true;
    	animationStartTime = System.currentTimeMillis();
    }
    
    public void stopAnimation() {
    	isAnimating = false;
    }
    
    public double animationCompleteness() {
    	long currentTime = System.currentTimeMillis();
    	double elapsedTime = (double) (currentTime - animationStartTime);
    	double completeness = elapsedTime / config.getAnimationDuration();
    	if (completeness > 1) {
    		this.stopAnimation();
    	}
    	return completeness;
    }
    
    public void initializeGame(int defaultMap) {
    	game = new Game(config, defaultMap);
    	map = game.getMap();
    	
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	Graphics2D g2d = (Graphics2D) g;
    	game.drawGame(g2d);
    	menu.drawMenu(g2d);
    	
    	if (game.playerDied()) {
    		Util.drawMessage(config, g2d, Util.GAME_OVER);
    	}
    	
    	if (game.playerEscaped()) {
    		Util.drawMessage(config, g2d, Util.GAME_WIN);
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (isAnimating) {
			double completeness = this.animationCompleteness();
			game.movePlayer(completeness);
			game.moveBoss(completeness);
		}
		
		
		this.repaint();
	}

	
	
	class mouseClickDetection implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (isAnimating) {
				return;
			}
			
			int mapChoice = menu.detectChoice(e.getX(), e.getY());
			if (mapChoice != -1 && mapChoice != game.getDefaultMap()) {
				defaultMap = mapChoice;
				menu.setCurrentMap(mapChoice);
				initializeGame(defaultMap);
				return;
			}
			
			if (game.playerDied()) {
				initializeGame(defaultMap);
				return;
			}
			
			if (game.playerEscaped()) {
				return;
			}
			
			int playerNextLoc = map.detectVertex(e.getX(), e.getY());
			if (game.isMovable(playerNextLoc)) {
				game.setPlayerNextLoc(playerNextLoc);
				game.setBossNextLoc();
				startNextAnimation();
				return;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

}
