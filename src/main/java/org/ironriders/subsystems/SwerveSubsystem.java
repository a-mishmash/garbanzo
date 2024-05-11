package org.ironriders.subsystems;

import org.ironriders.commands.SwerveCommands;
import org.ironriders.constants.SwerveConstants;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.io.IOException;
import java.util.function.DoubleSupplier;

import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class SwerveSubsystem extends SubsystemBase {

	private SwerveCommands swerveCommands;
	private SwerveDrive swerveDrive;

	public SwerveSubsystem() {
		swerveCommands = new SwerveCommands(this);
		
		try {
			swerveDrive = new SwerveParser(SwerveConstants.SWERVE_JSON_DIRECTORY)
				.createSwerveDrive(SwerveConstants.SWERVE_MAXIMUM_SPEED);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}

		SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
	}

	public void drive(double translationX, double translationY, double angularRotationX)
	{
		// Make the robot move
		swerveDrive.drive(new Translation2d(translationX * swerveDrive.getMaximumVelocity(),
			translationY * swerveDrive.getMaximumVelocity()),
			angularRotationX * swerveDrive.getMaximumAngularVelocity(),
			true,
			false
		);
	}

	public SwerveCommands getSwerveCommands() {
		return swerveCommands;
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
