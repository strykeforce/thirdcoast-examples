package frc.team2767.swerveexample.command;

import static org.strykeforce.thirdcoast.swerve.SwerveDrive.DriveMode.TELEOP;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2767.swerveexample.Robot;
import frc.team2767.swerveexample.control.DriverControls;
import frc.team2767.swerveexample.subsystem.DriveSubsystem;

public final class TeleOpDriveCommand extends Command {
  private static final double DEADBAND = 0.05;

  private static final DriveSubsystem swerve = Robot.DRIVE;
  private static final DriverControls controls = Robot.CONTROLS.getDriverControls();

  public TeleOpDriveCommand() {
    requires(swerve);
  }

  @Override
  protected void initialize() {
    swerve.setDriveMode(TELEOP);
  }

  @Override
  protected void execute() {
    double forward = deadband(controls.getForward());
    double strafe = deadband(controls.getStrafe());
    double azimuth = deadband(controls.getYaw());

    swerve.drive(forward, strafe, azimuth);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    swerve.drive(0.0, 0.0, 0.0);
  }

  private double deadband(double value) {
    if (Math.abs(value) < DEADBAND) return 0.0;
    return value;
  }
}
