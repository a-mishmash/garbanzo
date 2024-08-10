package org.ironriders.robot;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import org.ironriders.drive.DriveCommands;
import org.ironriders.drive.DriveConstants;
import org.ironriders.drive.DriveSubsystem;
import org.ironriders.util.ControlUtils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
	private final DriveSubsystem swerveSubsystem = new DriveSubsystem();
	private final DriveCommands swerveCommands = swerveSubsystem.getCommands();

	private final CommandXboxController driverController =
		new CommandXboxController(DriveConstants.DRIVER_CONTROLLER_PORT);

	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer() {
		configureBindings();
	}

	private void configureBindings() {
		swerveSubsystem.setDefaultCommand(
			swerveCommands.driveTeleop(
				() -> ControlUtils.controlCurve(-driverController.getLeftY()),
				() -> ControlUtils.controlCurve(-driverController.getLeftX()),
				() -> ControlUtils.controlCurve(-driverController.getRightX())
			)
		);
	}
}
