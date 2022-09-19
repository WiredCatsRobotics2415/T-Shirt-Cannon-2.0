package frc.subsystems;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter {
    //indicate left/right

    Solenoid[] solenoids;
    boolean autoCloseMode;
    int ticks = 0;
    Map<Integer, Integer> closeSolenioidMap;

    public Shooter() {
        solenoids = new Solenoid[4];
        solenoids[0] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.RIGHT_FIRE_SOLENOID_1);
        solenoids[1] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.RIGHT_FIRE_SOLENOID_2);
        solenoids[2] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.LEFT_FIRE_SOLENOID_1);
        solenoids[3] = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.LEFT_FIRE_SOLENOID_2);
        closeSolenioidMap = new HashMap<Integer, Integer>();
    }

    public void fireAll() {
        for (Solenoid solenoid : solenoids){
            //solenoid.set(true);
            solenoid.set(false);
        }
    }
    
    public void closeAll() {
        for (Solenoid solenoid : solenoids){
            //solenoid.set(false);
            solenoid.set(true);
        }
    }
    
    public void fire(int shooter) {
        solenoids[shooter].set(Constants.OPEN_SOLENOID_VALUE);
        if (this.autoCloseMode) {
            closeSolenioidMap.put(this.ticks + Constants.CLOSE_SOLENOID_AFTER_TICKS, shooter);
        }
    }
    
    public void close(int shooter) {
        if (!this.autoCloseMode) solenoids[shooter].set(!Constants.OPEN_SOLENOID_VALUE);
    }

    public boolean getSolenoidStatus(int shooter) {
        return solenoids[shooter].get();
    }

    public void setAutoCloseMode(boolean modeChoice) {
        autoCloseMode = modeChoice;
    }

    public boolean isAutoCloseMode() {
        return autoCloseMode;
    }

    public void periodic() {
        this.ticks++;
        if (!this.autoCloseMode) return;
        for (Map.Entry<Integer,Integer> entry : closeSolenioidMap.entrySet()) {
            if (this.ticks >= entry.getKey()) solenoids[entry.getValue()].set(true);
        }
    }
}
