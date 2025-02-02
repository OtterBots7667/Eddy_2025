package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.MechConstants;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;

public class Algae extends SubsystemBase{
    
    /* - - - - - - - - - Declare Variables & Components Here - - - - - - - - - */
    private static final Joystick buttonBox = MechConstants.buttonBox;

    SparkMax algaePivotMotor = new SparkMax(1, MotorType.kBrushless);
    private double target = 0.0;
    PIDController algaePivotPID = new PIDController(MechConstants.algaePivotP, MechConstants.algaePivotI, MechConstants.algaePivotD);
    double algaePivotF = MechConstants.algaePivotF;
    SparkMax algaeIntakeMotor = new SparkMax(0, MotorType.kBrushless);
    RelativeEncoder algaePivotEncoder = algaeIntakeMotor.getEncoder();

      public void periodic(){
      
        boolean teleopHasStarted = Robot.teleopHasStarted;

        if(teleopHasStarted){ 

                /* - - - - - - - - - Teleop Loop Code Here - - - - - - - - - */

            // Pivot Mechanism
            algaePivotMotor.set(algaePivotPID.calculate(algaePivotEncoder.getPosition(), target) + algaePivotF);

            if (buttonBox.getRawButton(MechConstants.algaePivotUpButtonID) && buttonBox.getRawButton(MechConstants.algaePivotDownButtonID)){
                target = algaePivotEncoder.getPosition();
            } else if (buttonBox.getRawButton(MechConstants.algaePivotUpButtonID)){
                target = MechConstants.algaePivotUpPos;
            } else if (buttonBox.getRawButton(MechConstants.algaePivotDownButtonID)){
                target = MechConstants.algaePivotDownPos;
            }
            
            // This is the motor that will intake the algae

            if ( buttonBox.getRawButton(MechConstants.algaeIntakeButtonID) && buttonBox.getRawButton(MechConstants.algaeOutakeButtonID)){
                algaeIntakeMotor.set(0.0);
            } else if(buttonBox.getRawButton(MechConstants.algaeIntakeButtonID)){
                algaeIntakeMotor.set (MechConstants.algaeIntakeSpeed);
            } else if (buttonBox.getRawButton(MechConstants.algaeOutakeButtonID)){
                algaeIntakeMotor.set(MechConstants.algaeOutakeSpeed); 
            } else {
                algaeIntakeMotor.set(0.0);
            }

        }
    }

}