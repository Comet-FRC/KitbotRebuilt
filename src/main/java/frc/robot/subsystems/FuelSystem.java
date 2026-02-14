package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.FuelConstants.*;

public class FuelSystem extends SubsystemBase {
    private TalonSRX feeder, intakeLauncher;

    public FuelSystem() {
        feeder = new TalonSRX(FEEDER_ID);
        intakeLauncher = new TalonSRX(INTAKE_LAUNCHER_ID);

        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.peakCurrentLimit = PEAK_CURRENT_LIMIT;
        config.peakCurrentDuration = PEAK_CURRENT_DURATION;
        config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;

        feeder.configAllSettings(config);
        feeder.configVoltageCompSaturation(VOLTAGE_COMPENSATION);
        feeder.enableVoltageCompensation(true);
        feeder.setNeutralMode(NeutralMode.Brake);
        feeder.setInverted(true);

        intakeLauncher.configAllSettings(config);
        intakeLauncher.configVoltageCompSaturation(VOLTAGE_COMPENSATION);
        intakeLauncher.enableVoltageCompensation(true);
        intakeLauncher.setNeutralMode(NeutralMode.Brake);
    }

    public void setIntakeLauncher(double speed) {
        intakeLauncher.set(TalonSRXControlMode.PercentOutput, speed / VOLTAGE_COMPENSATION);
    }

    public void setFeeder(double speed) {
        feeder.set(TalonSRXControlMode.PercentOutput, speed / VOLTAGE_COMPENSATION);
    }
}