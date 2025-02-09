// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import com.pathplanner.lib.commands.PathPlannerAuto;

public class Robot extends TimedRobot {
  public static boolean teleopHasStarted = false;

  // Loading Autos (I think that's what this does, idk)
  PathPlannerAuto defaultPath = new PathPlannerAuto("Default");
  PathPlannerAuto pos1FourCoral = new PathPlannerAuto("Start Pos 1, 4 Coral");
  PathPlannerAuto pos2OneCoral = new PathPlannerAuto("Start Pos 2, 1 Coral");
  PathPlannerAuto pos2FourCoral = new PathPlannerAuto("Start Pos 2, 4 Coral");
  PathPlannerAuto pos3FourCoral = new PathPlannerAuto("Start Pos 3, 4 Coral");
  PathPlannerAuto pos4TwoCoral = new PathPlannerAuto("Start Pos 4, 2 Coral");

  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  public Robot() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run(); 
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    teleopHasStarted = true;
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}

  @Override
  public void simulationPeriodic() {}
}