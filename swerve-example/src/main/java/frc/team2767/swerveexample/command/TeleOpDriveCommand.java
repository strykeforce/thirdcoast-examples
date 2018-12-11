package frc.team2767.swerveexample.command;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2767.swerveexample.Robot;
import frc.team2767.swerveexample.subsystem.DriveSubsystem;

import static org.strykeforce.thirdcoast.swerve.SwerveDrive.DriveMode.TELEOP;

public final class TeleOpDriveCommand extends Command {

  private final DriveSubsystem drive = Robot.DRIVE;

  public TeleOpDriveCommand() {
    requires(drive);
  }

  @Override
  protected void initialize() {
    drive.setDriveMode(TELEOP);
  }

  @Override
  protected void execute() {
    // TODO: configure joystick controls
    System.out.println("controls are not configured - robot will not drive!");

    double forward = 0.0;
    double strafe = 0.0;
    double azimuth = 0.0;
    drive.drive(forward, strafe, azimuth);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    drive.drive(0.0, 0.0, 0.0);
  }
}
