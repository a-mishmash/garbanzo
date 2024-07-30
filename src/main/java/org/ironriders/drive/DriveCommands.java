package org.ironriders.drive;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;

public class DriveCommands {
	private final DriveSubsystem driveSubsystem;

	public DriveCommands(DriveSubsystem driveSubsystem) {
		this.driveSubsystem = driveSubsystem;
	}

	public Command drive(DoubleSupplier translationX, DoubleSupplier translationY, DoubleSupplier angularRotation) {
		return driveSubsystem.runOnce(() -> {
			driveSubsystem.drive(
				translationX.getAsDouble(), 
				translationY.getAsDouble(), 
				angularRotation.getAsDouble()
			);
		});
	}

	public Command setXLock(BooleanSupplier set) {
		return driveSubsystem.runOnce(() -> {
			driveSubsystem.setXLock(set.getAsBoolean());
		});
	}
}
