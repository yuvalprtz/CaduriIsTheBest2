package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final double dt = 0.02;
    private final double epsilon = 0.05;

    private final TalonFX motor = new TalonFX(0);
    private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(2, 1, 2);
    private final PIDController feedback = new PIDController(1, 0, 0);
    private final StatusSignal<AngularVelocity> currentVelocity = motor.getVelocity();
    private final TrapezoidProfile profile = new TrapezoidProfile(new TrapezoidProfile.Constraints(1, 1));
    private TrapezoidProfile.State setpoint = new TrapezoidProfile.State(0, 0);

    public Command goToGoal(TrapezoidProfile.State goal) {
        return new FunctionalCommand(
            () -> setpoint = new TrapezoidProfile.State(motor.getPosition().getValueAsDouble(), motor.getVelocity().getValueAsDouble()),
            () -> {
                setpoint = profile.calculate(dt, setpoint, goal);

                final double feedforwardOutput = feedforward.calculate(setpoint.velocity);
                final double feedbackOutput = feedback.calculate(currentVelocity.getValueAsDouble(), setpoint.velocity);  
                final double voltage = feedforwardOutput + feedbackOutput;  

                motor.set(voltage);
            },
            null,
            () -> Math.abs(setpoint.position - goal.position) < epsilon,
            this
        );
    }
}
