package org.example;

import java.net.URL;

import javax.inject.Singleton;

import org.strykeforce.thirdcoast.swerve.GyroModule;
import org.strykeforce.thirdcoast.swerve.SwerveDrive;
import org.strykeforce.thirdcoast.swerve.WheelModule;
import org.strykeforce.thirdcoast.telemetry.NetworkModule;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = { NetworkModule.class, GyroModule.class, WheelModule.class })
public interface SingletonComponent {

    SwerveDrive swerveDrive();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder thirdCoastConfig(URL config);

        SingletonComponent build();
    }

}