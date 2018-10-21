package org.example.swerve;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import org.example.swerve.controls.Controls;
import org.example.swerve.controls.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.strykeforce.thirdcoast.swerve.SwerveDrive;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/** Third Coast swerve drive demo robot that uses Dagger dependency injection. */
public class Robot extends IterativeRobot {

  static final Logger logger = LoggerFactory.getLogger(Robot.class);

  /** Configuration file CONFIG is tried first for swerve drive Talon configurations. */
  private static final String CONFIG = "/home/lvuser/swerve.toml";

  /** If CONFIG is missing, then JAR file DEFAULT_CONFIG will be used. */
  private static final String DEFAULT_CONFIG = "/META-INF/settings.toml";

  /** Flight simulator joystick deadband. */
  private static final double DEADBAND = 0.05;

  /** See {@code getConfig} below. */
  private RobotComponent component;

  private SwerveDrive swerve;
  private Controls controls;
  private Trigger gyroResetButton;

  /**
   * Initialize the robot. We use the dependency injection framework to get configured controls and
   * swerve drive. See {@link org.strykeforce.thirdcoast.swerve.SwerveDrive} for configuration
   * details.
   */
  @Override
  public void robotInit() {
    controls = getComponent().controls();
    gyroResetButton = controls.getResetButton();
    swerve = getComponent().swerveDrive();

    // offset the relative azimuth encoders by the difference between the stored zero position and
    // the absolute position. Stored zero positions are stored in the WPILib preference under
    // the keys: "SwerveDrive/wheel.0" through "SwerveDrive/wheel.3".
    swerve.zeroAzimuthEncoders();
  }

  @Override
  public void teleopInit() {
    swerve.stop();
  }

  @Override
  public void teleopPeriodic() {
    if (gyroResetButton.hasActivated()) {
      String msg = "Resetting gyro yaw zero";
      logger.warn(msg);
      DriverStation.reportWarning(msg, false);
      swerve.getGyro().zeroYaw();
    }
    double forward = applyDeadband(controls.getForward());
    double strafe = applyDeadband(controls.getStrafe());
    double azimuth = applyDeadband(controls.getAzimuth());

    swerve.drive(forward, strafe, azimuth);
  }

  /** Compensate for the joystick not returning perfectly to zero when in neutral position. */
  private double applyDeadband(double input) {
    if (Math.abs(input) < DEADBAND) {
      return 0;
    }
    return input;
  }

  /**
   * Initialize dependency injection if needed and return the Dagger component used to get
   * configured robot components. This will first look for a config file in "CONFIG" and fall back
   * to a default config in the JAR file.
   *
   * @return the Dagger robot component.
   */
  private RobotComponent getComponent() {
    if (component != null) return component;

    URL config = null;

    File f = new File(CONFIG);
    if (f.exists() && !f.isDirectory()) {
      try {
        config = f.toURI().toURL();
      } catch (MalformedURLException e) {
        logger.error(CONFIG, e);
      }
    }

    if (config == null) config = this.getClass().getResource(DEFAULT_CONFIG);

    logger.info("reading settings from '{}'", config);
    component = DaggerRobotComponent.builder().config(config).build();
    return component;
  }
}
