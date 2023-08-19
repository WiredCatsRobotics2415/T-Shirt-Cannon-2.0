package frc.robot;

import java.util.Map;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.utils.RobotPreferences;

public class OIs {
    public static abstract class OI {
        //PROPERTIES
        public double DEADBAND;

        public Map<String, JoystickButton> binds;

        //JOYSTICKS
        public abstract double getThrottle();

        public abstract double getRotation();

        public abstract double getLeft();

        public abstract double getRight();

        //UTILS
        public abstract void setInputPrefrences();
    }
    
    public static class LogitechController extends OI {
        XboxController controller;

        private int curve;

        public void setInputPrefrences() {
            //Curve
            curve = RobotPreferences.getCurvePower();
            if (curve < 1) curve = 1; //Note: must include because fractional/negative powers will yield uncontrolable results
            System.out.println("OI: Using Curve at " + curve + " power.");
        }

        public LogitechController() {
            controller = new XboxController(0);
            binds = Map.of(
                "Barrel 1", new JoystickButton(controller, 1), // X Y A B
                "Barrel 2", new JoystickButton(controller, 2), // X Y A B
                "Barrel 3", new JoystickButton(controller,3), // X Y A B
                "Barrel 4", new JoystickButton(controller,4), // X Y A B
                "Fire All", new JoystickButton(controller, 8), // + or -
                "Close All", new JoystickButton(controller, 7) // + or -
            );
        }

        public final double DEADBAND = 0.05;

        public double getThrottle() {
            return Math.pow(MathUtil.applyDeadband(controller.getRawAxis(1), DEADBAND), curve);
        }

        public double getRotation() { 
            return Math.pow(MathUtil.applyDeadband(controller.getRawAxis(4), DEADBAND), curve);
        }

        public double getLeft() {
            return Math.pow(MathUtil.applyDeadband(controller.getRawAxis(1), DEADBAND), curve);
        }

        public double getRight() { 
            return Math.pow(MathUtil.applyDeadband(controller.getRawAxis(5), DEADBAND), curve);
        }
    }
}