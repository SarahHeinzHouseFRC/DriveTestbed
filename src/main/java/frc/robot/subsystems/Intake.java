package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/* 
 * very basic for now, wil be expanded when we add second roller + indexing
 */

public class Intake extends SubsystemBase {
    private MotorController motor;
    
    public Intake(MotorController motor) {
        this.motor = motor;
    }

    public void setPower(double power) {
        motor.set(power);
    }
}
