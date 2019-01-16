package frc.team2767.swerveexample;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team2767.swerveexample.control.Controls;
import frc.team2767.swerveexample.subsystem.DriveSubsystem;
import java.util.Date;

public class Robot extends TimedRobot {

  public static final DriveSubsystem DRIVE = new DriveSubsystem();

  // Controls initialize Commands so this should be instantiated last to prevent
  // NullPointerExceptions in commands that require() Subsystems above.
  public static final Controls CONTROLS = new Controls();

  @Override
  public void robotInit() {
    System.out.println("Today is " + new Date().toString());
    DRIVE.zeroAzimuthEncoders();
    DRIVE.zeroGyro();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
