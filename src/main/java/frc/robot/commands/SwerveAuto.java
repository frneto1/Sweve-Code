package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveWrapper;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.VisionFake;

public class SwerveAuto extends Command{

    public Vision vision;
    public VisionFake vFake = new VisionFake();
    Vision limelight;
    public SwerveWrapper swerveSubsystem;
    PIDController pid = new PIDController(0, 0, 0);
    public double erroX, erroY, erroRot;

    public SwerveAuto(Vision limelight, SwerveWrapper swerve){
        this.vision = limelight;
        this.swerveSubsystem = swerve;
        addRequirements(swerve);
}
    public void initialize(){
        Pose2d poseInicial = new Pose2d(1.5, 5.5, Rotation2d.fromDegrees(180));
        swerveSubsystem.zeroGyro();
        swerveSubsystem.resetOdometry(poseInicial);
    }
        @Override
        public void execute() {
        
            if (!vFake.hasTarget()) {
                swerveSubsystem.drive(0, 0, 1, false, true);
                return;
            }
        
            double x = vFake.getErroX();
            double y = vFake.getErroY();
            double rot = vFake.getErroRot();
        
            swerveSubsystem.drive(x, y, rot, false, true);
        }
    public void end(boolean interrupted){
        return;
    }

}
