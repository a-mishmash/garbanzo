package org.ironriders.commands;

import java.util.function.DoubleSupplier;

import org.ironriders.subsystems.SwerveSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class SwerveCommands {
	private final SwerveSubsystem driveSubsystem;

	public SwerveCommands(SwerveSubsystem driveSubsystem) {
		this.driveSubsystem = driveSubsystem;
	}

	public Command drive(DoubleSupplier translationX, DoubleSupplier translationY, DoubleSupplier angularRotationX) {
		return driveSubsystem.runOnce(() -> {
			driveSubsystem.drive(0.1, 0.0, 0.0);
		});
	}
}
