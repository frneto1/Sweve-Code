package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.SwerveLoc;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;

public class SwerveWrapper extends SubsystemBase{

    private SwerveDrive swerve;
    private Joystick bob = new Joystick(0);
    double x = bob.getRawAxis(0);
    double y = -bob.getRawAxis(1);
    double rot = bob.getRawAxis(4);

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

        Translation2d vel = new Translation2d(vx, vy);
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

}
