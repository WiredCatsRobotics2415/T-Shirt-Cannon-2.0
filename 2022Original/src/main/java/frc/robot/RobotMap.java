package frc.robot;

public class RobotMap {
    //shooter penumatics
    /* OLD PORTS
    public static final int LEFT_INTAKE_EXTEND = 0;
    public static final int LEFT_INTAKE_RETRACT = 0;
    public static final int RIGHT_INTAKE_EXTEND = 0;
    public static final int RIGHT_INTAKE_RETRACT = 0;
    */

    public static final int LEFT_FIRE_SOLENOID_1 = 0;
    public static final int LEFT_FIRE_SOLENOID_2 = 2;
    public static final int RIGHT_FIRE_SOLENOID_1 = 1;
    public static final int RIGHT_FIRE_SOLENOID_2 = 3;

    public static final int[] FIRE_SOLENOIDS = {RIGHT_FIRE_SOLENOID_1, RIGHT_FIRE_SOLENOID_2, LEFT_FIRE_SOLENOID_1, LEFT_FIRE_SOLENOID_2};

    public static final int LEFT_TALONS = 0;
    public static final int RIGHT_TALONS = 9;

    public static final int PCM_ID = 20;
}
