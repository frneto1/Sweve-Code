package frc.robot;

import frc.robot.commands.SwerveAuto;
import frc.robot.commands.SwerveLoc;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.SwerveWrapper;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {

  private PS5Controller ps5 = new PS5Controller(1);

  public SwerveWrapper swerve = new SwerveWrapper();
  public Climber climber = new Climber();
  public Vision limelight = new Vision("null");  
  public SwerveAuto swerveAuto = new SwerveAuto(limelight, swerve);

  public RobotContainer() {
    swerve.setDefaultCommand(new SwerveLoc(swerve, ps5, swerveAuto, climber));
    getAutonomousCommand();
  }
  public Command getAutonomousCommand() {
    return swerveAuto;
    }
}
