package frc.robot.subsystems;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  private static final double SPEED = 0.2;

  private final TalonSRX motorLeft = new TalonSRX(5);  
  private final TalonSRX motorRight = new TalonSRX(8); 

  public Drive() {
    motorRight.setInverted(true);
  }

  public Command go(DoubleSupplier howMuchForward, DoubleSupplier howMuchRight) {
    return Commands.run(() -> { 
      final double outputLeft = Math.max(Math.min(howMuchForward.getAsDouble() + howMuchRight.getAsDouble(), 1), -1);
      final double outputRight = -Math.max(Math.min(howMuchForward.getAsDouble() - howMuchRight.getAsDouble(), 1), -1);
      
      motorLeft.set(ControlMode.PercentOutput, outputLeft * SPEED); 
      motorRight.set(ControlMode.PercentOutput, outputRight * SPEED);
    }, this);
  }
}