// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private final CommandXboxController XBox = new CommandXboxController(0);
  private final TalonSRX motorcontrolLeft = new TalonSRX(0);
  private final TalonSRX motorcontrolRight = new TalonSRX(1);

  @Override
  public void teleopPeriodic() {
    // yuval's very awsome code:
    double inputX, inputY, outputLeft, outputRight;
    inputX = XBox.getRightX();
    inputY = XBox.getLeftY();
    outputLeft = Math.max(Math.min(inputY + inputX, 1), -1);
    outputRight = Math.max(Math.min(inputY - inputX, 1), -1);
    
    motorcontrolLeft.set(ControlMode.PercentOutput, outputLeft);
    motorcontrolRight.set(ControlMode.PercentOutput, outputRight);
  }
}
