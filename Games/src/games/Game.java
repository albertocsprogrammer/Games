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
	public static String title = "Dual Balls";
	int ballAmount = 1;
	Ball ball1 = new Ball(this, 0, 0, 1, 1);
	Ball ball2 = new Ball(this, 100, 0, 1, 2);
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
		ball1.move();
		ball2.move();
		racket.move();
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLACK);
		ball1.paint(g2d);
		ball2.paint(g2d);
		racket.paint(g2d);
		g2d.drawString("Your score: " + (ball1.hits + ball2.hits), 10, 20);
	}
	
	public void gameOver() {
		if (ballAmount == 2) {
			if (ball1.lost && ball2.lost) {
				JOptionPane.showMessageDialog(this, "Game Over ):\n Your score was " + (ball1.hits + ball2.hits) + ".", "Game Over", JOptionPane.YES_NO_OPTION);
				System.exit(ABORT);
			}
		} else if (ballAmount == 1) {
			if (ball1.lost) {
				JOptionPane.showMessageDialog(this, "Game Over ):\n Your score was " + (ball1.hits + ball2.hits) + ".", "Game Over", JOptionPane.YES_NO_OPTION);
				System.exit(ABORT);
			}
		}
	}
	
	public Object[] selection = {"1", "2"};
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame(title);
		Game game = new Game();
		frame.add(game);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.ballAmount = Integer.parseInt((String)JOptionPane.showInputDialog(null, "1 or 2 balls?", "Ball Amount", JOptionPane.QUESTION_MESSAGE, null, game.selection, "1"));
		if (game.ballAmount == 1) {
			game.ball1.exists = true;
			game.ball2.exists = false;
		}
		else if (game.ballAmount == 2) {
			game.ball1.exists = true;
			game.ball2.exists = true;
		}
		else {
			System.exit(ABORT);
		}
		
		while (true) { //Game Loop
			game.move();
			game.repaint();
			game.gameOver();
			Thread.sleep(10);
		}
	}
}
