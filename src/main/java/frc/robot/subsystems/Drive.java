package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  private static final double SPEED = 0.2;
  private static final double TURN_SPEED = 0.3;

  private final TalonSRX motorLeft = new TalonSRX(5);  
  private final TalonSRX motorRight = new TalonSRX(8); 

  public Drive() {
    motorRight.setInverted(true);
  }

  public Command stopMotors() {
    return Commands.runOnce(() -> {
      motorLeft.set(ControlMode.PercentOutput, 0);
      motorRight.set(ControlMode.PercentOutput, 0);
    }, this);
  }

  public Command go(DoubleSupplier controllerXSupplier, DoubleSupplier controllerYSupplier) {
    return Commands.run(() -> { 
      final double controllerX = controllerXSupplier.getAsDouble() * TURN_SPEED;
      final double controllerY = controllerYSupplier.getAsDouble() * SPEED;

      final double outputLeft = MathUtil.clamp(controllerY + controllerX, -1, 1);
      final double outputRight = MathUtil.clamp(controllerY - controllerX, -1, 1);
      
      motorLeft.set(ControlMode.PercentOutput, outputLeft); 
      motorRight.set(ControlMode.PercentOutput, outputRight);
    }, this);
  }

  public Command translate(DoubleSupplier amplitude) {
    return go(() -> 0, amplitude);
  }

  public Command turn(DoubleSupplier amplitude) {
    return go(amplitude, () -> 0);
  }
}