package org.example;

import dagger.BindsInstance;
import dagger.Component;
import org.strykeforce.thirdcoast.talon.Talons;

import javax.inject.Singleton;
import java.net.URL;

@Singleton
@Component
public interface SingletonComponent {

  Talons talons();

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder thirdCoastConfig(URL config);

    SingletonComponent build();
  }
}
