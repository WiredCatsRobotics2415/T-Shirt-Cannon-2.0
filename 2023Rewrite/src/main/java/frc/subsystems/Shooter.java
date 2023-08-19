package frc.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
    //indicate left/right

    private Solenoid[] solenoids;
    private boolean autoCloseMode;

    public Shooter() {
        solenoids = new Solenoid[4];
        solenoids[0] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.RIGHT_FIRE_SOLENOID_1);
        solenoids[1] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.RIGHT_FIRE_SOLENOID_2);
        solenoids[2] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.LEFT_FIRE_SOLENOID_1);
        solenoids[3] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.LEFT_FIRE_SOLENOID_2);
    }

    public void fireAll() {
        for (Solenoid solenoid : solenoids){
            solenoid.set(Constants.OPEN_SOLENOID);
        }
    }
    
    public void closeAll() {
        for (Solenoid solenoid : solenoids){
            solenoid.set(Constants.CLOSE_SOLENOID);
        }
    }
    
    public void fire(int shooter) {
        solenoids[shooter].set(Constants.OPEN_SOLENOID);
        if (this.autoCloseMode) {
            SequentialCommandGroup scg = new SequentialCommandGroup(
                new WaitCommand((Constants.CLOSE_SOLENOID_AFTER_TICKS*20)/1000),
                new InstantCommand(() -> { this.solenoids[shooter].set(Constants.CLOSE_SOLENOID); }, this)
            );
            CommandScheduler.getInstance().schedule(scg);
        }
    }
    
    public void close(int shooter) {
        if (!this.autoCloseMode) solenoids[shooter].set(Constants.CLOSE_SOLENOID);
    }

    public boolean getSolenoidStatus(int shooter) {
        return !solenoids[shooter].get(); //! because true/false switched around
    }

    public void setAutoCloseMode(boolean modeChoice) {
        autoCloseMode = modeChoice;
    }

    public boolean isAutoCloseMode() {
        return autoCloseMode;
    }

    @Override
    public void periodic() {
        for (Solenoid s : solenoids) {
            SmartDashboard.putBoolean("Barrel " + s.getChannel(), !s.get());
        }
    }
}