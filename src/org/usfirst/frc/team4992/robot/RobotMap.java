package org.usfirst.frc.team4992.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//systems - drive, climb, gear
	//CANtalon will be from 70-75
	
	//Drive
	public static int
		backLeftMotor = 40,//CANtalon
		backRightMotor = 41,//CANtalon
		frontLeftMotor = 42,//CANtalon
		frontRightMotor = 43,//CANtalon
		//Climb
		climberGear = 45,//CANtalon
		//Gear
		solenoidOutArms = 6,//Solenoid
		solenoidInArms = 7,//Solenoid
		solenoidPlatformUp = 8,//Solenoid
		solenoidPlatformDown =9,//Solenoid
		kicker = 10;//Not sure yet
	

	
}
