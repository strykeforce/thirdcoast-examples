# thirdcoast-examples

Examples of Third Coast library use. See [thirdcoast](https://github.com/strykeforce/thirdcoast/) repo.

# The example projects

## swerve-example

Gradle project with an example of Third Coast swerve drive that drives in teleop.

## telemetry-example

Gradle project with a simple example of using Third Coast telemetry on a TalonSRX.

# Obsolete example projects

These example projects are out of date and no longer actively maintained.

In addition to other updates that have made them obsolete, they demonstrate our 2018 method of using the [Dagger](https://google.github.io/dagger/) framework to manage dependency injection (DI).  While DI is still useful and used by us, we find that manually managing DI provides a better learning experience for students during the season.


## swerve-dagger-example

This project demonstrates our 2018 method of configuring and using our Third Coast swerve drive. Various updates (see [talon-example](#talon-example)) have made this obsolete.


## talon-example

This project demonstrates our 2018 method of provisioning Talons using our Third Coast library. With the additions made to the CTRE Phoenix libraries during the summer of 2018, especially `TalonSRXConfiguration` and friends, we no longer need to maintain our separate library for this.
