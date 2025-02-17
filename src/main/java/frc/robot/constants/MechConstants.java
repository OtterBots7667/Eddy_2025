package frc.robot.constants;

import edu.wpi.first.wpilibj.Joystick;

public class MechConstants {
    
    /* - - - - - - - - - Joystick IDs - - - - - - - - - */
    public static final Joystick buttonBox = new Joystick(2);
    public static final Joystick driveStick = new Joystick(0);
    

    /* - - - - - - - - - Button IDs - - - - - - - - - */
    public static final int coralIntakeButtonID = 1;
    public static final int coralOutakeButtonID = 2;

    public static final int elevatorDownButtonID = 3;
    public static final int elevatorSourceButtonID = 4;
    public static final int elevatorL1ButtonID = 5;
    public static final int elevatorL2ButtonID = 6;
    public static final int elevatorL3ButtonID = 7;
    public static final int elevatorL4ButtonID = 8;

    public static final int algaeIntakeButtonID = 9;
    public static final int algaeOutakeButtonID = 10;
    public static final int algaeRetractButtonID = 11;

    public static final int overrideJoystick_X_ID = 0;
    public static final int overrideJoystick_Y_ID = 1;

    public static final int climbButtonID = 12;

    public static final int limelightRightButtonID = 1;
    public static final int limelightLeftButtonID = 2;

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

    public static final double algaeIntakeSpeed = 0.3;
    public static final double algaeOutakeSpeed = -0.3;

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
    public static final double climbDownPos = 40960.0;

    public static final double coralPivotInPos = 0.0;
    public static final double coralPivotOutPos = 1.0;
    public static final double coralPivotDownPos = 2.0;
    public static final int coralPivotTolerance = 50;   // Must be positive

    public static final double algaeRetractPos = 0.0;
    public static final double algaeExtendPos = 1.0;

    public static final double elevatorDownPos = 0.0;
    public static final double elevatorSourcePos = 1.0;
    public static final double elevatorL1Pos = 2.0;
    public static final double elevatorL2Pos = 3.0;
    public static final double elevatorL3Pos = 4.0;
    public static final double elevatorL4Pos = 5.0;
    public static final int elevatorTolerance = 250;   // Must be positive

    /* - - - - - - - - - Limelight Constants - - - - - - - - - */
    

}