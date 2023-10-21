import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class Game {
	private Config config;
	
    private Map map;
    
	int playerLoc;
	int playerNextLoc = -1;
	int playerX, playerY;
	boolean gotKey = false; 
	
	int bossLoc;
	int bossNextLoc = -1;
	int bossX, bossY;


    public Game(Config configuration, int defaultMap) {
    	this.config = configuration;
    	map = new Map(config, defaultMap);

    	playerLoc = map.getPlayerDefaultLoc();
    	bossLoc = map.getBossDefaultLoc();
    	playerX = map.x[playerLoc];
    	playerY = map.y[playerLoc];
    	bossX = map.x[bossLoc];
    	bossY = map.y[bossLoc];
    }
	
    public void drawPlayer(Graphics2D g2d) {
    	g2d.setPaint(new Color(255, 160, 160));
    	int playerSize = config.getPlayerSize();
    	g2d.fillOval(playerX-playerSize/2, playerY-playerSize/2, 
    			playerSize, playerSize);
    }
    
    public void drawBoss(Graphics2D g2d) {
    	g2d.setPaint(new Color(220, 160, 220));
    	int playerSize = config.getPlayerSize();
    	g2d.fillOval(bossX-playerSize/2, bossY-playerSize/2, 
    			playerSize, playerSize);
    }
    
    public void drawGame(Graphics2D g2d) {
    	map.drawMap(g2d);
    	drawPlayer(g2d);
    	drawBoss(g2d);
    }
    
    public void movePlayer(double completeness) {

    	if (completeness > 1) {
    		playerLoc = playerNextLoc;
    		playerX = map.x[playerLoc];
    		playerY = map.y[playerLoc];
    		
    		if (map.isKey(playerLoc)) {
    			gotKey = true;
    			map.hideKey();
    		}
    		return;
    	}
    	playerX = Util.smoothTransition(map.x[playerLoc], map.x[playerNextLoc], completeness);
    	playerY = Util.smoothTransition(map.y[playerLoc], map.y[playerNextLoc], completeness);
    }
    
    public void moveBoss(double completeness) {

    	if (completeness > 1) {
    		bossLoc = bossNextLoc;
    		bossX = map.x[bossLoc];
    		bossY = map.y[bossLoc];
    		return;
    	}
    	bossX = Util.smoothTransition(map.x[bossLoc], map.x[bossNextLoc], completeness);
    	bossY = Util.smoothTransition(map.y[bossLoc], map.y[bossNextLoc], completeness);   	
    }
    
    public boolean isMovable(int vertex) {
    	boolean movability = (vertex != -1);
    	movability &= (map.getNeighbors(playerLoc).contains(vertex));

    	return movability;
    }
    
    public void setPlayerNextLoc(int v) {
    	playerNextLoc = v;
    }
    
    public void setBossNextLoc() {
    	LinkedList<Integer> path = map.BFS(bossLoc, playerNextLoc);
    	bossNextLoc = path.get(0);
    }
    
    public boolean playerEscaped() {
    	return map.isExit(playerLoc) && gotKey;
    }
    
    public boolean playerDied() {
    	return (playerLoc == bossLoc && !playerEscaped());
    }

    public Map getMap() {
    	return map;
    }
    
    public int getDefaultMap() {
    	return map.defaultMap;
    }
    
}