package org.example.swerve;

import dagger.BindsInstance;
import dagger.Component;
import java.net.URL;
import javax.inject.Singleton;
import org.example.swerve.controls.Controls;
import org.strykeforce.thirdcoast.swerve.GyroModule;
import org.strykeforce.thirdcoast.swerve.SwerveDrive;
import org.strykeforce.thirdcoast.swerve.WheelModule;

/** This interface configures dependency injection for the Robot. */
@Singleton
@Component(
  modules = {
    GyroModule.class,
    WheelModule.class,
  }
)
interface RobotComponent {

  Controls controls();

  SwerveDrive swerveDrive();

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder config(URL config);

    RobotComponent build();
  }
}
