package org.ironriders.robot;

import org.ironriders.commands.SwerveCommands;
import org.ironriders.constants.DriveConstants;
import org.ironriders.subsystems.SwerveSubsystem;

import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
	private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
	private final SwerveCommands swerveCommands = swerveSubsystem.getSwerveCommands();

	private final PS5Controller driverController =
	new PS5Controller(DriveConstants.DRIVER_CONTROLLER_PORT);

	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer() {
		configureBindings();
	}

	private void configureBindings() {
		swerveSubsystem.setDefaultCommand(
			swerveCommands.drive(
				driverController.getLeftX(),
				driverController.getLeftY(),
				driverController.getRightX()
			)
		);
	}
}
