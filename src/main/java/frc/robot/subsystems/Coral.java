package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
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
    
static int eieio = 0;
    public static final Command coralDefaultCommand() {
        return Commands.run(() -> eieio++);
    }

    /* - - - - - - - - - Declare Variables & Components Here - - - - - - - - - */
    private static final Joystick buttonBox = MechConstants.buttonBox;

    final PIDController pivotPID = new PIDController(MechConstants.coralPivotP, MechConstants.coralPivotI, MechConstants.coralPivotD);
    final SparkMax pivotMotor = new SparkMax(MechConstants.coralPivotMotorID, MotorType.kBrushless);
    final SparkMax intakeMotor = new SparkMax(MechConstants.coralIntakeMotorID, MotorType.kBrushless);
    final RelativeEncoder pivotEncoder = pivotMotor.getEncoder();

    final PIDController elevatorPID = new PIDController(MechConstants.elevatorP, MechConstants.elevatorI, MechConstants.elevatorD);
    final TalonFX elevatorMotor = new TalonFX(MechConstants.elevatorMotorID);

    private double pivotTarget = 0.0;
    private double elevatorTarget = 0.0;
    private boolean pivotCutPower = true;
    private boolean elevatorCutPower = true;

    @Override
    public void periodic(){
        
        final boolean teleopHasStarted = Robot.teleopHasStarted;

        if(teleopHasStarted){

            /* - - - - - - - - - - - - - Teleop Loop Code Here - - - - - - - - - - - - - */


            // Intake & Outake
            if(buttonBox.getRawButton(MechConstants.coralIntakeButtonID)){
                intakeMotor.set(MechConstants.coralIntakeSpeed);
            } else
            if(buttonBox.getRawButton(MechConstants.coralOutakeButtonID)){
                intakeMotor.set(MechConstants.coralOutakeSpeed);
            } else {
                intakeMotor.set(0.0);
            }

            
            
            // Pivot PID
            if(!pivotCutPower){
                pivotMotor.set(pivotPID.calculate(pivotEncoder.getPosition(), pivotTarget) + MechConstants.coralPivotF);
            }
    
            if(pivotTarget == MechConstants.coralPivotDownPos){
                if(pivotEncoder.getPosition() < MechConstants.coralPivotDownPos + MechConstants.coralPivotTolerance &&
                    pivotEncoder.getPosition() > MechConstants.coralPivotDownPos - MechConstants.coralPivotTolerance){
                    pivotCutPower = true;
                }
            }
    
            if(pivotCutPower){
                pivotMotor.set(0.0);
            }

            // Elevator PID

            if(!elevatorCutPower){
                elevatorMotor.set(elevatorPID.calculate(elevatorMotor.getPosition().getValueAsDouble(), elevatorTarget));
            }
    
                if(elevatorTarget == MechConstants.elevatorDownPos){
                    if(elevatorMotor.getPosition().getValueAsDouble() < MechConstants.elevatorDownPos + MechConstants.elevatorTolerance &&
                    elevatorMotor.getPosition().getValueAsDouble() > MechConstants.elevatorDownPos - MechConstants.elevatorTolerance){
                        pivotCutPower = true;
                    }
                }
    
                if(elevatorCutPower){
                    elevatorMotor.set(0.0);
                }


            // Elevator and Pivot position targeter
            if(buttonBox.getRawButton(MechConstants.elevatorDownButtonID)){
                elevatorTarget = MechConstants.elevatorDownPos;
                pivotTarget = MechConstants.coralPivotDownPos;
            } else
            if(buttonBox.getRawButton(MechConstants.elevatorSourceButtonID)){
                elevatorTarget = MechConstants.elevatorSourcePos;
                elevatorCutPower = false;
                pivotTarget = MechConstants.coralPivotInPos;
                pivotCutPower = false;
            } else
            if(buttonBox.getRawButton(MechConstants.elevatorL1ButtonID)){
                elevatorTarget = MechConstants.elevatorL1Pos;
                elevatorCutPower = false;
                pivotTarget = MechConstants.coralPivotOutPos;
                pivotCutPower = false;
            } else
            if(buttonBox.getRawButton(MechConstants.elevatorL2ButtonID)){
                elevatorTarget = MechConstants.elevatorL2Pos;
                elevatorCutPower = false;
                pivotTarget = MechConstants.coralPivotOutPos;
                pivotCutPower = false;
            } else
            if(buttonBox.getRawButton(MechConstants.elevatorL3ButtonID)){
                elevatorTarget = MechConstants.elevatorL3Pos;
                elevatorCutPower = false;
                pivotTarget = MechConstants.coralPivotOutPos;
                pivotCutPower = false;
            } else
            if(buttonBox.getRawButton(MechConstants.elevatorL4ButtonID)){
                elevatorTarget = MechConstants.elevatorL4Pos;
                elevatorCutPower = false;
                pivotTarget = MechConstants.coralPivotOutPos;
                pivotCutPower = false;
            }

            // Pivot manual override
            if(buttonBox.getRawAxis(MechConstants.overrideJoystick_X_ID) > 0.5){
                pivotTarget++;
                pivotCutPower = false;
            } else if(buttonBox.getRawAxis(MechConstants.overrideJoystick_X_ID) < -0.5){
                pivotTarget--;
                pivotCutPower = false;
            }

            // Elevator manual override
            if(buttonBox.getRawAxis(MechConstants.overrideJoystick_Y_ID) < -0.5){
                elevatorTarget++;
                elevatorCutPower = false;
            } else if(buttonBox.getRawAxis(MechConstants.overrideJoystick_Y_ID) > 0.5){
                elevatorTarget--;
                elevatorCutPower = false;
            }

        }
    }

}