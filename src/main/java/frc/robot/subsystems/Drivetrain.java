package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkRelativeEncoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPLTVController;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DrivetrainConstants.*;

public class Drivetrain extends SubsystemBase {
    private SparkMax frontLeft, frontRight, backLeft, backRight;
    private SparkRelativeEncoder leftEncoder, rightEncoder;
    private Pigeon2 gyro;

    private DifferentialDrive drive;
    private DifferentialDriveKinematics kinematics;
    private DifferentialDrivePoseEstimator poseEstimator;

    public Drivetrain() {
        frontLeft = new SparkMax(FRONT_LEFT_ID, MotorType.kBrushless);
        frontRight = new SparkMax(FRONT_RIGHT_ID, MotorType.kBrushless);
        backLeft = new SparkMax(BACK_LEFT_ID, MotorType.kBrushless);
        backRight = new SparkMax(BACK_RIGHT_ID, MotorType.kBrushless);

        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(CURRENT_LIMIT);
        config.voltageCompensation(VOLTAGE_COMPENSATION);
        config.idleMode(IdleMode.kBrake);
        config.encoder.positionConversionFactor(ENCODER_POSITION_FACTOR);
        config.encoder.velocityConversionFactor(ENCODER_VELOCITY_FACTOR);
        frontLeft.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        config.follow(frontLeft);
        backLeft.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        config.disableFollowerMode();
        config.inverted(true);
        frontRight.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        config.follow(frontRight);
        backRight.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        leftEncoder = (SparkRelativeEncoder)frontLeft.getEncoder();
        rightEncoder = (SparkRelativeEncoder)frontRight.getEncoder();

        gyro = new Pigeon2(GYRO_ID);

        drive = new DifferentialDrive(frontLeft, frontRight);

        kinematics = new DifferentialDriveKinematics(TRACK_WIDTH);
        poseEstimator = new DifferentialDrivePoseEstimator(
            kinematics, 
            gyro.getRotation2d(), 
            leftEncoder.getPosition(), 
            rightEncoder.getPosition(), 
            new Pose2d());

        configureAutoBuilder();
    }

    public void configureAutoBuilder() {
        RobotConfig config;
        try {
            config = RobotConfig.fromGUISettings();

            AutoBuilder.configure(
            this::getPose,
            this::resetPose,
            this::getRobotRelativeSpeeds,
            (speeds, feedforwards) -> driveRobotRelative(speeds),
            new PPLTVController(0.2, MAX_VELOCITY),
            config,
            () -> {var alliance = DriverStation.getAlliance();
              if (alliance.isPresent()) {
                return alliance.get() == DriverStation.Alliance.Red;
              }
              return false;
            },
            this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }
 
    @Override
    public void periodic() {
        poseEstimator.update(
            gyro.getRotation2d(),
            leftEncoder.getPosition(),
            rightEncoder.getPosition());

        System.out.println("Gyro: " + gyro.getRotation2d());
    }

    public Pose2d getPose() {
        return poseEstimator.getEstimatedPosition();
    }

    public void resetPose(Pose2d pose) {
        poseEstimator.resetPosition(
            gyro.getRotation2d(), 
            leftEncoder.getPosition(), 
            rightEncoder.getPosition(), 
            pose);
    }

    public ChassisSpeeds getRobotRelativeSpeeds() {
        return kinematics.toChassisSpeeds(new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity()));
    }

    public void driveRobotRelative(ChassisSpeeds speeds) {
        var wheelSpeeds = kinematics.toWheelSpeeds(speeds);
        wheelSpeeds.desaturate(MAX_VELOCITY);
        frontLeft.set(wheelSpeeds.leftMetersPerSecond / MAX_VELOCITY);
        frontRight.set(wheelSpeeds.rightMetersPerSecond / MAX_VELOCITY);
    }

    public void resetGyro() {
        gyro.reset();
    }
}