package org.ironriders.subsystems;

import org.ironriders.commands.SwerveCommands;
import org.ironriders.constants.SwerveConstants;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.util.Units;

public class SwerveSubsystem extends SubsystemBase {

	private SwerveCommands swerveCommands;
	private SwerveDriveKinematics kinematics;

	public SwerveSubsystem() {
		swerveCommands = new SwerveCommands(this);

		kinematics = new SwerveDriveKinematics(
            new Translation2d(
				Units.inchesToMeters(SwerveConstants.SWERVE_MODULE_DISTANCE_X), 
				Units.inchesToMeters(SwerveConstants.SWERVE_MODULE_DISTANCE_Y)), // Front Left
            new Translation2d(
				Units.inchesToMeters(SwerveConstants.SWERVE_MODULE_DISTANCE_X), 
				Units.inchesToMeters(-SwerveConstants.SWERVE_MODULE_DISTANCE_Y)), // Front Right
            new Translation2d(
				Units.inchesToMeters(-SwerveConstants.SWERVE_MODULE_DISTANCE_X), 
				Units.inchesToMeters(SwerveConstants.SWERVE_MODULE_DISTANCE_Y)), // Back Left
            new Translation2d(
				Units.inchesToMeters(-SwerveConstants.SWERVE_MODULE_DISTANCE_X), 
				Units.inchesToMeters(-SwerveConstants.SWERVE_MODULE_DISTANCE_Y))  // Back Right
        );
	}

	public void drive(double translationX, double translationY, double angularRotation)
	{
		
	}

	@Override
	public void periodic() {
	}

	public SwerveCommands getCommands() {
		return swerveCommands;
	}
}
