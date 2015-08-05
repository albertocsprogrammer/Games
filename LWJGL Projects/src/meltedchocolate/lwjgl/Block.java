package meltedchocolate.lwjgl;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Rectangle;
import java.util.Arrays;

public class Block {
	float width;
	float height;
	float x;
	float y;
	float leftBound = x - (width / 2);
	float bottomBound = y - (height / 2);
	float rightBound = x + (width / 2);
	float topBound = y + (height / 2);
	float vx = 0;
	float vy = 0;
	int collidingSide = 0;
	boolean colliding = false;
	public Block(float width, float height, float x, float y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public void draw() {
		glBegin(GL_QUADS);
			glVertex2f(x - (width / 2), y + (height / 2)); //top left
			glVertex2f(x - (width / 2), y - (height / 2)); //bottom left
			glVertex2f(x + (width / 2), y - (height / 2));	//bottom right
			glVertex2f(x + (width / 2), y + (height / 2));	//top right
		glEnd();
	}
	
	public void updateBounds() {
		leftBound = x - (width / 2);
		bottomBound = y - (height / 2);
		rightBound = x + (width / 2);
		topBound = y + (height / 2);
	}
	public boolean isColliding(Block block) {
		if (leftBound > block.leftBound && bottomBound > block.bottomBound && leftBound < block.rightBound && bottomBound < block.topBound) //Bottom Left
			colliding = true;
		else if (rightBound < block.rightBound && bottomBound > block.bottomBound && rightBound > block.leftBound && bottomBound < block.topBound) //Bottom Right
			colliding = true;
		else if (leftBound > block.leftBound && topBound < block.topBound && leftBound < block.rightBound && topBound > block.bottomBound) //Top Left
			colliding = true;
		else if (rightBound < block.rightBound && topBound < block.topBound && rightBound > block.leftBound && topBound > block.bottomBound) // Top Right
			colliding = true;
		else
			colliding = false;
		
		return colliding;
	}
	
	//TODO \V/ fix this method, it's really wierd... i'm bad at describing things :/
	public int collisionSide(Block block, float padding) { //returns 0 (can't determine side), 1 (left), 2 (right), 3 (top), or 4 (bottom)
		if (colliding = true) {
			if (rightBound == block.leftBound) {
				collidingSide = 1;
			}
			else if (leftBound == block.rightBound) {
				collidingSide = 2;
			}
			else if (bottomBound >= block.topBound - padding && bottomBound <= block.topBound + padding) {
				collidingSide = 3;
			}
			else if (topBound <= block.bottomBound + padding && topBound >= block.bottomBound - padding) {
				collidingSide = 4;
			}
			else {
				collidingSide = 0;
			}
			
		}
		else {
			collidingSide = 0;
		}
		
		return collidingSide;
	}
	
}
