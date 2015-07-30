package meltedchocolate.lwjgl;

import org.lwjgl.glfw.GLFWCursorPosCallback;

//Our MouseHandler class extends the abstract class
//abstract classes should never be instantiated so here
//we create a concrete that we can instantiate
public class MouseHandler extends GLFWCursorPosCallback {
	float x;
	float y;
	
	public MouseHandler() {
		
	}
	
	@Override
	public void invoke(long window, double xpos, double ypos) {
		// this basically just prints out the X and Y coordinates 
		// of our mouse whenever it is in our window
		Game game = new Game();
		this.x = (float)xpos / (game.WIDTH / 2) - 1;
		this.y = -((float)ypos / (game.HEIGHT / 2) - 1);
		//System.out.println(this.x + " " + this.y + " " + game.projectile.x + " " + game.mouseX);
	}
	
	public float getMouseX() {
		return this.x;
	}
	
	public float getMouseY() {
		return this.y;
	}
}