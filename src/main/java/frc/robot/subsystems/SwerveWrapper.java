package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;

public class SwerveWrapper extends SubsystemBase{

    private SwerveDrive swerve;
    private PS5Controller ps5 = new PS5Controller(1);
    double x = ps5.getLeftX();
    double y = -ps5.getLeftY();
    double rot = ps5.getRightX();
    public double speed;

    public SwerveWrapper(){
        File yagslDir = new File(Filesystem.getDeployDirectory(),"yagsl");

        try {
            swerve = new SwerveParser(yagslDir).createSwerveDrive(Constants.MAX_SPEED);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
}
    public void drive(double x, double y, double rot, boolean fieldRelative, boolean isOpenLoop){
        double vx = x * Constants.MAX_SPEED;
        double vy = y * Constants.MAX_SPEED;
        
        double vrot = rot * Constants.MAX_ANGULAR_SPEED;

        Translation2d vel = new Translation2d(vy, vx);
        swerve.drive(vel, vrot, fieldRelative, isOpenLoop);
    }

    public void getPose(){
        swerve.getPose();
    }
    public void resetOdometry(Pose2d pose){
        swerve.resetOdometry(pose);
    }
    public void zeroGyro(){
        swerve.zeroGyro();
    }
    public SwerveModuleState[] getModuleStates() {
        return swerve.getStates();
    }
    public void setModuleStates(SwerveModuleState[] states, boolean isOpenLoop) {
        swerve.setModuleStates(states, isOpenLoop);
    }
    public void conversor(){
        ChassisSpeeds velocities = swerve.getRobotVelocity();
        speed = Math.sqrt(Math.pow(velocities.vxMetersPerSecond, 2) + Math.pow(velocities.vyMetersPerSecond, 2));
    }
}
