# Swerve Example

This example implements the Third Coast Swerve drive in Java. It requires a NavX installed on the roboRIO MXP port.


**Note:** This robot **will not drive** until configured, see below.

## Configuration

1. Configure your preferred joystick controls in the `execute` method of `TeleOpDriveCommand`.
2. Configure Motion Magic closed-loop position parameters for your azimuth motors in `DriveSubsystem`.
3. Verify azimuth and drive motor encoder and current limits are appropriate for your motors in `DriveSubsystem`.

