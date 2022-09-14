package frc.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;

public class Shooter {
    //indicate left/right

    Solenoid[] solenoids;

    public Shooter() {
        solenoids = new Solenoid[4];
        solenoids[0] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.RIGHT_FIRE_SOLENOID_1);
        solenoids[1] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.RIGHT_FIRE_SOLENOID_2);
        solenoids[2] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.LEFT_FIRE_SOLENOID_1);
        solenoids[3] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.LEFT_FIRE_SOLENOID_2);
    }

    public void fireAll() {
        for (Solenoid solenoid : solenoids){
            solenoid.set(true);
        }
    }
    
    public void closeAll() {
        for (Solenoid solenoid : solenoids){
            solenoid.set(false);
        }
    }
    
    public void fire(int shooter) {
        solenoids[shooter].set(true);
    }
    
    public void close(int shooter) {
        solenoids[shooter].set(false);
    }
}
