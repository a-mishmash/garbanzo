package org.ironriders.robot;

import org.ironriders.drive.DriveConstants;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

	private enum Test {
		TEST1,
		TEST2
	}

	private static final Test DEFAULT_AUTO = Test.TEST1;

	private static SendableChooser<Test> autoSendableChooser = new SendableChooser<>();

	private RobotContainer robotContainer;

	@Override
	public void robotInit() {
		autoSendableChooser.setDefaultOption("Test 1", DEFAULT_AUTO);
		autoSendableChooser.addOption("Test 2", Test.TEST2);
		SmartDashboard.putData("Autonomous Routine", autoSendableChooser);

		robotContainer = new RobotContainer();
	}

	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {
		
	}
}
