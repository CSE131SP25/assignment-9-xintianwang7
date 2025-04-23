package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	
	private Snake snake;
	private Food food;

	
	public Game() {
		StdDraw.enableDoubleBuffering();
		snake = new Snake();
		food = new Food();
	}
	
	public void play() {
		intro();
		
		while (snake.isInbounds()) { //TODO: Update this condition to check if snake is in bounds
			int dir = getKeypress();
			//Testing only: you will eventually need to do more work here
			System.out.println("Keypress: " + dir);
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
			
			if (dir != -1) { // at the bounds
				snake.changeDirection(dir);
			}
			if (snake.eatFood(food)){
				food = new Food();
			} else {
				snake.move();
			}
			updateDrawing();
		}
		over(); // end game when out of bounds
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		//FIXME
		
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
		
		StdDraw.clear();
		snake.draw();
		food.draw();
		StdDraw.pause(50);
		StdDraw.show();
	}
	
	private void intro() {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.PINK);
	    StdDraw.text(0.5, 0.6, "Welcome to the game!");
	    StdDraw.text(0.5, 0.5, "Use W A S D to move.");
		StdDraw.show();
		 while (!StdDraw.hasNextKeyTyped()) { // any key
		 }
	}
	
	private void over() {
	    StdDraw.clear();
	    StdDraw.setPenColor(StdDraw.PINK);
	    StdDraw.text(0.5, 0.5, "Game Over!");
	    StdDraw.text(0.5, 0.4, "Press Q to quit or R to restart");
	    StdDraw.show();
	    
	    while (true) {
	        if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
	            System.exit(0);
	        } else if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
	            Game g = new Game();
	            g.play();  // restart
	        }
	    }
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
