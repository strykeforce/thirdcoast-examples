package org.example.swerve.controls;

import javax.inject.Inject;

public class GyroResetButton extends Trigger {

  private final Controls controls;

  @Inject
  public GyroResetButton(Controls controls) {
    this.controls = controls;
  }

  @Override
  public boolean get() {
    return controls.getResetButton();
  }

  @Override
  public String toString() {
    return "gyro reset button";
  }
}
