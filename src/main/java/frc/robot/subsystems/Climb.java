package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.MechConstants;
import com.ctre.phoenix6.hardware.TalonFX;


public class Climb extends SubsystemBase{
    
    /* - - - - - - - - - Declare Variables & Components Here - - - - - - - - - */
    private static final Joystick buttonBox = MechConstants.buttonBox;
    boolean climbyTime = false;
    boolean teleopHasStarted = Robot.teleopHasStarted;
    TalonFX climbMotor = new TalonFX(MechConstants.climbMotorID);

    public void periodic(){
        
        boolean teleopHasStarted = Robot.teleopHasStarted;

        if(teleopHasStarted){

            /* - - - - - - - - - Teleop Loop Code Here - - - - - - - - - */

            if(buttonBox.getRawButton(MechConstants.climbButtonID))
                climbyTime = true;
            if(climbyTime && (climbMotor.getPosition().getValueAsDouble() < MechConstants.climbDownPos)){
                climbMotor.set(MechConstants.climbSpeed);
            } else {
                climbMotor.set(0.0);
            }

        }
    }

}