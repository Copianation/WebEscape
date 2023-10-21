
public class Config {
	
	final int FRAME_RATE = 10;

	final int SCREEN_HEIGHT = 600;
	final int SCREEN_WIDTH = 600;

	private int dotPerLine = 3;
	private int gapLength = SCREEN_WIDTH/(dotPerLine + 1);
	private int dotSize = (int) (0.53 * gapLength);
	private int lineSize = (int) (0.18 * dotSize);
	private int playerSize = (int) (0.6 * dotSize);
	
	private int animationDuration = gapLength*3;
	
	public Config() {
		
	}
	
	public int getLineSize() {
		return lineSize;
	}

	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}

	public int getDotSize() {
		return dotSize;
	}

	public void setDotSize(int dotSize) {
		this.dotSize = dotSize;
	}

	public int getDotPerLine() {
		return dotPerLine;
	}

	public void setDotPerLine(int dotPerLine) {
		this.dotPerLine = dotPerLine;
		this.gapLength = SCREEN_WIDTH/(dotPerLine + 1);
		this.dotSize = (int) (0.53 * gapLength);
		this.lineSize = (int) (0.18 * dotSize);
		this.playerSize = (int) (0.6 * dotSize);
		this.animationDuration = gapLength*3;
	}

	public int getGapLength() {
		return gapLength;
	}

	public void setGapLength(int gapLength) {
		this.gapLength = gapLength;
	}

	public int getPlayerSize() {
		return playerSize;
	}

	public void setPlayerSize(int playerSize) {
		this.playerSize = playerSize;
	}
	
	public int getAnimationDuration() {
		return animationDuration;
	}
	
	
}
