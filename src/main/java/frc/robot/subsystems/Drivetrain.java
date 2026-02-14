package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DrivetrainConstants.*;

public class Drivetrain extends SubsystemBase {
    private SparkMax frontLeft, frontRight, backLeft, backRight;
    private DifferentialDrive drive;

    public Drivetrain() {
        frontLeft = new SparkMax(FRONT_LEFT_ID, MotorType.kBrushless);
        frontRight = new SparkMax(FRONT_RIGHT_ID, MotorType.kBrushless);
        backLeft = new SparkMax(BACK_LEFT_ID, MotorType.kBrushless);
        backRight = new SparkMax(BACK_RIGHT_ID, MotorType.kBrushless);

        frontLeft.setCANTimeout(TIMEOUT);
        backLeft.setCANTimeout(TIMEOUT);
        frontRight.setCANTimeout(TIMEOUT);
        backRight.setCANTimeout(TIMEOUT);

        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(CURRENT_LIMIT);
        config.voltageCompensation(VOLTAGE_COMPENSATION);
        config.idleMode(IdleMode.kBrake);
        frontLeft.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        config.follow(frontLeft);
        backLeft.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        config.disableFollowerMode();
        config.inverted(true);
        frontRight.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        config.follow(frontRight);
        backRight.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
       

        drive = new DifferentialDrive(frontLeft, frontRight);
    }

    public void drive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }
}