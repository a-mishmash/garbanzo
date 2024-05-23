package org.ironriders.constants;

import java.io.File;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;

public class SwerveConstants {

    public static final double SWERVE_MAXIMUM_SPEED = Units.feetToMeters(4.5); // Meters per Second

	public static final File SWERVE_JSON_DIRECTORY = new File(Filesystem.getDeployDirectory(),"swerve");
}
