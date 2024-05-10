package org.ironriders.robot;

import org.ironriders.constants.OperatorConstants;
import org.ironriders.commands.DriveCommand;
import org.ironriders.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
	private final DriveSubsystem m_exampleSubsystem = new DriveSubsystem();

	private final CommandXboxController m_driverController =
	new CommandXboxController(OperatorConstants.kDriverControllerPort);

	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer() {
		configureBindings();
	}

	private void configureBindings() {

	}
}
