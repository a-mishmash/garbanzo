package org.ironriders.drive;

import java.io.File;

import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.wpilibj.Filesystem;

public class DriveConstants {

    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final double CONTROLLER_DEADBAND = 0.1; // 0 - 1

    public static final double SWERVE_MAXIMUM_SPEED = 4.5; // Meters per second
    public static final double SWERVE_BASE_RADIUS = 0.0; // Meters

	public static final File SWERVE_JSON_DIRECTORY = new File(Filesystem.getDeployDirectory(), "swerve"); // YAGSL directory

    public static final HolonomicPathFollowerConfig HOLONOMIC_CONFIG = new HolonomicPathFollowerConfig(
        new PIDConstants(5.0, 0.0, 0.0), // Translation PID constants
        new PIDConstants(5.0, 0.0, 0.0), // Rotation PID constants
        SWERVE_MAXIMUM_SPEED,
        0.43,
        new ReplanningConfig() // Default path replanning config
    );
}
