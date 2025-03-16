// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

import frc.robot.commands.AlgaeDefaultCommand;
import frc.robot.commands.CoralDefaultCommand;
import frc.robot.commands.ClimbDefaultCommand;
import frc.robot.constants.TunerConstants;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Algae;
import frc.robot.subsystems.Coral;
import frc.robot.subsystems.Climb;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

public class RobotContainer {

    


    // Declaring subsystems & default commands (This took me way too long to figure out how to do - CL 2/9/25)
    final Algae myAlgae = new Algae();
    AlgaeDefaultCommand algy = new AlgaeDefaultCommand(myAlgae);

    final Climb myClimb = new Climb();
    ClimbDefaultCommand climy = new ClimbDefaultCommand(myClimb);

    final Coral myCoral = new Coral();
    CoralDefaultCommand cory = new CoralDefaultCommand(myCoral);


    private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity


    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

    private final Telemetry logger = new Telemetry(MaxSpeed);

    private final CommandJoystick driveStick = new CommandJoystick(0);
    private final CommandJoystick steerStick = new CommandJoystick(1);

    public final Swerve drivetrain = TunerConstants.createDrivetrain();

    /* Path follower */
    public static SendableChooser<Command> autoChooser;

    public RobotContainer() {

        
        NamedCommands.registerCommand("Elevator Down", myCoral.elevatorDownCommand());
        NamedCommands.registerCommand("Elevator to Source", myCoral.elevatorSourceCommand());
        NamedCommands.registerCommand("Elevator to L1", myCoral.elevatorL1Command());
        NamedCommands.registerCommand("Elevator to L2", myCoral.elevatorL2Command());
        NamedCommands.registerCommand("Elevator to L3", myCoral.elevatorL3Command());
        NamedCommands.registerCommand("Elevator to L4", myCoral.elevatorL4Command());
        NamedCommands.registerCommand("Intake", myCoral.coralIntakeCommand());
        NamedCommands.registerCommand("Outake", myCoral.coralOutakeCommand());
        NamedCommands.registerCommand("Intake Off", myCoral.coralStopIntakeCommand());


        // Loading Autos (I think that's what this does, idk)
      PathPlannerAuto defaultPath = new PathPlannerAuto("Default");
      PathPlannerAuto midStart2Coral = new PathPlannerAuto("Mid Start, 1 Coral");
      PathPlannerAuto leftStart2Coral = new PathPlannerAuto("Left Start, 2 Coral");


        myAlgae.setDefaultCommand(algy);
        myCoral.setDefaultCommand(cory);
        myClimb.setDefaultCommand(climy);

        autoChooser = AutoBuilder.buildAutoChooser("Default");
        SmartDashboard.putData("Auto Mode", autoChooser);
        configureBindings();

        myCoral.resetEncoders();
        myClimb.resetEncoder();
    }

    private void configureBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-driveStick.getY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-driveStick.getX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-steerStick.getX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
            )
        );

        driveStick.button(1).whileTrue(drivetrain.applyRequest(() -> brake));

        steerStick.button(12).whileTrue(drivetrain.applyRequest(() ->
            point.withModuleDirection(new Rotation2d(-driveStick.getY(), -driveStick.getX()))
        ));

        // // Run SysId routines when holding back/start and X/Y.
        // // Note that each routine should be run exactly once in a single log.
        // joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        // joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        // joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        // joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));
        // // - - - - - - - - - - - - - - - - - - - - - - - - - - -  COMMENTED 1/26/25 bc using flight sticks
        // // reset the field-centric heading on left bumper press
        // joystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    public Command getAutonomousCommand() {
        /* Run the path selected from the auto chooser */
        return autoChooser.getSelected();
    }
}