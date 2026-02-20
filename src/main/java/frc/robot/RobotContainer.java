package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.driveCommands.Drive;
import frc.robot.commands.fuelCommands.AdjustLauncherVoltage;
import frc.robot.commands.fuelCommands.Eject;
import frc.robot.commands.fuelCommands.Intake;
import frc.robot.commands.fuelCommands.LaunchSequence;
import frc.robot.controllers.CometXboxController;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.FuelSystem;

public class RobotContainer {
  private Drivetrain drivetrain;
  private FuelSystem fuelSystem;
  private CometXboxController controller;

  public RobotContainer() {
    drivetrain = new Drivetrain();
    fuelSystem = new FuelSystem();
    controller = new CometXboxController(0);

    configureBindings();
  }

  private void configureBindings() {
    drivetrain.setDefaultCommand(new Drive(drivetrain,
        () -> -controller.getLeftY(),
        () -> -controller.getRightX()));

    fuelSystem.setDefaultCommand(fuelSystem.run(() -> fuelSystem.stop()));

    controller.b().whileTrue(new Intake(fuelSystem));
    controller.y().whileTrue(new LaunchSequence(fuelSystem));
    controller.a().whileTrue(new Eject(fuelSystem));
    controller.x().whileTrue(new AdjustLauncherVoltage(12));
    controller.up().whileTrue(new AdjustLauncherVoltage(0.05));
    controller.down().whileTrue(new AdjustLauncherVoltage(-0.05));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}