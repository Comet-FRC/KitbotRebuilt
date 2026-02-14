package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FuelSystem;

import static frc.robot.Constants.FuelConstants.*;

public class RevLauncher extends Command {
    private FuelSystem fuelSystem;

    public RevLauncher(FuelSystem fuelSystem) {
        this.fuelSystem = fuelSystem;
    }

    @Override
    public void initialize() {
        fuelSystem.setIntakeLauncher(LAUNCH_LAUNCHER_VOLTAGE);

    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        fuelSystem.setIntakeLauncher(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}