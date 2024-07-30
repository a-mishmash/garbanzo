package org.ironriders.constants;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;

public class SwerveConstants {

    public static final double SWERVE_MAXIMUM_SPEED = 2.0; // Meters per Second
    public static final double SWERVE_ANGULAR_SPEED_MULTIPLIER = 0.75;

	public static final File SWERVE_JSON_DIRECTORY = new File(Filesystem.getDeployDirectory(),"swerve");
}
