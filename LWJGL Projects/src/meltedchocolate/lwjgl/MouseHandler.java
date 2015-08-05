package meltedchocolate.lwjgl;

import java.math.BigDecimal;

import org.lwjgl.glfw.GLFWCursorPosCallback;

//Our MouseHandler class extends the abstract class
//abstract classes should never be instantiated so here
//we create a concrete that we can instantiate
public class MouseHandler extends GLFWCursorPosCallback {
	Game game = new Game();
	public MouseHandler() {
		
	}
	
	@Override
	public void invoke(long window, double xpos, double ypos) {
		// this basically just prints out the X and Y coordinates 
		// of our mouse whenever it is in our window
		
		//game.projectile.x = round((float)xpos / (game.WIDTH / 2) - 1, 4);
		//game.projectile.y = round(-((float)ypos / (game.HEIGHT / 2) - 1), 4);
	}
	
	public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

}