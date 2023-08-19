// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import frc.subsystems.DriveTrain;
import frc.subsystems.Shooter;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it contains the code
 * necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  Shooter shooter;
  DriveTrain drive;
  OI oi;
  Compressor compressor;
  PowerDistribution pdp;

  public int Y_AXIS_NUMBER = 1; // aka throttle
  public int X_AXIS_NUMBER = 4; // aka rotation

  @Override
  public void robotInit() {
    shooter = new Shooter();
    shooter.closeAll();
    drive = new DriveTrain();
    oi = new OI();
    compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    //compressor.enableAnalog(0, 32);
    //compressor.enableHybrid(0, 32);
    pdp = new PowerDistribution();
    SmartDashboard.setDefaultBoolean("autoClose", false);
  }

  @Override
  public void teleopPeriodic() {
    drive.drive(oi.getThrottle(), oi.getRotation());
    shooter.periodic();

    if(oi.getShooterButton(1)) {
      if (shooter.getSolenoidStatus(0)) shooter.close(0); //bottom right f310:A
      else shooter.fire(0);
    } else if(oi.getShooterButton(3)){
      if (shooter.getSolenoidStatus(1)) shooter.close(1); //bottom left f310:X
      else shooter.fire(1);
    } else if (oi.getShooterButton(2)) {
      if (shooter.getSolenoidStatus(2)) shooter.close(2); //top right f310:B
      else shooter.fire(2);
    } else if (oi.getShooterButton(4)) {
      if (shooter.getSolenoidStatus(3)) shooter.close(3); //top left f310:Y
      else shooter.fire(3);
    }

    if(oi.getShooterButton(7)) shooter.closeAll(); //f310: back
    if(oi.getShooterButton(8)) shooter.fireAll(); //f310: start
    
    if (SmartDashboard.getBoolean("autoClose", false) && !shooter.isAutoCloseMode()) {
      shooter.setAutoCloseMode(true);
    }
    if (!SmartDashboard.getBoolean("autoClose", false) && shooter.isAutoCloseMode()) {
      shooter.setAutoCloseMode(false);
    }
  }

  @Override
  public void disabledInit() {
    shooter.closeAll();
  }
}
