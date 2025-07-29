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

  private final TalonSRX motorLeft = new TalonSRX(5);  
  private final TalonSRX motorRight = new TalonSRX(8); 

  public Drive() {
    motorRight.setInverted(true);
  }

  public Command go(DoubleSupplier controllerXSupplier, DoubleSupplier controllerYSupplier) {
    return Commands.run(() -> { 
      final double controllerX = controllerXSupplier.getAsDouble();
      final double controllerY = controllerYSupplier.getAsDouble();

      final double outputLeft = MathUtil.clamp(controllerY + controllerX, -1, 1);
      final double outputRight = MathUtil.clamp(controllerY - controllerX, -1, 1);
      
      motorLeft.set(ControlMode.PercentOutput, outputLeft * SPEED); 
      motorRight.set(ControlMode.PercentOutput, outputRight * SPEED);
    }, this);
  }
}