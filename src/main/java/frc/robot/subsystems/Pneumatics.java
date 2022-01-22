package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Pneumatics extends SubsystemBase {
    Compressor compressor;

    public Pneumatics() {
        compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    }
    
    public void setCompressor(boolean enable) {
        if (enable) {
            compressor.enableDigital();
        } else {
            compressor.disable();
        }
    }
}
