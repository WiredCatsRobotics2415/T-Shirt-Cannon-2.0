package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
public class OI {
    private XboxController controller;
    private final double DEADBAND = 0.05;

    public OI() {
        controller = new XboxController(0);
    }

    public double getThrottle() {
        if (Math.abs(this.controller.getRawAxis(1)) < DEADBAND) {
            return 0; 
        } else {
            return this.controller.getRawAxis(1);
        }
    }

    public double getRotation(){
        //reason for odd code here:
        //as of nov 14 22, the right side of the robot (relative to the back) has an extremly sensitive rotation.
        //Therefore, if the controller is rotating right, then divide the value by 2 to desensitize it.
        //It seems to be equal to the other side, but could use some tuning.
        double contValue = this.controller.getRawAxis(4);
        if (contValue > DEADBAND) { 
            return this.controller.getRawAxis(4)/2;
        } else {
            if (Math.abs(this.controller.getRawAxis(4)) < DEADBAND) {
                return 0;
            } else {
                return this.controller.getRawAxis(4);
            }
        }
    }

    public boolean getShooterButton(int button) {
        return this.controller.getRawButtonPressed(button);
    }
}
