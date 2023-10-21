import javax.swing.JFrame;

public class Main extends JFrame{

	public Main() {
		Config config = new Config();
		
		this.add(new GamePanel(config));
		this.setTitle("Web Escape");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		try {
			this.setLocationRelativeTo(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
        new Main();
    }
}
