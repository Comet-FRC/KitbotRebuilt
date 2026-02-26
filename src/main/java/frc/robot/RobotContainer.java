package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;
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

    NamedCommands.registerCommand("Launch", new LaunchSequence(fuelSystem));

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
    controller.x().whileTrue(drivetrain.run(() -> drivetrain.resetGyro()));

    controller.up().whileTrue(new AdjustLauncherVoltage(0.05));
    controller.down().whileTrue(new AdjustLauncherVoltage(-0.05));
  }

  public Command getAutonomousCommand() {
    return new PathPlannerAuto("Test Auto");
  }
}