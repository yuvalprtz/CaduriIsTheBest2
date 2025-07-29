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
    xboxController.leftTrigger().whileTrue(drive.goFwdBwd(() -> xboxController.getLeftY()));
    xboxController.rightTrigger().whileTrue(drive.turn(() -> xboxController.getRightX()));
    xboxController.a().onTrue(drive.goForwardThenRight());
  }

  public void teleopPeriodic() {
    sch.run();
  }
} 
