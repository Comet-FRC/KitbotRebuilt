package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class Drive extends Command {
    private Drivetrain drivetrain;
    private DoubleSupplier speed, rotation;
    
    public Drive(Drivetrain drivetrain, DoubleSupplier speed, DoubleSupplier rotation) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.rotation = rotation;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        drivetrain.drive(speed.getAsDouble(), rotation.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}