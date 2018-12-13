package frc.team2767.customitemexample.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2767.customitemexample.Robot;
import org.jetbrains.annotations.NotNull;
import org.strykeforce.thirdcoast.telemetry.grapher.Measure;
import org.strykeforce.thirdcoast.telemetry.item.Item;

import java.util.Collections;
import java.util.EnumSet;
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

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Item interface implementation controls the graphing of this subsystem
  //////////////////////////////////////////////////////////////////////////////////////////////////

  // This is the name of the subsystem or device  graphable item that the client presents to the
  // user.

  @NotNull
  @Override
  public String getDescription() {
    return "Graphable Subsystem";
  }

  // Each graphable item has a type that identifies the measurements that it presents for graphing.
  // The type is a unique string that is not presented to users by the client. We use class name in
  // this example.

  @NotNull
  @Override
  public String getType() {
    return this.getClass().getName();
  }

  // In cases where there are multiple instances for a graphable item, for example, multiple Talons,
  // then each has an unique device id. We use zero in this example since there will only be one
  // copy of the subsystem.

  @Override
  public int getDeviceId() {
    return 0;
  }

  // Select the type of measured values from the Measure enumeration. There are a number of device
  // (e.g. Talon) and generic measurements available - select measurements that have the most
  // appropriate description for the client to present.

  @NotNull
  @Override
  public Set<Measure> getMeasures() {
    Set<Measure> measures = EnumSet.of(Measure.ANGLE, Measure.RANGE);
    return Collections.unmodifiableSet(measures);
  }

  // When the user has made their choice of measurements and starts the client, the telemetry system
  // creates a subscription internally that caches DoubleSuppliers that are supplied by this method.
  // The DoubleSuppliers are invoked in a separate thread periodically (currently every 5 ms) to
  // send data to the client so be aware of and mitigate any side-effects that might give you
  // concurrency issues during grapher client usage.

  @NotNull
  @Override
  public DoubleSupplier measurementFor(@NotNull Measure measure) {
    switch (measure) {
      case ANGLE:
        return () -> counter % 360;
      case RANGE:
        return () -> counter;
      default:
        throw new IllegalStateException(measure.toString());
    }
  }

  // The comparable interface implementation controls how this graphable item is sorted for the user
  // in the client list of graphable devices and subsystems. The code below is typical - first sort
  // by description, then sort by device ID. If you do not sort, then the items will returned to the
  // client in random order across robot restarts which will interfere with keeping client settings
  // per device or subsystem graphable item.

  @Override
  public int compareTo(@NotNull Item other) {
    int result = getDescription().compareTo(other.getDescription());
    if (result != 0) return result;
    else return Integer.compare(getDeviceId(), other.getDeviceId());
  }
}
