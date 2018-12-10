package org.example;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import org.strykeforce.thirdcoast.telemetry.TelemetryController;
import org.strykeforce.thirdcoast.telemetry.TelemetryService;

public class Robot extends TimedRobot {

  private TalonSRX talon = new TalonSRX(1);
  private TelemetryService telemetryService = new TelemetryService(TelemetryController::new);

  @Override
  public void robotInit() {
    telemetryService.register(talon);
    telemetryService.start();
  }

  @Override
  public void teleopPeriodic() {
    talon.set(ControlMode.PercentOutput, 0.0);
  }
}
