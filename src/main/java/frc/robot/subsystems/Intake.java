package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
    private CANSparkMax motor;
    
    public Intake(int id) {
        motor = new CANSparkMax(id, MotorType.kBrushless);
    }

    public void setPower(double power) {
        motor.set(power);
    }
}
