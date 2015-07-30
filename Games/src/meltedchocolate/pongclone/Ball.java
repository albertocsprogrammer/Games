package meltedchocolate.pongclone;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 20;
	int x = 0;
	int y = 0;
	int vx;
	int vy;
	int hits = 0;
	boolean lost = false;
	boolean exists;
	private Game game;
	
	public Ball(Game game, int x, int y, int vx, int vy) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	void move() {
		if (exists){
			if (x + vx < 0)
				vx = -vx;
			if(x + vx > game.getWidth() - DIAMETER)
				vx = -vx;
			if(y + vy < 0)
				vy = -vy;
			if (y + vy > game.getHeight() - DIAMETER)
				lost = true;
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
	}
	
	public boolean collision() {
		return game.racket.getBounds().intersects(getBounds());
	}

	
	public void paint(Graphics2D g){
		if(exists)
			g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
}
