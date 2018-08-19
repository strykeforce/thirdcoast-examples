# Third Coast Telemetry Robot Example

This is a quick-start example of using Third Coast Telemetry and Grapher client to instrument a Talon SRX. For a swerve drive example, see the `skippy` branch of this repository. If you have questions, see the [thirdcoast-users forum](https://groups.google.com/forum/#!forum/thirdcoast-users).

## Instructions

To run with the included [GradleRIO](https://github.com/Open-RIO/GradleRIO) configuration:

1. Edit `build.gradle` and change this line to your team number:

  ```
  def TEAM = 2767 // edit for your team
  ```

2. Go to this project directory in a terminal window with the JDK on the path.

3. Compile the project with `gradlew build` (Windows) or `./gradlew build` (macOS/Linux).

4. Deploy to robot with `gradlew deploy` (Windows) or `./gradlew deploy` (macOS/Linux).

## Dependencies

This project has the following Maven dependencies. Alternatively, you can manually download the Third Coast library JAR file from [Bintray](https://bintray.com/strykeforce/maven/thirdcoast).

### Required Maven Repositories

- jCenter or Maven Central
- https://oss.sonatype.org/content/repositories/snapshots

See `build.gradle` for example usage.

### Jar Files

See `build.gradle` for current versions.

- org.strykeforce.thirdcoast:core:VERSION
- 'org.slf4j:slf4j-simple:VERSION', optional for logging
