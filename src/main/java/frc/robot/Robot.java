// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.subsystems.DriveTrain;
import frc.subsystems.Shooter;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it contains the code
 * necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  Shooter shooter;
  DriveTrain drive;
  OI oi;
  Compressor compressor;

  public int Y_AXIS_NUMBER = 4; // aka throttle
  public int X_AXIS_NUMBER = 1; // aka rotation
  //TODO: Sept 14: make sure y and x axis are not switched in OI

  @Override
  public void robotInit() {
    shooter = new Shooter();
    drive = new DriveTrain();
    oi = new OI();
    compressor = new Compressor(PneumaticsModuleType.CTREPCM);
  }

  @Override
  public void teleopPeriodic() {
    drive.drive(oi.getThrottle(), oi.getRotation());
    if(oi.getShooterButton(1)){
        shooter.fire(0);
    } else if(oi.getShooterButton(2)){
        shooter.fire(1);
    } else if (oi.getShooterButton(3)) {
        shooter.fire(2);
    } else if (oi.getShooterButton(4)) {
        shooter.fire(3);
    }

    if(oi.getShooterButton(7)) shooter.closeAll();
    if(oi.getShooterButton(8)) shooter.fireAll();
    
  }
}
