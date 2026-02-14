package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.Drive;
import frc.robot.commands.Eject;
import frc.robot.commands.Intake;
import frc.robot.commands.Launch;
import frc.robot.commands.RevLauncher;
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

    controller.b().whileTrue(new Intake(fuelSystem));
    controller.y().whileTrue(Commands.parallel(new Launch(fuelSystem), new RevLauncher(fuelSystem)));
    controller.x().whileTrue(new RevLauncher(fuelSystem));
    controller.a().whileTrue(new Eject(fuelSystem));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}