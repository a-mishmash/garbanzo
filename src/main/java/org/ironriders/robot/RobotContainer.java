package org.ironriders.robot;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import org.ironriders.commands.SwerveCommands;
import org.ironriders.constants.DriveConstants;
import org.ironriders.subsystems.SwerveSubsystem;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
	private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
	private final SwerveCommands swerveCommands = swerveSubsystem.getCommands();

	private final XboxController driverController =
		new XboxController(DriveConstants.DRIVER_CONTROLLER_PORT);

	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer() {
		configureBindings();
	}

	private void configureBindings() {
		swerveSubsystem.setDefaultCommand(
			swerveCommands.drive(
				() -> driverController.getLeftY(),
				() -> driverController.getLeftX(),
				() -> driverController.getLeftY()
			)
		);
	}
}
