package games;

import javax.swing.JFrame; //JFrame
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

@SuppressWarnings("serial")
public class Game extends JPanel{
	//Variable Declaration
	public static String title = "First Game";
	Ball ball = new Ball(this);
	Racket racket = new Racket(this);
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				racket.keyReleased(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				racket.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	private void move() {
		ball.move();
		racket.move();
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLUE);
		ball.paint(g2d);
		racket.paint(g2d);
		g2d.drawString("Your score: " + ball.hits, 10, 20);
	}
	
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "Game Over ):\n Your score was " + ball.hits + "", "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame(title);
		Game game = new Game();
		frame.add(game);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) { //Game Loop
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
