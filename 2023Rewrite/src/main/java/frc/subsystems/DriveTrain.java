package frc.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private Talon leftTalon, rightTalon;
    private DifferentialDrive drive;

    private double leftMotorM, rightMotorM;

    public DriveTrain() {
        leftTalon = new Talon(RobotMap.LEFT_TALONS);
        leftTalon.setInverted(false);
        rightTalon = new Talon(RobotMap.RIGHT_TALONS); 
        rightTalon.setInverted(true);
        drive = new DifferentialDrive(leftTalon, rightTalon);
    }

    public void setMotorMultipliers(double left, double right) {
        leftMotorM = left; rightMotorM = right;
    }
    
    public void arcadeDrive(double throttle, double turn) {
        double left = (throttle-turn)*leftMotorM;
        double right = (throttle+turn)*rightMotorM;
        drive.tankDrive(left, right, false);
    }

    public void tankDrive(double left, double right) {
        drive.tankDrive(left*leftMotorM, right*rightMotorM, false);
    }

    @Override
    public void periodic() {
        SmartDashboard.putData("Drivetrain", drive);
    }
}