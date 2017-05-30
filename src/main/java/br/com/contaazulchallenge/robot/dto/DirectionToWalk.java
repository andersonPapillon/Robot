package br.com.contaazulchallenge.robot.dto;

/* Enum to set directions */
public enum DirectionToWalk {
	
	NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');
	
	private char value;
	
	DirectionToWalk(char value){
		this.value = value;
	}
	
	public char getValue(){
		return this.value;
	}
	
}
