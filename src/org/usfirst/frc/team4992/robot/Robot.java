
package org.usfirst.frc.team4992.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4992.robot.commands.Climber;
import org.usfirst.frc.team4992.robot.commands.ExampleCommand;
import org.usfirst.frc.team4992.robot.commands.OperatorControled;
import org.usfirst.frc.team4992.robot.subsystems.Climb;
import org.usfirst.frc.team4992.robot.subsystems.Drive;
import org.usfirst.frc.team4992.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4992.robot.subsystems.GearLifter;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
		
	//varibles
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();//remove this then you remove the example subsystem
	//public static final OperatorControled operatorControled = new OperatorControled();
	public static final Climb objClimb = new Climb();
	public static final Drive drive = new Drive();
	public static final GearLifter gear = new GearLifter();
	public static OI oi;	
	
	protected static final int ImageWidth = 320;
	protected static final int ImageHeight = 240;
	
	//senors and random stuff
    Command autonomousCommand;
    UsbCamera camera;
    NetworkTable visionTable;
    double COGX;
	double COG_Y;
	double COG_SIZE;
	double COG_SIZEThresh;
    
    //Motor controllers
    CANTalon motorLeftBack; 
	CANTalon motorRightBack;
	CANTalon motorLeftFront ;
	CANTalon motorRightFront;
	public static RobotDrive driveRobot;
	Talon motorLeftBackTest; 
	Talon motorRightBackTest;
	Jaguar motorLeftFrontTest;
	Jaguar motorRightFrontTest;
	public static RobotDrive driveRobotTest;
	public static boolean reverseDriveActive;
	
	//NetworkTable visionTable; 
    
  //------------------------FRC methods-------------------------
    public void robotInit() {
    	oi = new OI();

        
        motorLeftBack = new CANTalon(RobotMap.backLeftMotor);
    	motorRightBack = new CANTalon(RobotMap.backRightMotor);
    	motorLeftFront = new CANTalon(RobotMap.frontLeftMotor);
    	motorRightFront = new CANTalon(RobotMap.frontRightMotor);
    	driveRobot = new RobotDrive(motorLeftFront,motorLeftBack,motorRightFront,motorRightBack);
    	motorLeftBackTest = new Talon(1);
    	motorRightBackTest = new Talon(2);
    	motorLeftFrontTest = new Jaguar(3);
    	motorRightFrontTest = new Jaguar(4);
    	driveRobotTest = new RobotDrive(motorLeftFrontTest,motorLeftBackTest,motorRightFrontTest,motorRightBackTest);
    	//motorLeftBackTest= new Ja
    	//driveTestBot
    	reverseDriveActive = false;
    	
    	//Camera setup for vision
    	camera = CameraServer.getInstance().startAutomaticCapture();
    	MjpegServer server = new MjpegServer(CameraServer.getInstance().toString(),1181);
    	
    	
    }
	
    public void testPeriodic() {
    	
     }
    public void autonomousInit() {
    	visionTable= NetworkTable.getTable("RoboRealm");
		COGX = visionTable.getNumber("COG_X", 0.0);
    	COG_Y = visionTable.getNumber("COG_Y", 0.0);
		COG_SIZE = visionTable.getNumber("COG_BOX_SIZE", 0.0);
		COG_SIZEThresh = 100;
    	System.out.println("COG X:" + COGX + "\tCOG Y" + COG_Y + "\tCOG SIZE" + COG_SIZE);
    	
    }

    public void autonomousPeriodic() {
    	driveRobotTest.arcadeDrive(oi.stick);
    	/*
    	if(COG_SIZE<COG_SIZEThresh){
    		driveRobot.arcadeDrive(0.2, 0.0);
    		System.out.println(COG_SIZEThresh);
    	} else {
    		System.out.println("Stoping");
    		driveRobot.arcadeDrive(0.0, 0.0);
    	}
    	*/
    }

    public void teleopInit() {
    }

    public void teleopPeriodic() {
    	//operatorControled.start();
    }
    
    
    
    //-------------Other non FRC provided methods-------------------------
    
    public boolean switchReverseDrive(boolean currentReverseDriveBooleanValue){//use this method by setting the revsere boolean to this method and put itself in the parameter EXAMPLE: reverseOn = switchReverseDrive(reverseOn); 
    	if(currentReverseDriveBooleanValue){
    		return false;
    	} else{
    		return true;
    	}//end of if-else
    }//end of switchReverseDrive method
    
    //Sets the rumble of the joystick - smallRotateVal is the rumble for the left side - largeRotateVal is for the right side
    public void rumbleJoystick(float smallRumbleVal,float largeRumbleVal){
    	OI.stick.setRumble(RumbleType.kLeftRumble, smallRumbleVal);
		OI.stick.setRumble(RumbleType.kRightRumble, largeRumbleVal);
    }//end of rumble method
    
}
