package org.example;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import org.jetbrains.annotations.NotNull;
import org.strykeforce.thirdcoast.telemetry.TelemetryController;
import org.strykeforce.thirdcoast.telemetry.TelemetryService;
import org.strykeforce.thirdcoast.telemetry.grapher.ClientHandler;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Robot extends TimedRobot {

  private static final int CLIENT_PORT = 5801; // grapher client listens on this
  private static final int SERVER_PORT = 5800; // roborio listens on this

  private TalonSRX talon = new TalonSRX(1);
  private TelemetryService telemetryService;

  @Override
  public void robotInit() {
    telemetryService = getTelemetryService();
    telemetryService.register(talon);
    telemetryService.start();
  }

  @NotNull
  private TelemetryService getTelemetryService() {
    // Create a TelemetryService using manual dependency injection.
    //
    // TelemetryService is instantiated with a TelemetryController factory functional interface:
    //   inventory -> new TelemetryController
    //
    // The inventory is managed internally by the TelemetryService and updated when you register
    // talons, etc.
    //
    // The injected TelemetryController class listens for HTTP requests from telemetry clients.
    // The injected ClientHandler class provides the UDP data stream to the telemetry client. One
    // client at at time is supported for streaming.
    // The injected DatagramSocket is the source socket that datagrams are sent through.

    return new TelemetryService(
        inventory -> {
          DatagramSocket datagramSocket; // grr checked exceptions...
          try {
            datagramSocket = new DatagramSocket();
          } catch (SocketException se) {
            throw new RuntimeException(se);
          }
          return new TelemetryController(
              inventory, new ClientHandler(CLIENT_PORT, datagramSocket), SERVER_PORT);
        });
  }

  @Override
  public void teleopPeriodic() {
    talon.set(ControlMode.PercentOutput, 0.0);
  }
}
