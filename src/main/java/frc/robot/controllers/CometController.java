package frc.robot.controllers;

import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public abstract class CometController {
    final CommandGenericHID controller;

  CometController(CommandGenericHID controller) {
    this.controller = controller;
  }

  public CommandGenericHID getHid() {
    return this.controller;
  }

  public abstract double getLeftY();

  public abstract double getLeftX();

  public abstract double getRightY();

  public abstract double getRightX();

  public abstract double getLeftTriggerAxis();

  public abstract double getRightTriggerAxis();

  public abstract Trigger a();

  public abstract Trigger b();

  public abstract Trigger x();

  public abstract Trigger y();

  public abstract Trigger leftBumper();

  public abstract Trigger rightBumper();

  public abstract Trigger leftMenu();

  public abstract Trigger rightMenu();

  public abstract Trigger leftStick();

  public abstract Trigger rightStick();

  public abstract Trigger leftTrigger();

  public abstract Trigger rightTrigger();

  public abstract Trigger up();

  public abstract Trigger left();

  public abstract Trigger down();

  public abstract Trigger right();
}
