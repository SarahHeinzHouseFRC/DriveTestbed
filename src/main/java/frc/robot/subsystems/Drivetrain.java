package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    MotorController leftMotors;
    MotorController rightMotors;

    /** Creates a new ExampleSubsystem. */
    public Drivetrain(MotorController leftMotors, MotorController rightMotors) {
        this.leftMotors = leftMotors;
        this.rightMotors = rightMotors;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void setPower(double left, double right) {
        leftMotors.set(left);
        rightMotors.set(right);
    }

    public void arcadeControlDeadzoned(double power, double turn) {
        if (Math.abs(power) < 0.1) power = 0;
        if (Math.abs(turn) < 0.1) turn = 0;
        setPower(power + turn, power - turn);
    }
}