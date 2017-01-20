package org.usfirst.frc.team4992.robot.subsystems;

import org.usfirst.frc.team4992.robot.RobotMap;
import org.usfirst.frc.team4992.robot.commands.StayGoodBoy;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {
    
	//set up the drive CANTalon speed controllers 
	CANTalon motorLeftBack = new CANTalon(RobotMap.backLeftMotor);
	CANTalon motorRightBack = new CANTalon(RobotMap.backRightMotor);
	CANTalon motorLeftFront = new CANTalon(RobotMap.frontLeftMotor);
	CANTalon motorRightFront = new CANTalon(RobotMap.frontRightMotor);
	RobotDrive drive = new RobotDrive(motorLeftFront,motorLeftBack,motorRightFront,motorRightBack);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new StayGoodBoy());
    }
    
    public void Stop(){
    	drive.arcadeDrive(0,0);
    }
    
    public void Backwards(){
    	drive.arcadeDrive(-0.75,0);
    }
    
    public void Forwards(){
    	drive.arcadeDrive(0.75,0);
    }
    
    public void SetSpeed(float ForwardValue, float TurnValue){
    	drive.arcadeDrive(ForwardValue, TurnValue);
    }
    
}

