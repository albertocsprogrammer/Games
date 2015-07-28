package games;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racket {
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	
	int x = 150;
	int vx = 0;
	int y = 400;
	int vy = 0;
	int speed = 6;
	private Game game;
	
	public Racket(Game game) {
		this.game = game;
	}
	
	public void move() {
		if (x + vx > 0 && x + vx < game.getWidth()-WIDTH && y + vy > 0 && y + vy < game.getHeight()) {
			x = x + vx;
			y = y + vy;
		}
	}
	
	public void paint(Graphics2D g) {
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	public void keyReleased(KeyEvent e) {
		vx = 0;
		vy = 0;
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			vx = -speed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			vx = speed;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			vy = -speed;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			vy = speed;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public int getTopY() {
		return y;
	}
	
	public int getBottomY() {
		return y - HEIGHT;
	}
	
	public int getLeftX() {
		return x;
	}
	
	public int getRightX() {
		return x - WIDTH;
	}
}
