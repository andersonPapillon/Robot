package br.com.contaazulchallenge.robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.contaazulchallenge.robot.dto.Robot;
import br.com.contaazulchallenge.robot.exception.InvalidCommand;
import br.com.contaazulchallenge.robot.exception.InvalidOrientation;
import br.com.contaazulchallenge.robot.exception.InvalidPosition;
import br.com.contaazulchallenge.robot.service.RobotService;

/* Communication control class */
@RestController
public class RobotController {
	
	@Autowired
	RobotService robotservice;
	
	/* URL to return final position */
	@RequestMapping (value="/rest/mars", method=RequestMethod.GET)
	public ResponseEntity<?> getInitialPosition(){
		
		Robot initialPosition = robotservice.getFinalPosition();
		return ResponseEntity.status(HttpStatus.OK).body(initialPosition.toString());
		
	}
	
	/* URL to move robot on mars */
	@RequestMapping (value="/rest/mars/{commands}", method=RequestMethod.POST)
	public ResponseEntity<?> setPosition(@PathVariable("commands") String commands ){
		
		try{
			Robot lastPosition = this.robotservice.executeCommands(commands);
			return ResponseEntity.status(HttpStatus.OK).body(lastPosition.toString());
			
		}catch (InvalidOrientation | InvalidCommand | InvalidPosition e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
	}
}
