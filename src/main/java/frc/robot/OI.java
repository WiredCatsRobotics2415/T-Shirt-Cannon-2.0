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
        double contValue = this.controller.getRawAxis(4);
        if (contValue > 0) {
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
