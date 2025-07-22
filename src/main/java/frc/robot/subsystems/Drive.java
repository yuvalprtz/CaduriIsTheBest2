package frc.robot.subsystems;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  private final double SPEED = 0.1;

  private final TalonSRX motorControlLeft = new TalonSRX(5); 
  private final TalonSRX motorControlRight = new TalonSRX(8);

  public Drive() {
    motorControlRight.setInverted(true);
  }

  public Command go(Supplier<Double> howMuchKadima, Supplier<Double> turn) {
    return this.run(() -> {
      final double outputLeft = Math.max(Math.min(howMuchKadima.get() + turn.get(), 1), -1);
      final double outputRight = -Math.max(Math.min(howMuchKadima.get() - turn.get(), 1), -1);
      
      motorControlLeft.set(ControlMode.PercentOutput, outputLeft * SPEED); 
      motorControlRight.set(ControlMode.PercentOutput, outputRight * SPEED);
    });
  }

  private Command createMovementCommand(double leftMotorDirection, double rightMotorDirection, double amplitude) {
    return this.run(() -> {
      motorControlLeft.set(ControlMode.PercentOutput, leftMotorDirection * amplitude); 
      motorControlLeft.set(ControlMode.PercentOutput, rightMotorDirection * amplitude);
    });
  }

  public Command goForward(double amplitude) {
    return createMovementCommand(1, 1, amplitude);
  }
  
  public Command goBackward(double amplitude) {
    return createMovementCommand(-1, -1, amplitude);
  }
  
  public Command turnLeft(double amplitude) {
    return createMovementCommand(-1, 1, amplitude);
  }
  
  public Command turnRight(double amplitude) {
    return createMovementCommand(1, -1, amplitude);
  }
}