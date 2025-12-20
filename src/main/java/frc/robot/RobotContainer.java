package frc.robot;

import frc.robot.commands.SwerveLoc;
import frc.robot.subsystems.SwerveWrapper;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {

  private Joystick bob = new Joystick(0);

  public SwerveWrapper swerve = new SwerveWrapper();

  public RobotContainer() {
    swerve.setDefaultCommand(new SwerveLoc(swerve, bob));
  }
  public Command getAutonomousCommand() {
    return null;
    }
}
