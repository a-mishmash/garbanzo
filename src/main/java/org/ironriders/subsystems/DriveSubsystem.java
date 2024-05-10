package org.ironriders.subsystems;

import org.ironriders.commands.DriveCommand;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.util.Units;

public class DriveSubsystem extends SubsystemBase {
	
	private SwerveDriveKinematics kinematics;
	private SwerveDriveOdometry odometry;

	public DriveSubsystem() {
		kinematics = new SwerveDriveKinematics(
			// TODO: Actual measurements
            new Translation2d(Units.inchesToMeters(12.), Units.inchesToMeters(12.5)), // Front Left
            new Translation2d(Units.inchesToMeters(12.5), Units.inchesToMeters(-12.5)), // Front Right
            new Translation2d(Units.inchesToMeters(-12.5), Units.inchesToMeters(12.5)), // Back Left
            new Translation2d(Units.inchesToMeters(-12.5), Units.inchesToMeters(-12.5))  // Back Right
        );
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
