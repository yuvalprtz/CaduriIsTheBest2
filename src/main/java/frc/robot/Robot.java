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
 * the code necessary to operate a robot with tank drive. //TODO REMOVE COMMENTS
 */
public class Robot extends TimedRobot {
  private final CommandXboxController xBox = new CommandXboxController(0);
  
  private final TalonSRX motorControlLeft = new TalonSRX(0); 
  private final TalonSRX motorControlRight = new TalonSRX(1);

  @Override
  public void teleopPeriodic() {
    final double inputX = xBox.getRightX();
    final double inputY = xBox.getLeftY();
    final double outputLeft = Math.max(Math.min(inputY + inputX, 1), -1);
    final double outputRight = Math.max(Math.min(inputY - inputX, 1), -1);
    
    motorControlLeft.set(ControlMode.PercentOutput, outputLeft); 
    motorControlRight.set(ControlMode.PercentOutput, outputRight);
  }
}
