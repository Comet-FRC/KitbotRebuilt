package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.Drive;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private Drivetrain drivetrain;
  private XboxController controller;

  public RobotContainer() {
    drivetrain = new Drivetrain();
    controller = new XboxController(0);

    configureBindings();
  }

  private void configureBindings() {
    drivetrain.setDefaultCommand(new Drive(drivetrain, controller.getLeftY(), controller.getRightX()));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}