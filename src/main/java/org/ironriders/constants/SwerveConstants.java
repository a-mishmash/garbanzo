package org.ironriders.constants;

import java.io.File;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;

public class SwerveConstants {
    public static final int PIGEON_CAN_ID = 9;
    public static double SWERVE_MAXIMUM_SPEED = Units.feetToMeters(4.5);
    public static File SWERVE_JSON_DIRECTORY = new File(Filesystem.getDeployDirectory(),"swerve");
}
