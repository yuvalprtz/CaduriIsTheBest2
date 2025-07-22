// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drive;


public class Robot extends TimedRobot {
  private final CommandXboxController xboxController = new CommandXboxController(0); 

  private final Drive drive = new Drive();
  
  public Robot() {
    drive.setDefaultCommand(drive.go(() -> xboxController.getLeftY(), () -> xboxController.getRightX()));
  }
} 
