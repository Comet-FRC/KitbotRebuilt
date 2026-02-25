package frc.robot.commands.fuelCommands;

import static frc.robot.Constants.FuelConstants.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class AdjustLauncherVoltage extends Command {
  private double voltage;

  public AdjustLauncherVoltage(double voltage) {
    this.voltage = voltage;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    SmartDashboard.putNumber("Launch Launcher Voltage", SmartDashboard.getNumber("Launch Launcher Voltage", LAUNCH_LAUNCHER_VOLTAGE) + voltage);
    if(SmartDashboard.getNumber("Launch Launcher Voltage", LAUNCH_LAUNCHER_VOLTAGE) > 12) {
      SmartDashboard.putNumber("Launch Launcher Voltage", 12);
    } else if(SmartDashboard.getNumber("Launch Launcher Voltage", LAUNCH_LAUNCHER_VOLTAGE) < 6) {
      SmartDashboard.putNumber("Launch Launcher Voltage", 6);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}