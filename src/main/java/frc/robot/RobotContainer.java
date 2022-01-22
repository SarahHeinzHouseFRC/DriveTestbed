// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  int[] leftIDs = {-5, -11, -37};
  int[] rightIDs = {7, 9, 14};
  private final Drivetrain drivetrain = new Drivetrain(leftIDs, rightIDs);
  private final DoubleSolenoid shifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
  private final Intake intake = new Intake(15);

  XboxController stick = new XboxController(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }
  
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    drivetrain.setDefaultCommand(
      new InstantCommand(
        () -> drivetrain.arcadeControlDeadzoned(-stick.getLeftY(), stick.getRightX()), 
        drivetrain));

    new JoystickButton(stick, Button.kA.value)
      .whenPressed(new InstantCommand(() -> shifter.set(kForward)))
      .whenReleased(new InstantCommand(() -> shifter.set(kOff)));

    new JoystickButton(stick, Button.kB.value)
      .whenPressed(new InstantCommand(() -> shifter.set(kReverse)))
      .whenReleased(new InstantCommand(() -> shifter.set(kOff)));

    intake.setDefaultCommand(
      new InstantCommand(() -> intake.setPower(stick.getLeftTriggerAxis()), intake));

    new JoystickButton(stick, Button.kRightBumper.value)
      .whenPressed(new InstantCommand(() -> intake.setPower(-1), intake))
      .whenReleased(new InstantCommand(() -> intake.setPower(0), intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class. 
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
