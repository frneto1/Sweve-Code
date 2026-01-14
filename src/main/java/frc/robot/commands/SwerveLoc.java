package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.SwerveWrapper;
import swervelib.SwerveDrive;

public class SwerveLoc extends Command {

    private PS5Controller ps5 = new PS5Controller(1); 
    public SwerveWrapper swerveSubsystem;
    public SwerveAuto swerveAuto;
    public SwerveDrive swerve;
    public Climber climber;
    double x, y, rot;
    double vx, vy, vrot;

    public SwerveLoc(SwerveWrapper swerve, PS5Controller ps5, SwerveAuto swerveAuto, Climber climber) {
        this.swerveSubsystem = swerve;
        this.ps5 = ps5;
        this.swerveAuto = swerveAuto;
        this.climber = climber;
        addRequirements(swerveSubsystem);
    }
    public void initialize() {
        Pose2d poseInicial = new Pose2d(1.5, 5.5, Rotation2d.fromDegrees(180));
        swerveSubsystem.zeroGyro();
        swerveSubsystem.resetOdometry(poseInicial);
    }

    public void execute() {

        x = -ps5.getLeftX();
        y = -ps5.getLeftY();
        rot = ps5.getRightX();

        x = Math.abs(x) < 0.05 ? 0 : ps5.getLeftX();
        y = Math.abs(y) < 0.05 ? 0 : ps5.getLeftY();
        rot = Math.abs(rot) < 0.05 ? 0 : ps5.getRightX();

        swerveSubsystem.conversor();

        swerveSubsystem.drive(x, -y, rot, false, true);
        ControlClimber();
    }

    public void end(boolean interrupted) {
        return;
    }

    public void dash(){

        SmartDashboard.putNumber("Rot", rot);
        SmartDashboard.putNumber("X", x);
        SmartDashboard.putNumber("Y", y);
        SmartDashboard.putNumber("% Velocidade", swerveSubsystem.speed * 33);
        SmartDashboard.putNumber("m/s", swerveSubsystem.speed);

    }

    public void ControlClimber(){
    if (ps5.getPOV() == 0){
        climber.motorClimb.set(1);
    } 
    else if (ps5.getPOV() == 180){
        climber.motorClimb.set(-1);
    }
    else {
        climber.motorClimb.set(0);
    }
}
}
