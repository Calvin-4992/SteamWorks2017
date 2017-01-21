
package org.usfirst.frc.team4992.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
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

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final Drive drive = new Drive();
	public static final Climb climb = new Climb();
	public static final GearLifter gear = new GearLifter();
	public static OI oi;	
	
	protected static final int ImageWidth = 320;
	
	protected static final int ImageHeight = 240;
	

    Command autonomousCommand;
    SendableChooser chooser;
    NetworkTable vision;
    Pipeline pipe;
    UsbCamera camera;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    NetworkTable CCTest;
    public Robot(){
    	CCTest = NetworkTable.getTable("GRIP/myContoursReport");//"GRIP/mycontoursreport is were the information should be stored"
    }
    
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
    	//cc
		
        
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

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
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

    /**
     * This function is called periodically during autonomous
     */
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

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */

}
