// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Drivetrain drivetrain = new Drivetrain(Helpers.talonGroup(-5, -11, -37), Helpers.talonGroup(7, 9, 14));
  private final Intake intake = new Intake(Helpers.sparkGroup(MotorType.kBrushless, 15));
  private final DoubleSolenoid shifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

  XboxController stick = new XboxController(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureBindings();
  }
  
  /**
   * Use this method to define your command mappings. 
   */
  private void configureBindings() {
    drivetrain.setDefaultCommand(
      new RunCommand(
        () -> drivetrain.arcadeControlDeadzoned(-stick.getLeftY(), stick.getRightX()), 
        drivetrain));

    new JoystickButton(stick, Button.kA.value)
      .whenPressed(new InstantCommand(() -> shifter.set(kForward)))
      .whenReleased(new InstantCommand(() -> shifter.set(kOff)));

    new JoystickButton(stick, Button.kB.value)
      .whenPressed(new InstantCommand(() -> shifter.set(kReverse)))
      .whenReleased(new InstantCommand(() -> shifter.set(kOff)));

    intake.setDefaultCommand(
      new RunCommand(() -> intake.setPower(stick.getRightTriggerAxis()), intake));

    new JoystickButton(stick, Button.kRightBumper.value)
      .whileHeld(new RunCommand(() -> intake.setPower(-1), intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class. 
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
