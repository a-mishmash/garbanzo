package org.ironriders.subsystems;

import org.ironriders.commands.SwerveCommands;
import org.ironriders.constants.SwerveConstants;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.util.Units;

/**
 * The SwerveSubsystem encompasses everything that the Swerve Drive needs to function.
 * It keeps track of the robot's position and angle, and uses the controller
 * input to figure out how the individual modules need to turn and be angled.
 */
public class SwerveSubsystem extends SubsystemBase {

	private SwerveCommands swerveCommands;
	private SwerveDriveKinematics kinematics;
	private SwerveDriveOdometry odometry;
	private Pigeon2 gyro;
	private SwerveModule[] swerveModules;

	public SwerveSubsystem() {
		swerveCommands = new SwerveCommands(this);
		gyro = new Pigeon2(SwerveConstants.PIGEON_CAN_ID); // Make a Pigeon (gyroscope) with the id on the CAN bus
		swerveModules = new SwerveModule[] {
			new SwerveModule(
				SwerveConstants.FRONT_LEFT_MODULE_STEER_CAN_ID, 
				SwerveConstants.FRONT_LEFT_MODULE_DRIVE_CAN_ID, 
				SwerveConstants.FRONT_LEFT_MODULE_ENCODER_ID
			), // FL
			new SwerveModule(
				SwerveConstants.FRONT_RIGHT_MODULE_STEER_CAN_ID, 
				SwerveConstants.FRONT_RIGHT_MODULE_DRIVE_CAN_ID, 
				SwerveConstants.FRONT_RIGHT_MODULE_ENCODER_ID
			), // FR
			new SwerveModule(
				SwerveConstants.BACK_LEFT_MODULE_STEER_CAN_ID, 
				SwerveConstants.BACK_LEFT_MODULE_DRIVE_CAN_ID, 
				SwerveConstants.BACK_LEFT_MODULE_ENCODER_ID
			), // BL
			new SwerveModule(
				SwerveConstants.BACK_RIGHT_MODULE_STEER_CAN_ID, 
				SwerveConstants.BACK_RIGHT_MODULE_DRIVE_CAN_ID, 
				SwerveConstants.BACK_RIGHT_MODULE_ENCODER_ID
			) // BR
		};

		// Make the kinematics class with four Translation2ds, which each represent the X and Y of a swerve module
		kinematics = new SwerveDriveKinematics(
            new Translation2d(
				Units.inchesToMeters(SwerveConstants.SWERVE_MODULE_DISTANCE_X), 
				Units.inchesToMeters(SwerveConstants.SWERVE_MODULE_DISTANCE_Y)), // Front Left
            new Translation2d(
				Units.inchesToMeters(SwerveConstants.SWERVE_MODULE_DISTANCE_X), 
				Units.inchesToMeters(-SwerveConstants.SWERVE_MODULE_DISTANCE_Y)), // Front Right
            new Translation2d(
				Units.inchesToMeters(-SwerveConstants.SWERVE_MODULE_DISTANCE_X), 
				Units.inchesToMeters(SwerveConstants.SWERVE_MODULE_DISTANCE_Y)), // Back Left
            new Translation2d(
				Units.inchesToMeters(-SwerveConstants.SWERVE_MODULE_DISTANCE_X), 
				Units.inchesToMeters(-SwerveConstants.SWERVE_MODULE_DISTANCE_Y))  // Back Right
        );

		// The odometry class keeps track of every module's position and the robot's angle/rotation
		odometry = new SwerveDriveOdometry(
			kinematics, 
			new Rotation2d(Math.toRadians(gyro.getAngle())),
			new SwerveModulePosition[] {
				new SwerveModulePosition(), 
				new SwerveModulePosition(), 
				new SwerveModulePosition(), 
				new SwerveModulePosition()
			},
			new Pose2d()
		);
	}

	/** Vrrrrooooooooom brrrrrrrrr BRRRRRR wheeee BRRR brrrr VRRRRROOOOOOM */
	public void drive(double translationX, double translationY, double angularRotation) {
		// chassisSpeeds represents the desired speed of the robot
		ChassisSpeeds chassisSpeeds = new ChassisSpeeds(
			translationX,
			translationY,
			angularRotation
		);

		// Covert desired speed into module speeds & angles
		SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(chassisSpeeds);

		// Set the module states
		swerveModules[0].setState(swerveModuleStates[0]);
        swerveModules[1].setState(swerveModuleStates[1]);
        swerveModules[2].setState(swerveModuleStates[2]);
        swerveModules[3].setState(swerveModuleStates[3]);
	}

	// Fetch the current swerve module positions.
    public SwerveModulePosition[] getCurrentSwerveModulePositions() {
        return new SwerveModulePosition[]{
            new SwerveModulePosition(swerveModules[0].getDistance(), swerveModules[0].getAngle()), // Front-Left
            new SwerveModulePosition(swerveModules[1].getDistance(), swerveModules[1].getAngle()), // Front-Right
            new SwerveModulePosition(swerveModules[2].getDistance(), swerveModules[2].getAngle()), // Back-Left
            new SwerveModulePosition(swerveModules[3].getDistance(), swerveModules[3].getAngle())  // Back-Right
        };
    }
    
    @Override
    public void periodic() {
        // Update the odometry every run.
        odometry.update(new Rotation2d(gyro.getAngle()),  getCurrentSwerveModulePositions());
    }

	/** Fetch the SwerveCommands class */
	public SwerveCommands getCommands() {
		return swerveCommands;
	}
}
