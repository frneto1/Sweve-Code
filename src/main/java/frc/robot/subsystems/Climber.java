package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase{   

    public SparkMax motorClimb = new SparkMax(9, SparkMax.MotorType.kBrushless);

    public Climber() {
        SparkMaxConfig config = new SparkMaxConfig();
        config.idleMode(IdleMode.kBrake);
        config.inverted(false);

        motorClimb.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
}
