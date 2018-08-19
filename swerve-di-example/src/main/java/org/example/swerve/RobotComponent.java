package org.example.swerve;

import dagger.BindsInstance;
import dagger.Component;
import java.net.URL;
import javax.inject.Singleton;
import org.example.swerve.controls.Controls;
import org.example.swerve.controls.GyroResetButton;
import org.strykeforce.thirdcoast.swerve.GraphableSwerveDriveModule;
import org.strykeforce.thirdcoast.swerve.GyroModule;
import org.strykeforce.thirdcoast.swerve.SwerveDrive;
import org.strykeforce.thirdcoast.swerve.WheelModule;
import org.strykeforce.thirdcoast.telemetry.NetworkModule;
import org.strykeforce.thirdcoast.telemetry.TelemetryService;

/** This interface configures dependency injection for the Robot. */
@Singleton
@Component(
  modules = {
    NetworkModule.class,
    GyroModule.class,
    WheelModule.class,
    GraphableSwerveDriveModule.class,
  }
)
interface RobotComponent {

  Controls controls();

  GyroResetButton gyroResetButton();

  SwerveDrive swerveDrive();

  TelemetryService telemetryService();

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder config(URL config);

    RobotComponent build();
  }
}
