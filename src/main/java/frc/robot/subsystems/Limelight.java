package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.MechConstants;

import com.ctre.phoenix6.swerve.SwerveRequest;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase{


// static int eieio = 0;
//     public static final Command limelightDefaultCommand() {
//         return Commands.run(() -> eieio++);
//     }

    /* - - - - - - - - - Declare Variables & Components Here - - - - - - - - - */
    private static final Joystick buttonBox = MechConstants.buttonBoxMisc;
    public boolean teleopHasStarted = Robot.teleopHasStarted;

    double KpAim = -1.0;
    double KpDistance = -1.0;
    double min_aim_command = 0.05;

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    
    double tx = table.getEntry("tx").getDouble(0.0);
    double ty = table.getEntry("ty").getDouble(0.0);
    NetworkTableEntry ta = table.getEntry("ta");

    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric();

    @ Override
    public void periodic(){
        teleopHasStarted = Robot.teleopHasStarted;
        if(teleopHasStarted){

        /* - - - - - - - - - Teleop Loop Code Here - - - - - - - - - */

        //read values periodically
        double x = tx;
        double y = ty;
        double area = ta.getDouble(0.0);

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);

        if(buttonBox.getRawButton(MechConstants.limelightRightButtonID)){
            //Changes pipline to pipeline 0
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getDouble(0);
            //Definitions for adjustments
            double tx = table.getEntry("tx").getDouble(0.0);
            double ty = table.getEntry("ty").getDouble(0.0);
            double heading_error = -tx;
            double distance_error = -ty;
            double steering_adjust = 0.0;
            double left_command = 0.0;
            double right_command = 0.0;
            
            //Caculating adjustments
            if (tx > 1.0){
                steering_adjust = KpAim*heading_error - min_aim_command;
            }
            else if (tx < -1.0){
                steering_adjust = KpAim*heading_error + min_aim_command;
            }

            double distance_adjust = KpDistance * distance_error;

            left_command += steering_adjust + distance_adjust;
            right_command -= steering_adjust + distance_adjust;

            //Moves chasis based on adjustments
            point.withModuleDirection(new Rotation2d(steering_adjust));

            if(ty < 20 && ty > 0){
                drive.withVelocityX(0.5);
            }

        } else if(buttonBox.getRawButton(MechConstants.limelightLeftButtonID)){
            //Changes pipline to pipeline 1
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getDouble(1);

            //Definitions for adjustments
            double tx = table.getEntry("tx").getDouble(0.0);
            double ty = table.getEntry("ty").getDouble(0.0);
            double heading_error = -tx;
            double distance_error = -ty;
            double steering_adjust = 0.0;
            double left_command = 0.0;
            double right_command = 0.0;
            
            //Caculating adjustments
            if (tx > 1.0){
                steering_adjust = KpAim*heading_error - min_aim_command;
            }
            else if (tx < -1.0){
                steering_adjust = KpAim*heading_error + min_aim_command;
            }

            double distance_adjust = KpDistance * distance_error;

            left_command += steering_adjust + distance_adjust;
            right_command -= steering_adjust + distance_adjust;

            //Moves chasis based on adjustments
            point.withModuleDirection(new Rotation2d(steering_adjust));

            if(ty < 20 && ty > 0){
                drive.withVelocityX(0.5);
            }
        }
        }
    }
}