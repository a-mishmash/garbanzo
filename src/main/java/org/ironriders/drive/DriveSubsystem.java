package org.ironriders.drive;

import java.io.IOException;
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
public class DriveSubsystem extends SubsystemBase {

	private DriveCommands swerveCommands;
	private SwerveDrive swerveDrive;

	private boolean xLocked;

	public DriveSubsystem() {

		swerveCommands = new DriveCommands(this);
		try {
			swerveDrive = 
				new SwerveParser(DriveConstants.SWERVE_JSON_DIRECTORY)
					.createSwerveDrive(DriveConstants.SWERVE_MAXIMUM_SPEED);
		} catch(IOException e) {
			// do something
		}

		swerveDrive.setHeadingCorrection(false);
		
		SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
	}

	/** Vrrrrooooooooom brrrrrrrrr BRRRRRR wheeee BRRR brrrr VRRRRROOOOOOM ZOOOOOOM ZOOOOM WAHOOOOOOOOO WAHAHAHHA */
	public void drive(double inputX, double inputY, double angularInput) {

		// if(xLocked) {
		// 	swerveDrive.setModuleStates(new SwerveModuleState[] {
		// 		new SwerveModuleState(0.1, new Rotation2d(Math.toRadians(225.0))),
		// 		new SwerveModuleState(0.1, new Rotation2d(Math.toRadians(135.0))),
		// 		new SwerveModuleState(0.1, new Rotation2d(Math.toRadians(-225.0))),
		// 		new SwerveModuleState(0.1, new Rotation2d(Math.toRadians(135.0))),
		// 	}, false);
		// 	return;
		// }

		SmartDashboard.putNumber("Input X", inputX);
		SmartDashboard.putNumber("Input Y", inputY);
		SmartDashboard.putNumber("Input Rotation", angularInput);

		Translation2d translationXY = new Translation2d(inputX * swerveDrive.getMaximumVelocity(), inputY * swerveDrive.getMaximumVelocity());
		double rotation = angularInput * swerveDrive.getMaximumAngularVelocity() * DriveConstants.SWERVE_ANGULAR_SPEED_MULTIPLIER;

		// Make the robot move
		swerveDrive.drive(translationXY, rotation, true, false);
	}

	public void setXLock(boolean set) {
		xLocked = set;
	}

	/** Fetch the SwerveCommands class */
	public DriveCommands getCommands() {
		return swerveCommands;
	}
}
