package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.FuelSystem;

import static frc.robot.Constants.FuelConstants.*;

public class LaunchSequence extends SequentialCommandGroup {
  public LaunchSequence(FuelSystem fuelSystem) {
    addCommands(
        new RevLauncher(fuelSystem).withTimeout(SmartDashboard.getNumber("Rev Launcher Timeout", REV_LAUNCHER_TIMEOUT)),
        new Launch(fuelSystem));
  }
}