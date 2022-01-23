package frc.robot;

import java.util.ArrayList;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Helpers {
    public static MotorControllerGroup talonGroup(int... ids) {
        ArrayList<WPI_TalonSRX> controllers = new ArrayList<WPI_TalonSRX>();
        for (int id : ids) {
            WPI_TalonSRX controller = new WPI_TalonSRX(Math.abs(id));
            controller.setInverted(id < 0);
            controllers.add(controller);
        }
        return new MotorControllerGroup(controllers.toArray(new WPI_TalonSRX[0]));
    }
    public static MotorControllerGroup sparkGroup(MotorType type, int... ids) {
        ArrayList<CANSparkMax> controllers = new ArrayList<CANSparkMax>();
        for (int id : ids) {
            CANSparkMax controller = new CANSparkMax(Math.abs(id), type);
            controller.setInverted(id < 0);
            controllers.add(controller);
        }
        return new MotorControllerGroup(controllers.toArray(new CANSparkMax[0]));
    }
}
