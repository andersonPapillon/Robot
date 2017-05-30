package br.com.contaazulchallenge.robot.service;

import org.springframework.stereotype.Service;

import br.com.contaazulchallenge.robot.dto.DirectionToWalk;
import br.com.contaazulchallenge.robot.dto.Robot;
import br.com.contaazulchallenge.robot.exception.InvalidCommand;
import br.com.contaazulchallenge.robot.exception.InvalidOrientation;
import br.com.contaazulchallenge.robot.exception.InvalidPosition;

/* Service class to control the robot */
@Service
public class RobotService {
	
	/* The robot is limited to walking only 5x5 */
	private static final int MAX_X = 4;
	private static final int MIN_X = 0;
	private static final int MAX_Y = 4;
	private static final int MIN_Y = 0;
	
	/* URL command only accepts 'M', 'L' and 'R' */
	private static final char MOVE_LEFT    = 'L';
	private static final char MOVE_RIGHT   = 'R';
	private static final char MOVE_FORWARD = 'M';
	
	private Robot robotFinalPosition = new Robot(0, 0, DirectionToWalk.NORTH);
	
	/* Get final position */
	public Robot getFinalPosition(){
		return this.robotFinalPosition;
	}
	
	/* Set final position */
	public void setFinalPosition(Robot currentPosition){
		this.robotFinalPosition = currentPosition;
	}
	
	/* Check if URL is Invalid */
	public boolean isUrlInvalid(String urlcommands){
		for (int i = 0; i < urlcommands.length(); i++){
			if (MOVE_LEFT != urlcommands.charAt(i) && MOVE_RIGHT != urlcommands.charAt(i) && MOVE_FORWARD != urlcommands.charAt(i)){
				return true;
			}
		}
		return false;
	}
	
	/* Turn robot to left */
	public DirectionToWalk moveToLeft(DirectionToWalk current) throws InvalidOrientation{
		
		switch (current){
		case EAST:
			return DirectionToWalk.NORTH;
		case NORTH:
			return DirectionToWalk.WEST;
		case WEST:
			return DirectionToWalk.SOUTH;
		case SOUTH:
			return DirectionToWalk.EAST;
		default:
			throw new InvalidOrientation();
		}
	}
	
	/* Turn robot to right */
	public DirectionToWalk moveToRight(DirectionToWalk current) throws InvalidOrientation{
		
		switch (current){
		case EAST:
			return DirectionToWalk.SOUTH;
		case SOUTH:
			return DirectionToWalk.WEST;
		case WEST:
			return DirectionToWalk.NORTH;
		case NORTH:
			return DirectionToWalk.EAST;
		default:
			throw new InvalidOrientation();
		}
		
	}
	
	/* Moves robot forward */
	public Robot moveToForward(Robot robotPosition) throws InvalidPosition, InvalidOrientation{
		
		switch (robotPosition.getDirectionToWalk()){
		case EAST:
			robotPosition.setxPos(robotPosition.getxPos() + 1);
			if (robotPosition.getxPos() > MAX_X){
				throw new InvalidPosition();
			}
			break;
		case WEST:
			robotPosition.setxPos(robotPosition.getxPos() - 1);
			if(robotPosition.getxPos() < MIN_X){
				throw new InvalidPosition();
			}
			break;
		case NORTH:
			robotPosition.setyPos(robotPosition.getyPos() + 1);
			if(robotPosition.getyPos() > MAX_Y){
				throw new InvalidPosition();
			}
			break;
		case SOUTH:
			robotPosition.setyPos(robotPosition.getyPos() - 1);
			if(robotPosition.getyPos() < MIN_Y){
				throw new InvalidPosition();
			}
			break;
		default:
			throw new InvalidOrientation();
		}
		return robotPosition;
	}
	
	/* Run all commands that comes from URL */ 
	public Robot executeCommands(String commands) throws InvalidOrientation, InvalidCommand, InvalidPosition{
			
		if(isUrlInvalid(commands)){
			throw new InvalidCommand();
		}else{
			
			Robot currentPosition = new Robot(0, 0, DirectionToWalk.NORTH);
			
			for(int i = 0; i < commands.length(); i++){
				switch (commands.charAt(i)){
				case MOVE_FORWARD:
					currentPosition = this.moveToForward(currentPosition);
					break;
				case MOVE_LEFT:
					currentPosition.setDirectionToWalk(this.moveToLeft(currentPosition.getDirectionToWalk()));
					break;
				case MOVE_RIGHT:
					currentPosition.setDirectionToWalk(this.moveToRight(currentPosition.getDirectionToWalk()));
					break;
				}
			}
			
			this.setFinalPosition(currentPosition);
			return currentPosition;
		}
		
	}
	
}
