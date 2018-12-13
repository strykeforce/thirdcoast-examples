package frc.team2767.customitemexample;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.team2767.customitemexample.subsystem.GraphableSubsystem;
import org.strykeforce.thirdcoast.telemetry.TelemetryController;
import org.strykeforce.thirdcoast.telemetry.TelemetryService;

public class Robot extends TimedRobot {

  public static final TelemetryService TELEMETRY = new TelemetryService(TelemetryController::new);

  // this example subsystem will register itself with the telemetry system
  public static final GraphableSubsystem GRAPHABLE_SUBSYSTEM = new GraphableSubsystem();

  @Override
  public void robotInit() {
    TELEMETRY.start(); // start the telemetry system once all items are added
  }

  @Override
  public void disabledPeriodic() {
    GRAPHABLE_SUBSYSTEM.incrementCounter();
  }
}
