package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FuelSystem;

import static frc.robot.Constants.FuelConstants.*;

public class Launch extends Command {
    private FuelSystem fuelSystem;

    public Launch(FuelSystem fuelSystem) {
        this.fuelSystem = fuelSystem;
        addRequirements(fuelSystem);
    }

    @Override
    public void initialize() {
        fuelSystem.setFeeder(LAUNCH_FEEDER_VOLTAGE);
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        fuelSystem.setFeeder(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}