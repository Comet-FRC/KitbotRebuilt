package frc.robot;

public class Constants {
        public class DrivetrainConstants {
                public static final int FRONT_LEFT_ID = 6;
                public static final int FRONT_RIGHT_ID = 5;
                public static final int BACK_LEFT_ID = 4;
                public static final int BACK_RIGHT_ID = 3;

                public static final int GYRO_ID = 21;

                public static final int CURRENT_LIMIT = 60;
                public static final int VOLTAGE_COMPENSATION = 12;
                public static final int TIMEOUT = 250;

                public static final double MASS = 31.2;
                public static final double MOI = 2.328;
                public static final double TRACK_WIDTH = 0.546;
                public static final double GEAR_RATIO = 8.46;
                public static final double WHEEL_RADIUS = 0.076;
                public static final double MAX_VELOCITY = 4.0;
                public static final double ENCODER_POSITION_FACTOR = (2 * Math.PI * WHEEL_RADIUS) / GEAR_RATIO;
                public static final double ENCODER_VELOCITY_FACTOR = ENCODER_POSITION_FACTOR / 60.0;
        }

        public class FuelConstants {
                public static final int FEEDER_ID = 11;
                public static final int INTAKE_LAUNCHER_ID = 12;

                public static final int PEAK_CURRENT_LIMIT = 40;
                public static final int PEAK_CURRENT_DURATION = 1500;
                public static final int CONTINUOUS_CURRENT_LIMIT = 30;
                public static final int VOLTAGE_COMPENSATION = 12;

                public static final double INTAKE_FEEDER_VOLTAGE = -12;
                public static final double INTAKE_INTAKE_VOLTAGE = 9;

                public static final double LAUNCH_LAUNCHER_VOLTAGE = 12;
                public static final double REV_LAUNCHER_TIMEOUT = 1;

                public static final double EJECT_FEEDER_VOLTAGE = 10;
                public static final double EJECT_INTAKE_VOLTAGE = -10;
        }
}