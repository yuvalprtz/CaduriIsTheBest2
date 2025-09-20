package frc.robot.subsystems;

import java.util.Properties;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
    private final TalonFX motor = new TalonFX(0);
    private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(2, 1, 2);
    private final PIDController feedback = new PIDController(1, 0, 0);
    private final StatusSignal<AngularVelocity> currentVelocity = motor.getVelocity();

    public Command motorToVelocity(double velocity) {  
        final double feedforwardOutput = feedforward.calculate(velocity); 

        return Commands.run(() -> setVelocity(velocity, feedforwardOutput));
    }

    private void setVelocity(double velocity, double feedforwardOutput) {
        final double feedbackOutput = feedback.calculate(currentVelocity.getValueAsDouble(), velocity);  
        final double voltage = feedforwardOutput + feedbackOutput;  

        motor.set(voltage);
    }
}
