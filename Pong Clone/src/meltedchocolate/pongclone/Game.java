package meltedchocolate.pongclone;

import javax.swing.JFrame; //JFrame
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class Game extends JPanel{
	//Variable Declaration
	public String title = "Pong Clone";
	int ballAmount = 1;
	public boolean gamePaused = false;
	Ball ball1 = new Ball(this, 0, 0, 1, 1);
	Ball ball2 = new Ball(this, 100, 0, 1, 2);
	Racket racket = new Racket(this);
	Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				racket.keyReleased(e);
				pause(e);
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
		if (gamePaused) {
			g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 30));
			g2d.drawString("PAUSED", 180, 250);
		}
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
	
	public void pause(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (gamePaused) {
				gamePaused = false;
			}
			else if (!gamePaused) {
				gamePaused = true;
			}
		}
	}
	
	public Object[] selection = {"1", "2"};
	
	public static void main(String[] args) throws InterruptedException {
		Game game = new Game();
		JFrame frame = new JFrame(game.title);
		frame.add(game);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
		game.ballAmount = Integer.parseInt((String)JOptionPane.showInputDialog(null, "1 or 2 balls?", "Ball Amount", JOptionPane.QUESTION_MESSAGE, null, game.selection, "1"));
		} catch (NumberFormatException e) {
			System.exit(ABORT);
		}
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
				if (!game.gamePaused) {
					game.move();
					
					game.gameOver();
				}
				game.repaint();
				Thread.sleep(10);
			}
	}
}
/*
99 little bugs in the code,
99 little bugs.
Take one down, patch it around,
127 little bugs in the code.
*/