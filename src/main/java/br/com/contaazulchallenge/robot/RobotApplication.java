package br.com.contaazulchallenge.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Run Application and show up some instructions in log */
@SpringBootApplication
public class RobotApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotApplication.class, args);
		
		System.out.println(" ___________________________________________________________ ");
		System.out.println("|******  INSTRUCTIONS FOR HANDLING THE ROBOT ON MARS  ******|");
        System.out.println("|-----------------------------------------------------------|");
        System.out.println("| 1 - Access URL: http://localhost:8080/rest/mars           |");
        System.out.println("| 2 - To return current position use GET method             |");
        System.out.println("| 3 - Move the robot with POST method + valid URL commands  |");
        System.out.println("| 4 - Robot area is limited in 5x5                          |");
        System.out.println("|___________________________________________________________|\n");
	}
	
}
