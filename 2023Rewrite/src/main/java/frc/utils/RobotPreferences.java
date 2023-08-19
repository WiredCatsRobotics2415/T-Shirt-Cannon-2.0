package frc.utils;

import edu.wpi.first.wpilibj.Preferences;

public class RobotPreferences {
    public static enum PrefTypes {
        BOOL,
        DOUBLE,
        FLOAT,
        LONG,
        INT,
        STRING
    }

    private static void ensureExistance(String key, PrefTypes type, Object defaultValue) {
        if (!Preferences.containsKey(key)) {
            switch (type) {
                case BOOL:
                    Preferences.initBoolean(key, (boolean)defaultValue);
                    break;
                case DOUBLE:
                    Preferences.initDouble(key, (double)defaultValue);
                    break;
                case FLOAT:
                    Preferences.initFloat(key, (float)defaultValue);
                    break;
                case INT:
                    Preferences.initInt(key, (int)defaultValue);
                    break;
                case LONG:
                    Preferences.initLong(key, (long)defaultValue);
                    break;
                case STRING:
                    Preferences.initString(key, (String)defaultValue);
                    break;
                default:
                    break;
            }
        }
    }

    /**
    * @return true if preference is set to tank, false if preference is arcade
    */
    public static boolean getDriveMode() {
        String key = "Drive mode (False = arcade, True = tank): ";
        ensureExistance(key, PrefTypes.BOOL, false);
        return Preferences.getBoolean(key, false);
    }

    /**
    * @return integer>0 if IF is true, otherwise 1
    */
    public static int getCurvePower() {
        String key = "Curve Power (Only if IF is True): ";
        ensureExistance(key, PrefTypes.INT, 1);
        return Preferences.getInt(key, 1);
    }

    /**
    * @return auto close mode preference
    */
    public static boolean getAutoCloseMode() {
        String key = "Auto Close mode: ";
        ensureExistance(key, PrefTypes.BOOL, false);
        return Preferences.getBoolean(key, false);
    }

    /**
    * @return left motor power multiplier
    */
    public static double getLeftPowerMultiplier() {
        String key = "Left Power Multiplier: ";
        ensureExistance(key, PrefTypes.DOUBLE, 1.0d);
        return Preferences.getDouble(key, 1.0d);
    }

    /**
    * @return right motor power multiplier
    */
    public static double getRightPowerMultiplier() {
        String key = "Right Power Multiplier: ";
        ensureExistance(key, PrefTypes.DOUBLE, 1.0d);
        return Preferences.getDouble(key, 1.0d);
    }
}
