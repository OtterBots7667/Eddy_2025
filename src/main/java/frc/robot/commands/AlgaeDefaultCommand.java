package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Algae;

public class AlgaeDefaultCommand extends Command{
    public AlgaeDefaultCommand(Algae algae){

        addRequirements(algae);

    }
}
