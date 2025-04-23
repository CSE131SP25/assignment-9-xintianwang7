package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	private double movementRate;
	
	public Snake() {
		//FIXME - set up the segments instance variable
		deltaX = 0;
		deltaY = 0;
		segments = new LinkedList<>();
		segments.add(new BodySegment(0, 0, SEGMENT_SIZE));
		//System.out.println("Snake initialized with " + segments.size() + " segment(s)");
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		BodySegment head = segments.getFirst();
		double updateX = head.getX() + deltaX;
		double updateY = head.getY() + deltaY;
		BodySegment updatedHead = new BodySegment(updateX, updateY, SEGMENT_SIZE);
		segments.addFirst(updatedHead);
		segments.removeLast();
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment segments:segments) {
			segments.draw();
		}	
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.getFirst();
		double distanceX = head.getX() - f.getX();
		double distanceY = head.getY() - f.getY();
		
		double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
		
		movementRate = MOVEMENT_SIZE;
		if (distance < SEGMENT_SIZE) {
			double updateX = head.getX() + deltaX;
			double updateY = head.getY() + deltaY;
			BodySegment updatedHead = new BodySegment(updateX, updateY, SEGMENT_SIZE);
			segments.addFirst(updatedHead);
			
			movementRate *= 0.95;
			
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		BodySegment head = segments.getFirst();
		double x = head.getX();
		double y = head.getY();
		
		if (x >= 0 && x <= 1 && y >= 0 && y <= 1) {
			return true;
		} else {
			return false;
		}
	}
}
