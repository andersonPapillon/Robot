package br.com.contaazulchallenge.robot.dto;

/* Class to represent the robot */
public class Robot {
	
	private int xPos;
	private int yPos;
	private DirectionToWalk directiontowalk;
	
	Robot(){};
	
	Robot(int x, int y){
		this.xPos = x;
		this.yPos = y;
	}
	
	public Robot(int x, int y, DirectionToWalk direction){
		this.xPos = x;
		this.yPos = y;
		this.directiontowalk = direction;
	}
	
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public DirectionToWalk getDirectionToWalk() {
		return directiontowalk;
	}
	public void setDirectionToWalk(DirectionToWalk directionToWalk) {
		this.directiontowalk = directionToWalk;
	}
	
	@Override
	public String toString(){
		return " (" + this.getxPos() + ", " + this.getyPos() + ", " + this.getDirectionToWalk() + ")";
	}
	
}
