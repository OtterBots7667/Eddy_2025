package frc.robot.constants;

import edu.wpi.first.wpilibj.Joystick;

public class MechConstants {
    
    /* - - - - - - - - - Joystick IDs - - - - - - - - - */
    public static final Joystick buttonBox = new Joystick(2);

    /* - - - - - - - - - Button IDs - - - - - - - - - */
    public static final int coralIntakeButtonID = 1;
    public static final int coralOutakeButtonID = 2;
    public static final int coralPivotUpButtonID = 3;
    public static final int coralPivotDownButtonID = 4;

    public static final int algaeIntakeButtonID = 5;
    public static final int algaeOutakeButtonID = 6;

    public static final int elevatorDownButtonID = 7;
    public static final int elevatorSourceButtonID = 8;
    public static final int elevatorL1ButtonID = 9;
    public static final int elevatorL2ButtonID = 10;
    public static final int elevatorL3ButtonID = 11;
    public static final int elevatorL4ButtonID = 12;

    /* - - - - - - - - - Motor IDs - - - - - - - - - */
    public static final int elevatorMotorID = 8;
    public static final int coralIntakeMotorID = 9;
    public static final int coralPivotMotorID = 10;

    public static final int algaeIntakeMotorID = 11;
    public static final int algaePivotMotorID = 12;

    public static final int climbMotorID = 13;

    /* - - - - - - - - - Motor Speeds - - - - - - - - - */
    public static final double coralIntakeSpeed = 0.3;
    public static final double coralOutakeSpeed = -0.3;

    public static final double AlgaeIntakeSpeed = 0.3;
    public static final double AlgaeOutakeSpeed = -0.3;

    public static final double climbSpeed = 1.0;

    /* - - - - - - - - - Motor PID Constants - - - - - - - - - */
    public static final double elevatorP = 0.01;
    public static final double elevatorI = 0.0;
    public static final double elevatorD = 0.0001;
    public static final double elevatorF = 0.05;

    public static final double coralPivotP = 0.01;
    public static final double coralPivotI = 0.0;
    public static final double coralPivotD = 0.0001;
    public static final double coralPivotF = 0.05;

    public static final double algaePivotP = 0.01;
    public static final double algaePivotI = 0.0;
    public static final double algaePivotD = 0.0001;
    public static final double algaePivotF = 0.05;

    /* - - - - - - - - - Motor Position Constants - - - - - - - - - */
    public static final double climbEndPos = 40960.0;

    public static final double coralPivotUpPos = 0.0;
    public static final double coralPivotDownPos = 0.0;

    public static final double algaePivotUpPos = 0.0;
    public static final double algaePivotDownPos = 0.0;

    public static final double elevatorDownPos = 0.0;
    public static final double elevatorSourcePos = 1.0;
    public static final double elevatorL1Pos = 2.0;
    public static final double elevatorL2Pos = 3.0;
    public static final double elevatorL3Pos = 4.0;
    public static final double elevatorL4Pos = 5.0;
}