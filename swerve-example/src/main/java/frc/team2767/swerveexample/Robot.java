package frc.team2767.swerveexample;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team2767.swerveexample.subsystem.DriveSubsystem;

public class Robot extends TimedRobot {

  public static final DriveSubsystem DRIVE = new DriveSubsystem();

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
