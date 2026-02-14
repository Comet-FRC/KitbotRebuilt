package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FuelSystem;

import static frc.robot.Constants.FuelConstants.*;

public class Eject extends Command {
    private FuelSystem fuelSystem;

    public Eject(FuelSystem fuelSystem) {
        this.fuelSystem = fuelSystem;
        addRequirements(fuelSystem);
    }

    @Override
    public void initialize() {
        fuelSystem.setFeeder(EJECT_FEEDER_VOLTAGE);
        fuelSystem.setIntakeLauncher(EJECT_INTAKE_VOLTAGE);
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        fuelSystem.setFeeder(0);
        fuelSystem.setIntakeLauncher(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}