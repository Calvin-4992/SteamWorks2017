package org.usfirst.frc.team4992.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//systems - drive, climb, gear
	
					//Drive
	public static int
					backLeftMotor = 1,//CANtalon
					backRightMotor = 2,//CANtalon
					frontLeftMotor = 3,//CANtalon
					frontRightMotor = 4,//CANtalon
					//Climb
					climberGear = 5,//CANtalon(probably)
					//Gear
					solenoidOutArms = 6,//Solenoid
					solenoidInArms = 7,//Solenoid
					solenoidPlatformUp = 8,//Solenoid
					solenoidPlatformDown =9,//Solenoid
					kicker = 10;//Not sure yet
	

	
}
