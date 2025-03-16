// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  public static boolean teleopHasStarted = false;

  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  public Robot() {
    m_robotContainer = new RobotContainer();
  }

  int chooserClock = 0; // Clock for printing out the chosen autonomous routine
  String oldAutoChosen = "waiting...";
  String newAutoChosen = "waiting...";

  @Override
  public void robotPeriodic() {

    CommandScheduler.getInstance().run(); 


    newAutoChosen = RobotContainer.autoChooser.getSelected().getName();

    if (!newAutoChosen.equals(oldAutoChosen) || chooserClock % 1500 == 0) {
      System.out.println("Auto Chosen: - " + newAutoChosen);    // Print out chosen auto if auto changes
    }                                                           // or every 30 seconds

    oldAutoChosen = RobotContainer.autoChooser.getSelected().getName();

    chooserClock++;

  }

  @Override
  public void disabledInit() {
  }

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