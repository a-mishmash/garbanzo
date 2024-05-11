package org.ironriders.commands;

import org.ironriders.subsystems.SwerveSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class SwerveCommands {
	private final SwerveSubsystem driveSubsystem;

	public SwerveCommands(SwerveSubsystem driveSubsystem) {
		this.driveSubsystem = driveSubsystem;
	}

	public Command drive(double translationX, double translationY, double angularRotationX) {
		return driveSubsystem.runOnce(() -> {
			driveSubsystem.drive(translationX, translationY, angularRotationX);
		});
	}
}
