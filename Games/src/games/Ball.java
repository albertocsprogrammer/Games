package games;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 20;
	int x = 0;
	int y = 0;
	int speed = 2;
	int vx = 1;
	int vy = speed;
	int hits = 0;
	private Game game;
	
	public Ball(Game game) {
		this.game = game;
	}
	void move(){
		if (x + vx < 0)
			vx = -vx;
		if(x + vx > game.getWidth() - DIAMETER)
			vx = -vx;
		if(y + vy < 0)
			vy = -vy;
		if (y + vy > game.getHeight() - DIAMETER)
			game.gameOver();
		if (collision()){
			/*if (y + vy > game.racket.getTopY() - DIAMETER) {
				vy = game.racket.vy + -vy;
				y = game.racket.getTopY() - DIAMETER;
			}
			if (y + vy > game.racket.getBottomY()) {
				//vy = game.racket.vy + vy;
				//y = game.racket.getBottomY() + DIAMETER;
			} not quite ready to implement it getting hit on the bottom :/ */
			vy = game.racket.vy + -vy;
			y = game.racket.getTopY() - DIAMETER;
			vx = game.racket.vx + vx;
			hits++;
			
		}
		
		x = x + vx;
		y = y + vy;
	}
	
	private boolean collision() {
		return game.racket.getBounds().intersects(getBounds());
	}
	
	public void paint(Graphics2D g){
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
}
