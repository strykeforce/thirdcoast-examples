package org.example;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import java.net.URL;

public class Robot extends TimedRobot {

  private TalonSRX talon;

  @Override
  public void robotInit() {
    URL thirdCoastConfig = Robot.class.getResource("/META-INF/thirdcoast.toml");
    SingletonComponent singletonComponent =
        DaggerSingletonComponent.builder().thirdCoastConfig(thirdCoastConfig).build();
    talon = singletonComponent.talons().getTalon(1);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    //    talon.set(ControlMode.PercentOutput, 0.25);
  }
}
