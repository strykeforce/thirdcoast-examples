package frc.team2767.customitemexample.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2767.customitemexample.Robot;
import org.jetbrains.annotations.NotNull;
import org.strykeforce.thirdcoast.telemetry.grapher.Measure;
import org.strykeforce.thirdcoast.telemetry.item.Item;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.DoubleSupplier;

public class GraphableSubsystem extends Subsystem implements Item {

  private int counter; // simulated value to graph

  public GraphableSubsystem() {
    Robot.TELEMETRY.register(this); // register subsystem with telemetry system
  }

  public void incrementCounter() {
    counter++;
  }

  // Subsystem implementation

  @Override
  protected void initDefaultCommand() {}

  // Item implementation

  @NotNull
  @Override
  public String getDescription() {
    return "Graphable Subsystem";
  }

  @NotNull
  @Override
  public Set<Measure> getMeasures() {
    Set<Measure> measures = new HashSet<>(Arrays.asList(Measure.ANGLE, Measure.RANGE));
    return Collections.unmodifiableSet(measures);
  }

  @NotNull
  @Override
  public DoubleSupplier measurementFor(@NotNull Measure measure) {
    switch (measure) {
      case ANGLE:
        return () -> counter % 360;
      case RANGE:
        return () -> counter;
      default:
        throw new IllegalStateException();
    }
  }

  // define the type of graphable item, if we define multiple items of type "subsystem", then return
  // a unigue device ID for each from getDeviceId().

  @NotNull
  @Override
  public String getType() {
    return "subsystem";
  }

  @Override
  public int getDeviceId() {
    return 0;
  }

  // sort by type, then device id
  @Override
  public int compareTo(@NotNull Item other) {
    int result = getType().compareTo(other.getType());
    if (result != 0) return result;
    else return Integer.compare(getDeviceId(), other.getDeviceId());
  }
}
