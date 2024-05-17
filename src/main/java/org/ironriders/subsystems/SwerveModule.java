package org.ironriders.subsystems;

import org.ironriders.constants.SwerveConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.AnalogEncoder;

/** Class representing a single Swerve module (drive motor, angle motor) */
public class SwerveModule {

    private CANSparkMax steerMotor;
    private CANSparkMax driveMotor;

    private PIDController steerPID;
    private PIDController drivePID;

    private AnalogEncoder absoluteEncoder;
    private RelativeEncoder driveEncoder;

    public SwerveModule(int steerMotorCANID, int driveMotorCANID, int encoderAnalogID) {

        // Find motor SparkMaxes based on their ID on the CANBUS
        steerMotor = new CANSparkMax(steerMotorCANID, MotorType.kBrushless);
        driveMotor = new CANSparkMax(driveMotorCANID, MotorType.kBrushless);

        absoluteEncoder = new AnalogEncoder(encoderAnalogID);
        driveEncoder = driveMotor.getEncoder();
        
        // Reset everything to factory defaults
        driveMotor.restoreFactoryDefaults();
        steerMotor.restoreFactoryDefaults();

        // PID stuff
        steerPID = new PIDController(
            SwerveConstants.SWERVE_MODULE_STEER_P,
            SwerveConstants.SWERVE_MODULE_STEER_I,
            SwerveConstants.SWERVE_MODULE_STEER_D
        );
        drivePID = new PIDController(
            SwerveConstants.SWERVE_MODULE_DRIVE_P, 
            SwerveConstants.SWERVE_MODULE_DRIVE_I, 
            SwerveConstants.SWERVE_MODULE_DRIVE_D
        );

        // We do this so that the controller doesn't think the shortest path
        // from, for example, 359 to 1 is to go aaaalllllll the way around.
        steerPID.enableContinuousInput(0, 360);
    }

    /** Update the PID Controllers and run the motors based on the output. */
    public void update() {
        steerMotor.set(steerPID.calculate(getAngle().getDegrees()));
        driveMotor.set(drivePID.calculate(driveEncoder.getVelocity() * SwerveConstants.WHEEL_RPM_TO_MPS));
    }

    public double getDistance() {
        return driveEncoder.getPosition();
    }
    
    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees(absoluteEncoder.getAbsolutePosition());
    }
    
    /** Set the swerve module state. */
    public void setState(SwerveModuleState state) {
        steerPID.setSetpoint(state.angle.getDegrees());
        drivePID.setSetpoint(state.speedMetersPerSecond);
    }
}