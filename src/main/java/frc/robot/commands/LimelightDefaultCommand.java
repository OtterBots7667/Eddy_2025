package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Limelight;

public class LimelightDefaultCommand extends Command{
    public LimelightDefaultCommand(Limelight limelight){

        addRequirements(limelight);

    }
}
