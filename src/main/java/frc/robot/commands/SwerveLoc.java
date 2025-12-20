package frc.robot.commands;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveWrapper;
import swervelib.SwerveDrive;

public class SwerveLoc extends Command {

    public SwerveWrapper swerveSubsystem;
    public SwerveDrive swerve;
    public Joystick bob = new Joystick(0);
    double x, y, rot;
    double vx, vy, vrot;

    public SwerveLoc(SwerveWrapper swerve, Joystick bob) {
        this.swerveSubsystem = swerve;
        this.bob = bob;
        addRequirements(swerve);
    }
    public void initialize() {
        Pose2d poseInicial = new Pose2d(1.5, 5.5, Rotation2d.fromDegrees(180));
        swerveSubsystem.zeroGyro();
        swerveSubsystem.resetOdometry(poseInicial);
    }

    public void execute() {
        x = bob.getRawAxis(0);
        y = -bob.getRawAxis(1);
        rot = bob.getRawAxis(4);

        x = Math.abs(x) < 0.04 ? 0 : bob.getRawAxis(0);
        y = Math.abs(y) < 0.04 ? 0 : bob.getRawAxis(1); 
        rot = Math.abs(rot) < 0.04 ? 0 : bob.getRawAxis(4);

        swerveSubsystem.drive(x, -y, rot, true, true);
        dash();
    }

    public void end(boolean interrupted) {
        return;
    }

    public void dash(){
        SmartDashboard.putBoolean("A", bob.getRawButton(1)); 
    }
}