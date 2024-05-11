package org.ironriders.subsystems;

import org.ironriders.commands.DriveCommand;
import org.ironriders.constants.CANIDConstants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.util.Units;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstantsFactory;

public class DriveSubsystem extends SubsystemBase {
	
	private SwerveDriveKinematics kinematics;
	private Pigeon2 gyro;
	private SwerveDriveOdometry odometry;
	private SwerveModule[] swerveModules;

	public DriveSubsystem() {
		swerveModules = new SwerveModule[]{
			new SwerveModule(null, getName()),
			new SwerveModule(null, getName()),
			new SwerveModule(null, getName()),
			new SwerveModule(null, getName())
		};

		kinematics = new SwerveDriveKinematics(
			// TODO: Actual measurements
            new Translation2d(Units.inchesToMeters(12.), Units.inchesToMeters(12.5)), // Front Left
            new Translation2d(Units.inchesToMeters(12.5), Units.inchesToMeters(-12.5)), // Front Right
            new Translation2d(Units.inchesToMeters(-12.5), Units.inchesToMeters(12.5)), // Back Left
            new Translation2d(Units.inchesToMeters(-12.5), Units.inchesToMeters(-12.5))  // Back Right
        );

		gyro = new Pigeon2(CANIDConstants.PIGEON_CAN_ID);

		odometry = new SwerveDriveOdometry(
			kinematics, 
			Rotation2d.fromDegrees(gyro.getAngle()),
			new SwerveModulePosition[] {
				new SwerveModulePosition(),
				new SwerveModulePosition(),
				new SwerveModulePosition(),
				new SwerveModulePosition()
			},
			new Pose2d(0, 0, new Rotation2d())
		);
	}

	// Simple drive function
    public void drive()
    {
        // Create test ChassisSpeeds going X = 14in, Y=4in, and spins at 30deg per second.
        ChassisSpeeds testSpeeds = new ChassisSpeeds(
			Units.inchesToMeters(14), Units.inchesToMeters(4), Units.degreesToRadians(30)
		);
        
        // Get the SwerveModuleStates for each module given the desired speeds.
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(testSpeeds);
        // Output order is Front-Left, Front-Right, Back-Left, Back-Right

		swerveModules[0].setState(swerveModuleStates[0]);
        swerveModules[1].setState(swerveModuleStates[1]);
        swerveModules[2].setState(swerveModuleStates[2]);
        swerveModules[3].setState(swerveModuleStates[3]);
    }

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
