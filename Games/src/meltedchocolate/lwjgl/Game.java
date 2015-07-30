package meltedchocolate.lwjgl;

import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

 
import java.nio.ByteBuffer;
 
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Game {
	private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
    private GLFWCursorPosCallback mouseCallback;
    
    public int WIDTH = 800;
	public int HEIGHT = 800;
	
	MouseHandler mouseHandler = new MouseHandler();
	
	public double mouseX = mouseHandler.x;
	public double mouseY = mouseHandler.y;
	
	Game() {
		
	}
    
    private long window;
    public Block block = new Block(0.1f, 0.1f, 0.0f, 0.0f, 0.0f, 0.0f);
    public Block projectile = new Block(0.01f, 0.01f, mouseHandler.x, mouseHandler.y, 0.0f, 0.0f);
    
    public void run() {
    	try {
    		Thread thread = new Thread(null, null, "Moving Block");
    		init();
    		loop();
    		
    		glfwDestroyWindow(window);
    		keyCallback.release();
    		mouseCallback.release();
    	} finally {
    		glfwTerminate();
    		errorCallback.release();
    	}
    }
    
    private void init() {
    	//setup error callback
    	glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
    	
    	//initialize GLFW
    	if (glfwInit() !=GL_TRUE)
    		throw new IllegalStateException("Unable to Initialize GLFW");
    		
    	glfwWindowHint(GLFW_VISIBLE, GL_TRUE); //its not visible immediately
    	glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
    	
    	
    	
    	window = glfwCreateWindow(WIDTH, HEIGHT, "Movable Block", NULL, NULL); //create the window
    	if (window == NULL) //if the window doesn't exist for some reason then throw an error
    		throw new RuntimeException("Failed to create window");
    	
    	//setup key callback, it'll be called every time a key is pressed
    	glfwSetKeyCallback(window, keyCallback = new KeyboardHandler());
    	//setup mouse callback, it'll be called everytime the mouse moves.
    	glfwSetCursorPosCallback(window, mouseCallback = new MouseHandler());

    	
    	//get resolution
    	ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
    	
    	//center the window
    	glfwSetWindowPos(
    		window,
    		(GLFWvidmode.width(vidmode) - WIDTH) / 2,
    		(GLFWvidmode.height(vidmode) - HEIGHT) / 2
    	);
    	
    	glfwMakeContextCurrent(window); //Apply the above context
    	glfwSwapInterval(1); //Enable V-Sync
    	glfwShowWindow(window); //Make the window visible again
    }
    
    private void loop() { //Rendering and etc.
    	GLContext.createFromCurrent();
    	glClearColor(0.9f, 0.9f, 0.9f, 0.0f); //set background color
    	while (glfwWindowShouldClose(window) == GL_FALSE) {
    		//Controls
    		if (KeyboardHandler.isKeyDown(GLFW_KEY_ESCAPE))
    			glfwSetWindowShouldClose(window, GL_TRUE);
    		if (KeyboardHandler.isKeyDown(GLFW_KEY_UP))
    			block.y = block.y + 0.008f;
    		if (KeyboardHandler.isKeyDown(GLFW_KEY_DOWN))
    			block.y = block.y - 0.008f;
    		if (KeyboardHandler.isKeyDown(GLFW_KEY_RIGHT))
    			block.x = block.x + 0.008f;
    		if (KeyboardHandler.isKeyDown(GLFW_KEY_LEFT))
    			block.x = block.x - 0.008f;
    		
    		//Physics
    		
    		if (block.x >= 1.0f - (block.width / 2))
    			block.x = 1.0f - (block.width / 2) - 0.008f;
    		else if (block.x <= -1.0f + (block.width / 2))
    			block.x = -1.0f + (block.width / 2) + 0.008f;
    		else if (block.y >= 1.0f - (block.height / 2))
    			block.y = 1.0f - (block.height / 2) - 0.008f;
    		else if (block.y <= -1.0f + (block.height / 2))
    			block.y = -1.0f + (block.height / 2) + 0.008f;
    		//TODO fix glitch where block slips into the corners
    		
    		block.x = block.x + block.vx;
    		block.y = block.y + block.vy;
    		
    		projectile.x = this.mouseHandler.getMouseX();
    		projectile.y = this.mouseHandler.getMouseY();
    		
    		//System.out.println("Y: " + mouseHandler.y + " X: " + mouseHandler.x);
    		//Graphics
    		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); //Clear the framebuffer
    		glColor3f(1.0f, 0.0f, 0.0f);
    		block.draw(); //draw the block
    		glColor3f(0.0f, 0.0f, 1.0f);
    		projectile.draw();
    		
    		glfwSwapBuffers(window); //swap buffers
    		glfwPollEvents(); //Poll for window events
    	}
    }
    
    public static void main(String[] args) throws InterruptedException {
		new Game().run();
	}
    
}