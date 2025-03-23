package frc.robot.constants;

import edu.wpi.first.wpilibj.Joystick;

public class MechConstants {
    
    /* - - - - - - - - - Joystick IDs - - - - - - - - - */
    public static final Joystick buttonBox = new Joystick(2);
    public static final Joystick driveStick = new Joystick(0);
    

    /* - - - - - - - - - Button IDs - - - - - - - - - */
    public static final int coralIntakeButtonID = 8;
    public static final int slowCoralIntakeButtonID = 10;
    public static final int coralOutakeButtonID = 7;
    public static final int coralPivotResetButtonID = 11;

    public static final int elevatorDownButtonID = 6;
    public static final int elevatorSourceButtonID = 5;
    public static final int elevatorL1ButtonID = 2;
    public static final int elevatorL2ButtonID = 2;
    public static final int elevatorL3ButtonID = 3;
    public static final int elevatorL4ButtonID = 4;

    public static final int algaeIntakeButtonID = 10;
    public static final int algaeOutakeButtonID = 9;
    public static final int algaeRetractButtonID = 11;

    public static final int overrideJoystick_X_ID = 1;
    public static final int overrideJoystick_Y_ID = 0;

    public static final int climbButtonID = 99;

    public static final int limelightRightButtonID = 4;
    public static final int limelightLeftButtonID = 3;

    /* - - - - - - - - - Motor IDs - - - - - - - - - */
    public static final int elevatorMotorID = 13;
    public static final int coralIntakeMotorID = 16;
    public static final int coralPivotMotorID = 15;

    public static final int algaeIntakeMotorID = 30;
    public static final int algaePivotMotorID = 31;

    public static final int climbMotorID = 14;

    /* - - - - - - - - - Motor Speeds - - - - - - - - - */
    public static final double coralIntakeSpeed = 1.0;
    public static final double slowCoralIntakeSpeed = 0.35;
    public static final double coralOutakeSpeed = -1.0;

    public static final double algaeIntakeSpeed = 0.3;
    public static final double algaeOutakeSpeed = -0.3;

    public static final double climbSpeed = -0.2;

    /* - - - - - - - - - Motor PID Constants - - - - - - - - - */
    public static final double elevatorP = 0.0004;
    public static final double elevatorI = 0.0;
    public static final double elevatorD = 0.0;
    public static final double elevatorF = -0.03;

    public static final double coralPivotP = 0.00003;
    public static final double coralPivotI = 0.0;
    public static final double coralPivotD = 0.0;
    public static final double coralPivotF = -0.015;

    public static final double algaePivotP = 0.0;
    public static final double algaePivotI = 0.0;
    public static final double algaePivotD = 0.0;
    public static final double algaePivotF = 0.0;

    /* - - - - - - - - - Motor Position Constants - - - - - - - - - */
    public static final double climbDownPos = -35;

    public static final double coralPivotInPos = -18000;
    public static final double coralPivotOutPos = -8500;
    public static final double coralPivotL4Pos = -10250;
    public static final double coralPivotDownPos = -2500.0;
    public static final double coralPivotResetPos = -19500;
    public static final double coralPivotManualMargin = 200;

    public static final double algaeRetractPos = 0.0;
    public static final double algaeExtendPos = 1.0;

    public static final double elevatorDownPos = -300;
    public static final double elevatorSourcePos = -350;
    public static final double elevatorL1Pos = -1700;
    public static final double elevatorL2Pos = -1700;
    public static final double elevatorL3Pos = -4600;
    public static final double elevatorL4Pos = -7875;
    public static final double elevatorManualMargin = 45;

}