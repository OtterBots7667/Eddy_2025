package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.MechConstants;

public class Algae extends SubsystemBase{
    
    /* - - - - - - - - - Declare Variables & Components Here - - - - - - - - - */
    private static final Joystick buttonBox = MechConstants.buttonBox;

    public void periodic(){
        
        boolean teleopHasStarted = Robot.teleopHasStarted;

        if(teleopHasStarted){

            /* - - - - - - - - - Teleop Loop Code Here - - - - - - - - - */







        }
    }

}