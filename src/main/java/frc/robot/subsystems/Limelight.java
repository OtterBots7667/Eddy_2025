package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.Robot;
import frc.robot.constants.MechConstants;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase{

    /* - - - - - - - - - Declare Variables & Components Here - - - - - - - - - */
    private static final Joystick driveJoy = new Joystick(0);
    private static final Joystick steerJoy = new Joystick(1);
    public boolean teleopHasStarted = Robot.teleopHasStarted;

    double KpAim = -0.01;
    double KpDistance = -0.1;
    double min_aim_command = 0.1;

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry getpipe = table.getEntry("getpipe");

    public static double driveStickY = 0.0;
    public static double driveStickX = 0.0;
    public static double steerStickX = 0.0;


    @ Override
    public void periodic(){
        teleopHasStarted = Robot.teleopHasStarted;
        //System.out.println("Other thing runnin");

        if(teleopHasStarted){

        /* - - - - - - - - - Teleop Loop Code Here - - - - - - - - - */

            //System.out.println("IT'S RUNNING!!!!!");


        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        double pipeline = getpipe.getDouble(0.0);

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("Pipeline", pipeline);

        if(driveJoy.getRawButton(MechConstants.limelightRightButtonID)){
            //Changes pipline to pipeline 0
            LimelightHelpers.setPipelineIndex("", 0);
            System.out.println("Right Button Pressed");

            //Definitions for adjustments
            double distanceY = 1 / ((1 / (Math.tan(x))) + 1);
            double distanceX = (1 / (Math.tan(x))) / ((1 / (Math.tan(x))) + 1);
            double hypotenuse = (5 * Math.cos(y)) / (Math.sin(y) * Math.cos(x));

            double controllerX = (1 / (Math.tan(x))) / ((1 / (Math.tan(x))));
            double controllerY = 1 / ((1 / (Math.tan(x))) + 1);

            boolean isParrallel = true;
            

            //Gets the robot parrellel to the reef
            if(((distanceX * distanceX) + (distanceY * distanceY)) > (0.9 * (hypotenuse * hypotenuse)) && ((distanceX * distanceX) + (distanceY * distanceY)) < (1.1 * (hypotenuse * hypotenuse))){
                steerStickX = 0.0;
                isParrallel = true;
                System.out.println("parrell");
            } else if((distanceX * distanceX) + (distanceY * distanceY) > (hypotenuse * hypotenuse)){
                steerStickX = -0.5;
                isParrallel = false;
                System.out.println("not parrell");
            } else if((distanceX * distanceX) + (distanceY * distanceY) < (hypotenuse * hypotenuse)){
                steerStickX = 0.5;
                isParrallel = false;
                System.out.println("parrell");
            }
            
            //Drives the robot to the reef, replaces controller input
            if(isParrallel == true){
                driveStickX = controllerX;
                driveStickY = controllerY;
            } else {
                driveStickX = 0;
                driveStickY = 0;
            }   
        
        } else if(driveJoy.getRawButton(MechConstants.limelightLeftButtonID)){
            //Changes pipline to pipeline 1
            LimelightHelpers.setPipelineIndex("", 1);
            System.out.println("Left Button Pressed");

            //Definitions for adjustments
            double distanceY = 1 / ((1 / (Math.tan(x))) + 1);
            double distanceX = (1 / (Math.tan(x))) / ((1 / (Math.tan(x))) + 1);
            double hypotenuse = (5 * Math.cos(y)) / (Math.sin(y) * Math.cos(x));

            double controllerX = (1 / (Math.tan(x))) / ((1 / (Math.tan(x))));
            double controllerY = 1 / ((1 / (Math.tan(x))) + 1);

            boolean isParrallel = true;
            

            //Gets the robot parrellel to the reef
            if(((distanceX * distanceX) + (distanceY * distanceY)) > (0.9 * (hypotenuse * hypotenuse)) && ((distanceX * distanceX) + (distanceY * distanceY)) < (1.1 * (hypotenuse * hypotenuse))){
                steerStickX = 0.0;
                isParrallel = true;
            } else if((distanceX * distanceX) + (distanceY * distanceY) > (hypotenuse * hypotenuse)){
                steerStickX = -0.5;
                isParrallel = false;
            } else if((distanceX * distanceX) + (distanceY * distanceY) < (hypotenuse * hypotenuse)){
                steerStickX = 0.5;
                isParrallel = false;
            }
            
            //Drives the robot to the reef, replaces controller input
            if(isParrallel == true){
                driveStickX = controllerX;
                driveStickY = controllerY;
            } else {
                driveStickX = 0;
                driveStickY = 0;
            }   

        } else {
            driveStickY = driveJoy.getRawAxis(1);
            driveStickX = driveJoy.getRawAxis(0);
            steerStickX = steerJoy.getRawAxis(0);

            System.out.println("else branch running");
        }
        }
    }
}