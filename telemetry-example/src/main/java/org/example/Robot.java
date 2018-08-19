package org.example;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import java.net.URL;
import org.strykeforce.thirdcoast.swerve.SwerveDrive;
import org.strykeforce.thirdcoast.util.ExpoScale;

public class Robot extends TimedRobot {


  private SwerveDrive drive;

  @Override
  public void robotInit() {
    URL thirdCoastConfig = Robot.class.getResource("/META-INF/thirdcoast.toml");
    SingletonComponent singletonComponent =
        DaggerSingletonComponent.builder().thirdCoastConfig(thirdCoastConfig).build();
    drive = singletonComponent.swerveDrive();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    // do something with Talons or SwerveDrive
  }
}
