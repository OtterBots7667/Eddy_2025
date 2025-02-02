package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.constants.MechConstants;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;

public class Coral extends SubsystemBase{
    
    /* - - - - - - - - - Declare Variables & Components Here - - - - - - - - - */
    private static final Joystick buttonBox = MechConstants.buttonBox;

    final PIDController pivotPID = new PIDController(MechConstants.coralPivotP, MechConstants.coralPivotI, MechConstants.coralPivotD);
    final SparkMax pivotMotor = new SparkMax(MechConstants.coralPivotMotorID, MotorType.kBrushless);
    final SparkMax intakeMotor = new SparkMax(MechConstants.coralIntakeMotorID, MotorType.kBrushless);
    final RelativeEncoder pivotEncoder = pivotMotor.getEncoder();

    final PIDController elevatorPID = new PIDController(MechConstants.elevatorP, MechConstants.elevatorI, MechConstants.elevatorD);
    final TalonFX elevatorMotor = new TalonFX(MechConstants.elevatorMotorID);

    private double target = 0.0;


    public void periodic(){
        
        final boolean teleopHasStarted = Robot.teleopHasStarted;

        if(teleopHasStarted){

            /* - - - - - - - - - - - - - Teleop Loop Code Here - - - - - - - - - - - - - */
            
            pivotMotor.set(pivotPID.calculate(pivotEncoder.getPosition(), target) + MechConstants.coralPivotF);

            elevatorMotor.set(elevatorPID.calculate(elevatorMotor.getPosition().getValueAsDouble(), target));


            // Pivot
            if(buttonBox.getRawButton(MechConstants.coralPivotUpButtonID)){
                target = MechConstants.coralPivotUpPos;
            } else 
            if(buttonBox.getRawButton(MechConstants.coralPivotDownButtonID)){
                target = MechConstants.coralPivotDownPos;
            }


            // Intake & Outake
            if(buttonBox.getRawButton(MechConstants.coralIntakeButtonID)){
                intakeMotor.set(MechConstants.coralIntakeSpeed);
            } else
            if(buttonBox.getRawButton(MechConstants.coralOutakeButtonID)){
                intakeMotor.set(MechConstants.coralOutakeSpeed);
            } else {
                intakeMotor.set(0.0);
            }


            // Elevator
            if(buttonBox.getRawButton(MechConstants.elevatorDownButtonID)){
                target = MechConstants.elevatorDownPos;
            } else
            if(buttonBox.getRawButton(MechConstants.elevatorSourceButtonID)){
                target = MechConstants.elevatorSourcePos;
            } else
            if(buttonBox.getRawButton(MechConstants.elevatorL1ButtonID)){
                target = MechConstants.elevatorL1Pos;
            } else
            if(buttonBox.getRawButton(MechConstants.elevatorL2ButtonID)){
                target = MechConstants.elevatorL2Pos;
            } else
            if(buttonBox.getRawButton(MechConstants.elevatorL3ButtonID)){
                target = MechConstants.elevatorL3Pos;
            } else
            if(buttonBox.getRawButton(MechConstants.elevatorL4ButtonID)){
                target = MechConstants.elevatorL4Pos;
            }
                


        }
    }

}