package frc.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;

public class Shooter {
    //indicate left/right
    DoubleSolenoid leftSolenoid; 
    DoubleSolenoid rightSolenoid; 

    public Shooter() {
        leftSolenoid = new DoubleSolenoid(RobotMap.LEFT_INTAKE_EXTEND, RobotMap.LEFT_INTAKE_RETRACT);
        rightSolenoid = new DoubleSolenoid(RobotMap.RIGHT_INTAKE_EXTEND, RobotMap.RIGHT_INTAKE_RETRACT);
    }

    public void shoot() {
        leftSolenoid.set(DoubleSolenoid.Value.kForward);  
        rightSolenoid.set(DoubleSolenoid.Value.kForward); 
    }
}
