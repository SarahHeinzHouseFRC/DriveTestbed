package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.Compressor;

public class Pneumatics extends SubsystemBase {
    Compressor compressor;

    public Pneumatics() {
        compressor = new Compressor();
    }
    
    public void setCompressor(boolean enabled) {
        compressor.setClosedLoopControl(enabled);
    }
}
