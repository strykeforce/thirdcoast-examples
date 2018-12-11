package frc.team2767.swerveexample.subsystem;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2767.swerveexample.command.TeleOpDriveCommand;
import org.strykeforce.thirdcoast.swerve.SwerveDrive;
import org.strykeforce.thirdcoast.swerve.SwerveDrive.DriveMode;
import org.strykeforce.thirdcoast.swerve.SwerveDriveConfig;
import org.strykeforce.thirdcoast.swerve.Wheel;

public class DriveSubsystem extends Subsystem {

  private static final double DRIVE_SETPOINT_MAX = 0.0;
  private static final double ROBOT_LENGTH = 1.0;
  private static final double ROBOT_WIDTH = 1.0;

  private final SwerveDrive swerve = getSwerve();

  public DriveSubsystem() {}

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new TeleOpDriveCommand());
  }

  public void setDriveMode(DriveMode mode) {
    swerve.setDriveMode(mode);
  }

  public void drive(double forward, double strafe, double azimuth) {
    swerve.drive(forward, strafe, azimuth);
  }

  // Swerve configuration

  private SwerveDrive getSwerve() {
    SwerveDriveConfig config = new SwerveDriveConfig();
    config.wheels = getWheels();
    config.gyro = new AHRS(SPI.Port.kMXP);
    config.length = ROBOT_LENGTH;
    config.width = ROBOT_WIDTH;
    config.gyroLoggingEnabled = true;
    config.summarizeTalonErrors = false;

    return new SwerveDrive(config);
  }

  private Wheel[] getWheels() {

    // TODO: WILL NOT DRIVE - configure Motion Magic closed-loop control parameters for your azimuth motors
    TalonSRXConfiguration azimuthConfig = new TalonSRXConfiguration();
    azimuthConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
    azimuthConfig.continuousCurrentLimit = 10;
    azimuthConfig.peakCurrentDuration = 0;
    azimuthConfig.peakCurrentLimit = 0;

    azimuthConfig.slot_0.kP = 0.0;
    azimuthConfig.slot_0.kI = 0.0;
    azimuthConfig.slot_0.kD = 0.0;
    azimuthConfig.slot_0.kF = 0.0;
    azimuthConfig.slot_0.integralZone = 0;
    azimuthConfig.slot_0.allowableClosedloopError = 0;
    azimuthConfig.motionAcceleration = 10_000;
    azimuthConfig.motionCruiseVelocity = 800;

    // TODO: verify Percent Output configuration for your drive motors
    TalonSRXConfiguration driveConfig = new TalonSRXConfiguration();
    driveConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
    driveConfig.continuousCurrentLimit = 40;
    driveConfig.peakCurrentDuration = 0;
    driveConfig.peakCurrentLimit = 0;

    Wheel[] wheels = new Wheel[4];

    for (int i = 0; i < 4; i++) {
      TalonSRX azimuthTalon = new TalonSRX(i);
      azimuthTalon.configAllSettings(azimuthConfig);

      TalonSRX driveTalon = new TalonSRX(i + 10);
      driveTalon.configAllSettings(driveConfig);
      driveTalon.setNeutralMode(NeutralMode.Brake);

      Wheel wheel = new Wheel(azimuthTalon, driveTalon, DRIVE_SETPOINT_MAX);
      wheels[i] = wheel;
    }

    return wheels;
  }
}
