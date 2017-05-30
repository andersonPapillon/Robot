package br.com.contaazulchallenge.robot.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.contaazulchallenge.robot.dto.DirectionToWalk;
import br.com.contaazulchallenge.robot.dto.Robot;
import br.com.contaazulchallenge.robot.exception.InvalidCommand;
import br.com.contaazulchallenge.robot.exception.InvalidOrientation;
import br.com.contaazulchallenge.robot.exception.InvalidPosition;
import br.com.contaazulchallenge.robot.service.RobotService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotTest {
	
	@Autowired
	RobotService robotservice;
	
	/* Test to validate limit position */
	@Test(expected = InvalidPosition.class)
	public void test_MMMMMMMMMMMMMMMMMMMMMMMM() throws InvalidCommand, InvalidOrientation, InvalidPosition {
		String commands = "MMMMMMMMMMMMMMMMMMMMMMMM";
		this.robotservice.executeCommands(commands);
	}
	
	/* Test to validate invalid commands */
	@Test(expected = InvalidCommand.class) 
	public void test_AAA() throws InvalidCommand, InvalidOrientation, InvalidPosition {
		String commands = "AAA";
		this.robotservice.executeCommands(commands);
	}
	
	/* Test to validate final position return */
	@Test
	public void test_GETFINALPOS() throws InvalidCommand, InvalidOrientation, InvalidPosition{
		String commands = "MMM";
		
		Robot expectedPosition = this.robotservice.executeCommands(commands);
				
		Assert.assertEquals(expectedPosition.getxPos(), robotservice.getFinalPosition().getxPos());
		Assert.assertEquals(expectedPosition.getyPos(), robotservice.getFinalPosition().getyPos());
		Assert.assertEquals(expectedPosition.getDirectionToWalk(), robotservice.getFinalPosition().getDirectionToWalk());
		
	}

	/* Test to validate commands */
	@Test
	public void test_MMRMMRMM() throws InvalidCommand, InvalidOrientation, InvalidPosition {
		String commands = "MMRMMRMM";
		
		Robot lastPostion = this.robotservice.executeCommands(commands);
		
		Robot expectedPosition = new Robot(2, 0, DirectionToWalk.SOUTH);
		Assert.assertEquals(lastPostion.getxPos(), expectedPosition.getxPos());
		Assert.assertEquals(lastPostion.getyPos(), expectedPosition.getyPos());
		Assert.assertEquals(lastPostion.getDirectionToWalk(), expectedPosition.getDirectionToWalk());
	}
	
	/* Test to validate commands */
	@Test
	public void test_M() throws InvalidCommand, InvalidOrientation, InvalidPosition {
		String command = "M";
				
		Robot lastPostion = this.robotservice.executeCommands(command);
		
		Robot expectedPosition = new Robot(0, 1, DirectionToWalk.NORTH);
		Assert.assertEquals(lastPostion.getxPos(), expectedPosition.getxPos());
		Assert.assertEquals(lastPostion.getyPos(), expectedPosition.getyPos());
		Assert.assertEquals(lastPostion.getDirectionToWalk(), expectedPosition.getDirectionToWalk());
	}
	
	/* Test to validate commands */
	@Test
	public void test_MML_MML() throws InvalidCommand, InvalidOrientation, InvalidPosition {
		String commands = "MML";
		
		Robot lastPostion = this.robotservice.executeCommands(commands);
		
		Robot expectedPosition = new Robot(0, 2, DirectionToWalk.WEST);
		
		Assert.assertEquals(lastPostion.getxPos(), expectedPosition.getxPos());
		Assert.assertEquals(lastPostion.getyPos(), expectedPosition.getyPos());
		Assert.assertEquals(lastPostion.getDirectionToWalk(), expectedPosition.getDirectionToWalk());
		
		lastPostion = this.robotservice.executeCommands(commands);
		
		Assert.assertEquals(lastPostion.getxPos(), expectedPosition.getxPos());
		Assert.assertEquals(lastPostion.getyPos(), expectedPosition.getyPos());
		Assert.assertEquals(lastPostion.getDirectionToWalk(), expectedPosition.getDirectionToWalk());
	}
	
	/* Test to validate commands */
	@Test
	public void test_MML() throws InvalidCommand, InvalidOrientation, InvalidPosition {
		String commands = "MML";
		Robot lastPostion = this.robotservice.executeCommands(commands);
		
		Robot expectedPosition = new Robot(0, 2, DirectionToWalk.WEST);
		Assert.assertEquals(lastPostion.getxPos(), expectedPosition.getxPos());
		Assert.assertEquals(lastPostion.getyPos(), expectedPosition.getyPos());
		Assert.assertEquals(lastPostion.getDirectionToWalk(), expectedPosition.getDirectionToWalk());
	}

}
