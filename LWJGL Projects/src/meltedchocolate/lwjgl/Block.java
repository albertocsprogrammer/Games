package meltedchocolate.lwjgl;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Block {
	float width;
	float height;
	public float x;
	public float y;
	float vx;
	float vy;
	public Block(float width, float height, float x, float y, float vx, float vy) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	
	public void draw() {
		glBegin(GL_QUADS);
			glVertex2f(x - (width / 2), y + (height / 2)); //top left
			glVertex2f(x - (width / 2), y - (height / 2)); //bottom left
			glVertex2f(x + (width / 2), y - (height / 2));	//bottom right
			glVertex2f(x + (width / 2), y + (height / 2));	//top right
		glEnd();
	}
	
}
