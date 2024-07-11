package org.ironriders.constants;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;

public class SwerveConstants {

    public static final double SWERVE_MAXIMUM_SPEED = 6; // Meters per Second

	public static final File SWERVE_JSON_DIRECTORY = new File(Filesystem.getDeployDirectory(),"swerve");
}
