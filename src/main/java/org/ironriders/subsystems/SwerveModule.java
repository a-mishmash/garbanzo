package org.ironriders.subsystems;

import org.ironriders.constants.SwerveConstants;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.MotorFeedbackSensor;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
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
    private SparkPIDController drivePID;

    private AnalogEncoder absoluteEncoder;
    private RelativeEncoder driveEncoder;

    public SwerveModule(String name, int steerMotorCANID, int driveMotorCANID, int encoderAnalogID) {

        // Find motor SparkMaxes based on their ID on the CANBUS
        steerMotor = new CANSparkMax(steerMotorCANID, MotorType.kBrushless);
        driveMotor = new CANSparkMax(driveMotorCANID, MotorType.kBrushless);

        steerPID = new PIDController(0, 0, 0);
        drivePID = driveMotor.getPIDController();

        absoluteEncoder = new AnalogEncoder(encoderAnalogID);
        driveEncoder = driveMotor.getEncoder();
        
        // Reset everything to factory defaults
        steerMotor.restoreFactoryDefaults();
        driveMotor.restoreFactoryDefaults();

        // PID stuff
        steerPID.setP(SwerveConstants.SWERVE_MODULE_STEER_P);
        steerPID.setI(SwerveConstants.SWERVE_MODULE_STEER_I);
        steerPID.setD(SwerveConstants.SWERVE_MODULE_STEER_D);

        drivePID.setP(SwerveConstants.SWERVE_MODULE_DRIVE_P);
        drivePID.setI(SwerveConstants.SWERVE_MODULE_DRIVE_I);
        drivePID.setD(SwerveConstants.SWERVE_MODULE_DRIVE_D);

        // We do this so that the controller doesn't think the shortest path
        // from, for example, 359 to 1 is to go aaaalllllll the way around.
        steerPID.enableContinuousInput(0, 90);

        // More PID stuff yippeeeeeee
        drivePID.setFeedbackDevice((MotorFeedbackSensor) driveEncoder);
        driveEncoder.setVelocityConversionFactor(SwerveConstants.DRIVE_VELOCITY_CONVERSION_FACTOR);
        driveEncoder.setPosition(0);
    }

    public void update() {
        steerMotor.set(steerPID.calculate(absoluteEncoder.getAbsolutePosition()));
    }

    public double getDistance() {
        return driveEncoder.getPosition();
    }
    
    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees(absoluteEncoder.getAbsolutePosition() * 360);
    }
    
    /** Set the swerve module state. */
    public void setState(SwerveModuleState state) {
        steerPID.setSetpoint(state.angle.getDegrees());
        drivePID.setReference(state.speedMetersPerSecond, ControlType.kVelocity);
    }
}