
package org.usfirst.frc.team4992.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.opencv.core.Mat;
import org.usfirst.frc.team4992.robot.commands.ExampleCommand;
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
	public static final Drive drive = new Drive();
	public static final Climb climb = new Climb();
	public static final GearLifter gear = new GearLifter();
	public static OI oi;	
	
	protected static final int ImageWidth = 320;
	protected static final int ImageHeight = 240;
	
	//senors and random stuff
    Command autonomousCommand;
    SendableChooser chooser;
    NetworkTable vision;
    Pipeline pipe;
    UsbCamera camera;
    
    //Motor controllers
    CANTalon motorLeftBack; 
	CANTalon motorRightBack;
	CANTalon motorLeftFront ;
	CANTalon motorRightFront;
	public static RobotDrive driveRobot;
	public static boolean reverseDriveActive;
	
    NetworkTable CCTest;//a test network table for the grip vision soft ware(but GRIP doesn't write information to netwrok tables so we will need to use and convert Mat into and array or just upload the mat if possible)
    public Robot(){
    	CCTest = NetworkTable.getTable("GRIP/myContoursReport");//"GRIP/mycontoursreport is were the information should be stored"
    }
    
  //------------------------FRC methods-------------------------
    public void robotInit() {
    	oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
        SmartDashboard.putData(drive);
        SmartDashboard.putData("Auto mode", chooser);
        vision = NetworkTable.getTable("GRIP/myContoursReport");
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(ImageWidth,ImageHeight);
        pipe = new Pipeline ();
        
        motorLeftBack = new CANTalon(RobotMap.backLeftMotor);
    	motorRightBack = new CANTalon(RobotMap.backRightMotor);
    	motorLeftFront = new CANTalon(RobotMap.frontLeftMotor);
    	motorRightFront = new CANTalon(RobotMap.frontRightMotor);
    	driveRobot = new RobotDrive(motorLeftFront,motorLeftBack,motorRightFront,motorRightBack);
    	reverseDriveActive = false;
    }
	
    public void testPeriodic() {

    	//cc
    	Mat testCCMAT = new Mat();
    	double[] defaultVal = new double[0];
    		double[] areas = CCTest.getNumberArray("area", defaultVal);
    		System.out.print("area: ");
    		for(double area: areas){
    			System.out.print(area + " ");
    		}
    		System.out.println();
    		Timer.delay(1);
    	///cc
    	//NetworkTable visiontable = NetworkTable.getTable("vision");
    	//boolean shouldFire = visiontable.getvalue();
    	
    	//cc

        LiveWindow.run();
    }
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
    }
    
    
    
    //-------------Other non FRC provided methods-------------------------
    
    public boolean switchReverseDrive(boolean currentReverseDriveBooleanValue){//use this method by setting the revsere boolean to this method and put itself in the parameter EXAMPLE: reverseOn = switchReverseDrive(reverseOn); 
    	if(currentReverseDriveBooleanValue){
    		return false;
    	} else{
    		return true;
    	}
    }
    
}
