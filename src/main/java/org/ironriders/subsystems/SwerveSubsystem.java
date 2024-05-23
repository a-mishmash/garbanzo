package org.ironriders.subsystems;

import java.io.IOException;

import org.ironriders.commands.SwerveCommands;
import org.ironriders.constants.SwerveConstants;

import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The SwerveSubsystem encompasses everything that the Swerve Drive needs to function.
 * It keeps track of the robot's position and angle, and uses the controller
 * input to figure out how the individual modules need to turn and be angled.
 */
public class SwerveSubsystem extends SubsystemBase {

	private SwerveCommands swerveCommands;
	private SwerveDrive swerveDrive;

	public SwerveSubsystem() {

		swerveCommands = new SwerveCommands(this);
		try {
			swerveDrive = 
				new SwerveParser(SwerveConstants.SWERVE_JSON_DIRECTORY)
					.createSwerveDrive(SwerveConstants.SWERVE_MAXIMUM_SPEED);
		} catch(IOException e) {
			// add something here
		}

		swerveDrive.setHeadingCorrection(false);
		
		SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
	}

	/** Vrrrrooooooooom brrrrrrrrr BRRRRRR wheeee BRRR brrrr VRRRRROOOOOOM */
	public void drive(double translationX, double translationY, double angularRotation) {

		ChassisSpeeds chassisSpeeds = new ChassisSpeeds();
		chassisSpeeds.vxMetersPerSecond = translationX * swerveDrive.getMaximumVelocity();
		chassisSpeeds.vyMetersPerSecond = translationY * swerveDrive.getMaximumVelocity();
		chassisSpeeds.omegaRadiansPerSecond = angularRotation * swerveDrive.getMaximumAngularVelocity();

		// Make the robot move
		swerveDrive.driveFieldOriented(chassisSpeeds);
	}

	/** Fetch the SwerveCommands class */
	public SwerveCommands getCommands() {
		return swerveCommands;
	}
}
