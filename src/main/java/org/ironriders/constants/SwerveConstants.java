package org.ironriders.constants;

import edu.wpi.first.math.util.Units;

public class SwerveConstants {

    public static final int PIGEON_CAN_ID = 9;

    public static final double SWERVE_MAXIMUM_SPEED = Units.feetToMeters(4.5); // Meters per Second

	public static final double SWERVE_MODULE_STEER_TOLERANCE = 5.0; // Degrees
	public static final double SWERVE_MODULE_DRIVE_TOLERANCE = 0.2; // Meters per Second

	public static final double SWERVE_MODULE_DISTANCE_X = 12; // Inches
	public static final double SWERVE_MODULE_DISTANCE_Y = 12; // Inches

	public static final double SWERVE_MODULE_STEER_P = 0.17;
	public static final double SWERVE_MODULE_STEER_I = 0.0;
	public static final double SWERVE_MODULE_STEER_D = 1.0;

	public static final double SWERVE_MODULE_DRIVE_P = 0.1;
	public static final double SWERVE_MODULE_DRIVE_I = 0.0;
	public static final double SWERVE_MODULE_DRIVE_D = 0.01;

	// Front Left motor
	public static final int FRONT_LEFT_MODULE_STEER_CAN_ID = 7;
	public static final int FRONT_LEFT_MODULE_DRIVE_CAN_ID = 8;
	public static final int FRONT_LEFT_MODULE_ENCODER_ID = 2;

	// Front Right motor
	public static final int FRONT_RIGHT_MODULE_STEER_CAN_ID = 1;
	public static final int FRONT_RIGHT_MODULE_DRIVE_CAN_ID = 2;
	public static final int FRONT_RIGHT_MODULE_ENCODER_ID = 1;

	// Back Left motor
	public static final int BACK_LEFT_MODULE_STEER_CAN_ID = 5;
	public static final int BACK_LEFT_MODULE_DRIVE_CAN_ID = 6;
	public static final int BACK_LEFT_MODULE_ENCODER_ID = 3;

	// Back Right motor
	public static final int BACK_RIGHT_MODULE_STEER_CAN_ID = 3;
	public static final int BACK_RIGHT_MODULE_DRIVE_CAN_ID = 4;
	public static final int BACK_RIGHT_MODULE_ENCODER_ID = 0;

	// Conversion factors
	public static final double STEER_POSITION_CONVERSION_FACTOR = 28.125;
	public static final double DRIVE_VELOCITY_CONVERSION_FACTOR = 0.0;
}
