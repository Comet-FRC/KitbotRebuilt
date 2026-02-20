package frc.robot.commands.fuelCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FuelSystem;

import static frc.robot.Constants.FuelConstants.*;

public class Intake extends Command {
    private FuelSystem fuelSystem;

    public Intake(FuelSystem fuelSystem) {
        this.fuelSystem = fuelSystem;
        addRequirements(fuelSystem);
    }

    @Override
    public void initialize() {
        fuelSystem.setFeeder(SmartDashboard.getNumber("Intake Feeder Voltage", INTAKE_FEEDER_VOLTAGE));
        fuelSystem.setIntakeLauncher(SmartDashboard.getNumber("Intake Intake Voltage", INTAKE_INTAKE_VOLTAGE));
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