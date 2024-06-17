package org.ironriders.subsystems;

import java.io.IOException;

import org.ironriders.commands.SwerveCommands;
import org.ironriders.constants.SwerveConstants;

import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The SwerveSubsystem encompasses everything that the Swerve Drive needs to function.
 * It keeps track of the robot's position and angle, and uses the controller
 * input to figure out how the individual modules need to turn and be angled.
 */
public class SwerveSubsystem extends SubsystemBase {

	private SwerveCommands swerveCommands;
	private SwerveDrive swerveDrive;

	private boolean xLocked;

	public SwerveSubsystem() {

		swerveCommands = new SwerveCommands(this);
		try {
			swerveDrive = 
				new SwerveParser(SwerveConstants.SWERVE_JSON_DIRECTORY)
					.createSwerveDrive(SwerveConstants.SWERVE_MAXIMUM_SPEED);
		} catch(IOException e) {
			// do something
		}

		swerveDrive.setHeadingCorrection(false);
		
		SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
	}

	/** Vrrrrooooooooom brrrrrrrrr BRRRRRR wheeee BRRR brrrr VRRRRROOOOOOM */
	public void drive(double translationX, double translationY, double angularRotation) {

		if(xLocked) {
			swerveDrive.setModuleStates(new SwerveModuleState[] {
				new SwerveModuleState(0.1, new Rotation2d(Math.toRadians(225.0))),
				new SwerveModuleState(0.1, new Rotation2d(Math.toRadians(135.0))),
				new SwerveModuleState(0.1, new Rotation2d(Math.toRadians(-225.0))),
				new SwerveModuleState(0.1, new Rotation2d(Math.toRadians(135.0))),
			}, false);
			return;
		}

		SmartDashboard.putNumber("Translation X", translationX);
		SmartDashboard.putNumber("Translation Y", translationY);
		SmartDashboard.putNumber("Angular Rotation", angularRotation);

		Translation2d translationXY = new Translation2d(translationX * swerveDrive.getMaximumVelocity(), translationY * swerveDrive.getMaximumVelocity());
		double rotation = 0.0;// angularRotation * swerveDrive.getMaximumAngularVelocity();

		// Make the robot move
		swerveDrive.drive(translationXY, rotation, true, false);
	}

	public void setXLock(boolean set) {
		xLocked = set;
	}

	/** Fetch the SwerveCommands class */
	public SwerveCommands getCommands() {
		return swerveCommands;
	}
}
