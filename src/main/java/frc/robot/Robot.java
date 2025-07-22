// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode; //TODO: Useless imports
import com.ctre.phoenix.motorcontrol.can.TalonSRX; //TODO: Useless imports

import edu.wpi.first.util.sendable.SendableRegistry; //TODO: Useless imports
import edu.wpi.first.wpilibj.Joystick; //TODO: Useless imports
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController; //TODO: Useless imports
import edu.wpi.first.wpilibj.drive.DifferentialDrive; //TODO: Useless imports
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax; //TODO: Useless imports
import edu.wpi.first.wpilibj.motorcontrol.Talon; //TODO: Useless imports
import edu.wpi.first.wpilibj2.command.Commands; //TODO: Useless imports
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drive;


public class Robot extends TimedRobot {
  private final CommandXboxController xboxController = new CommandXboxController(0); 

  private final Drive drive = new Drive();
  
  public Robot() {
    drive.setDefaultCommand(drive.go(() -> xboxController.getLeftY(), () -> xboxController.getRightX()));
  }
} 
