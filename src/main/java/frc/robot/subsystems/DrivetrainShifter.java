package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainShifter extends SubsystemBase {
    DoubleSolenoid shifter;

    /** Creates a new ExampleSubsystem. */
    public DrivetrainShifter(int highSpeedSolenoid, int lowSpeedSolenoid) {
        shifter = new DoubleSolenoid(highSpeedSolenoid, lowSpeedSolenoid);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void shift(DoubleSolenoid.Value value) {
        shifter.set(value);
    }
}