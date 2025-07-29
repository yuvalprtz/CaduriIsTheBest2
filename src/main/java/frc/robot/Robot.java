// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drive;


public class Robot extends TimedRobot {
 CommandScheduler sch = CommandScheduler.getInstance();
 
  private final CommandXboxController xboxController = new CommandXboxController(0); 

  private final Drive drive = new Drive();
  
  public Robot() {
    xboxController.leftTrigger().whileTrue(drive.translate(() -> xboxController.getLeftY()).andThen(drive.stopMotors()));
    xboxController.rightTrigger().whileTrue(drive.turn(() -> xboxController.getRightX()).andThen(drive.stopMotors()));
    xboxController.a().whileTrue(drive.translate(() -> 1).withTimeout(2).andThen(drive.turn(() -> 1).withTimeout(1)).andThen(drive.stopMotors()));
  }

  public void teleopPeriodic() {
    sch.run();
  }
} 
