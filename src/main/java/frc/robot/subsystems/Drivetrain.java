package frc.robot.subsystems;

import java.util.ArrayList;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class Drivetrain extends SubsystemBase {
    ArrayList<TalonSRX> leftMotors = new ArrayList<TalonSRX>();
    ArrayList<TalonSRX> rightMotors = new ArrayList<TalonSRX>();

    /** Creates a new ExampleSubsystem. */
    public Drivetrain(int[] leftIDs, int[] rightIDs) {
        for (int id : leftIDs) {
            leftMotors.add(createMotorConfigured(id));
        }
        for (int id : rightIDs) {
            rightMotors.add(createMotorConfigured(id));
        }
    }

    private TalonSRX createMotorConfigured(int id) {
        TalonSRX motor = new TalonSRX(Math.abs(id));
        motor.setInverted(id < 0);
        return motor;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void setPower(double left, double right) {
        for (TalonSRX motor : leftMotors) {
            motor.set(TalonSRXControlMode.PercentOutput, left);
        }
        for (TalonSRX motor : rightMotors) {
            motor.set(TalonSRXControlMode.PercentOutput, right);
        }
    }

    public void arcadeControlDeadzoned(double power, double turn) {
        if (Math.abs(power) < 0.1) power = 0;
        if (Math.abs(turn) < 0.1) turn = 0;
        setPower(power + turn, power - turn);
    }
}