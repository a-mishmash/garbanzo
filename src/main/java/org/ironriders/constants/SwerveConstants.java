package org.ironriders.constants;

import edu.wpi.first.math.util.Units;

public class SwerveConstants {

    public static final int PIGEON_CAN_ID = 9;

    public static final double SWERVE_MAXIMUM_SPEED = Units.feetToMeters(4.5);

	public static final double SWERVE_MODULE_DISTANCE_X = 12;
	public static final double SWERVE_MODULE_DISTANCE_Y = 12;

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
}
