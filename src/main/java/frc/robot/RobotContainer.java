// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainShifter;
// import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  int[] leftIDs = {-5, -37, -11};
  int[] rightIDs = {7, 9, 14};
  private final Drivetrain drivetrain = new Drivetrain(leftIDs, rightIDs);
  private final DrivetrainShifter shifter = new DrivetrainShifter(0, 1);

  Joystick stick = new Joystick(0);

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
      new RunCommand(() -> drivetrain.arcadeControlDeadzoned(-stick.getRawAxis(1), stick.getRawAxis(4)), 
      drivetrain));

    new JoystickButton(stick, Button.kA.value)
      .whenPressed(new InstantCommand(() -> shifter.shift(kForward), shifter))
      .whenReleased(new InstantCommand(() -> shifter.shift(kOff), shifter));

    new JoystickButton(stick, Button.kB.value)
      .whenPressed(new InstantCommand(() -> shifter.shift(kReverse), shifter))
      .whenReleased(new InstantCommand(() -> shifter.shift(kOff), shifter));
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
