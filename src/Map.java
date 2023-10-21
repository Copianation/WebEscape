import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map extends Graph{
	
	private Config config;
	
	public int defaultMap;
	
    public int[] x, y;
    public int exit;
    public int key;
    public boolean showKey = true;
    
    public int playerDefaultLoc;
    public int bossDefaultLoc;

    public void setExit(int vertex) {
    	exit = vertex;
    }
    
    public boolean isExit(int vertex) {
    	return vertex == exit;
    }
    
    public void setKey(int vertex) {
    	key = vertex;
    }
    
    public boolean isKey(int vertex) {
    	return vertex == key;
    }
    
    public void hideKey() {
    	showKey = false;
    }
    
	public void setPlayerDefaultLoc(int playerDefaultLoc) {
		this.playerDefaultLoc = playerDefaultLoc;
	}

	public int getPlayerDefaultLoc() {
		return playerDefaultLoc;
	}

	public void setBossDefaultLoc(int bossDefaultLoc) {
		this.bossDefaultLoc = bossDefaultLoc;
	}

	public int getBossDefaultLoc() {
		return bossDefaultLoc;
	}

	public Map(Config configuration, int defaultMap) {
    	this.config = configuration;
    	this.defaultMap = defaultMap;
    	this.createDefaultMap(defaultMap);
    }
    
    public void layoutMap() {
    	// Get coordinates of dots
    	x = new int[this.vertexNumber()];
    	y = new int[this.vertexNumber()];
    	
    	int gapLength = config.getGapLength();
    	int dotPerLine = config.getDotPerLine();
    	
    	for (int i : this.getAllVertices()) {
    		x[i] = gapLength * (i % dotPerLine) + gapLength;
    		y[i] = gapLength * (int) (i/dotPerLine) + gapLength;
    	}
    }
    
    public int detectVertex(int x, int y) {
    	int vertex = -1;
    	int dotRadius = config.getDotSize()/2;
    	for (int i: this.getAllVertices()) {
    		if (Math.abs(x-this.x[i]) < dotRadius && Math.abs(y-this.y[i]) < dotRadius) {
    			vertex = i;
    		}
    	}
    	return vertex;
    }
    
    public void drawMap(Graphics2D g2d) {
    	g2d.setStroke(new BasicStroke(config.getLineSize()));
    	g2d.setPaint(Color.white);
    	for (int i : this.getAllVertices()) {
    		for (int j : this.getNeighbors(i)) {
    			g2d.drawLine(x[i], y[i], x[j], y[j]);
    		}
    	}
    	
    	int dotSize = config.getDotSize();
    	for (int i : this.getAllVertices()) {

    		g2d.fillOval(x[i]-dotSize/2, y[i]-dotSize/2, dotSize, dotSize);
    	}
    	
    	g2d.setPaint(new Color(140,255,140));
    	g2d.fillOval(x[exit]-dotSize/2, y[exit]-dotSize/2, dotSize, dotSize);
    	
    	if (showKey) {
    		Util.drawKey(config, g2d, x[key], y[key]);
    		Util.drawLock(config, g2d, x[exit], y[exit]);
    	}
    	
    }
    
    
    
    public void createDefaultMap(int number) {
    	switch (number) {
    	case 1:
    		createDefaultMap1();
    		break;
    	case 2:
    		createDefaultMap2();
    		break;
    	case 3:
    		createDefaultMap3();
    		break;
    	}
    }
    
    
    public void createDefaultMap1() {
    	config.setDotPerLine(3);
    	
        this.addVertex(0);
        this.addVertex(1);
        this.addVertex(2);
        this.addVertex(3);
        this.addVertex(4);
        this.addVertex(5);
        this.addVertex(6);
        this.addVertex(7);
        this.addVertex(8);
        this.addEdge(0, 1);
        this.addEdge(1, 2);
        this.addEdge(1, 4);
        this.addEdge(3, 4);
        this.addEdge(0, 4);
        this.addEdge(2, 5);
        this.addEdge(3, 6);
        this.addEdge(5, 6);
        this.addEdge(3, 7);
        this.addEdge(7, 8);
        this.addEdge(5, 8);
        
        this.setExit(8);
        this.setKey(3);
        this.setPlayerDefaultLoc(0);
        this.setBossDefaultLoc(6);
        this.layoutMap();
        
    }
    
    public void createDefaultMap2() {
    	config.setDotPerLine(5);
    	
        for (int i = 0; i < 25; i++) {
        	this.addVertex(i);
        }
        for (int i = 0; i < 24; i++) {
        	if (i%5 != 4) {
        		this.addEdge(i, i+1);
        	}
        	if (i+5 < 25) {
        		this.addEdge(i, i+5);
        	}
        }
        this.setExit(24);
        this.setKey(18);
        this.setPlayerDefaultLoc(0);
        this.setBossDefaultLoc(19);
        this.layoutMap();
    }
    
    public void createDefaultMap3() {
    	config.setDotPerLine(5);
    	
    	double gapX = 0.125;
    	double gapY = 0.1666666666;

    	double[] relativeX = {1*gapX,2*gapX,2*gapX,3*gapX,4*gapX,4*gapX,5*gapX,6*gapX,6*gapX,7*gapX};
    	double[] relativeY = {3*gapY,2*gapY,4*gapY,3*gapY,1*gapY,5*gapY,3*gapY,2*gapY,4*gapY,3*gapY};
    	
    	x = new int[10];
    	y = new int[10];
    	
    	for (int i = 0; i < 10; i++) {
    		this.addVertex(i);
    		x[i] = (int) (relativeX[i]*config.SCREEN_WIDTH);
    		y[i] = (int) (relativeY[i]*config.SCREEN_HEIGHT);
    	}
    	
    	addEdge(0, 1);
    	addEdge(0, 2);
    	addEdge(1, 2);
    	addEdge(1, 3);
    	addEdge(2, 3);
    	addEdge(1, 4);
    	addEdge(3, 4);
    	addEdge(2, 5);
    	addEdge(3, 5);
    	addEdge(4, 6);
    	addEdge(4, 7);
    	addEdge(5, 6);
    	addEdge(5, 8);
    	addEdge(6, 7);
    	addEdge(7, 8);
    	addEdge(6, 8);
    	addEdge(7, 9);
    	addEdge(8, 9);
    	
        this.setExit(9);
        this.setKey(4);
        this.setPlayerDefaultLoc(0);
        this.setBossDefaultLoc(7);
    }

}