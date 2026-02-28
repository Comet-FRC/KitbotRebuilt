package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

        SmartDashboard.putNumber("Intake Intake Voltage", INTAKE_INTAKE_VOLTAGE);
        SmartDashboard.putNumber("Intake Feeder Voltage", INTAKE_FEEDER_VOLTAGE);
        SmartDashboard.putNumber("Launch Launcher Voltage", LAUNCH_LAUNCHER_VOLTAGE);
        SmartDashboard.putNumber("Rev Launcher Timeout", REV_LAUNCHER_TIMEOUT);
        SmartDashboard.putNumber("Eject Intake Voltage", EJECT_INTAKE_VOLTAGE);
        SmartDashboard.putNumber("Eject Feeder Voltage", EJECT_FEEDER_VOLTAGE);
    }

    public void setIntakeLauncher(double speed) {
        intakeLauncher.set(TalonSRXControlMode.PercentOutput, speed / VOLTAGE_COMPENSATION);
    }

    public void setFeeder(double speed) {
        feeder.set(TalonSRXControlMode.PercentOutput, speed / VOLTAGE_COMPENSATION);
    }

    public void stop() {
        setIntakeLauncher(0);
        setFeeder(0);
    }

    public void adjustLauncherVoltage(double voltage) {
        SmartDashboard.putNumber("Launch Launcher Voltage", SmartDashboard.getNumber("Launch Launcher Voltage", LAUNCH_LAUNCHER_VOLTAGE) + voltage);
        
        if(SmartDashboard.getNumber("Launch Launcher Voltage", LAUNCH_LAUNCHER_VOLTAGE) > 12) {
            SmartDashboard.putNumber("Launch Launcher Voltage", 12);
        } else if(SmartDashboard.getNumber("Launch Launcher Voltage", LAUNCH_LAUNCHER_VOLTAGE) < 6) {
            SmartDashboard.putNumber("Launch Launcher Voltage", 6);
        }
    }
}