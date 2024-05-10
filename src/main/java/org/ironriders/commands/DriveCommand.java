package org.ironriders.commands;

import org.ironriders.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class DriveCommand extends Command {
	private final DriveSubsystem drive;

	public DriveCommand(DriveSubsystem drive) {
		this.drive = drive;

		addRequirements(drive);
	}

	@Override
	public void initialize() {

	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void end(boolean interrupted) {

	}
}
