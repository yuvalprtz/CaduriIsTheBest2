package frc.robot.subsystems;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  private static final double SPEED = 0.2;

  private final TalonSRX motorControlLeft = new TalonSRX(5); 
  private final TalonSRX motorControlRight = new TalonSRX(8);

  public Drive() {
    motorControlRight.setInverted(true);
  }

  public Command go(Supplier<Double> howMuchKadima, Supplier<Double> howMuchLatsida) {
    return this.run(() -> { 
      final double outputLeft = Math.max(Math.min(howMuchKadima.get() + howMuchLatsida.get(), 1), -1);
      final double outputRight = -Math.max(Math.min(howMuchKadima.get() - howMuchLatsida.get(), 1), -1);
      
      motorControlLeft.set(ControlMode.PercentOutput, outputLeft * SPEED); 
      motorControlRight.set(ControlMode.PercentOutput, outputRight * SPEED);
    });
  }
}