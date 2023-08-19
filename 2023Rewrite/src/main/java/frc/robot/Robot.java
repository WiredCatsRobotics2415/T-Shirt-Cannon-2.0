// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.subsystems.DriveTrain;
import frc.subsystems.Shooter;
import frc.utils.RobotPreferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class Robot extends TimedRobot {
  private Shooter shooter;
  private DriveTrain drive;

  private SendableChooser<Integer> oiChooser;
  private OIs.OI selectedOI;

  boolean currentDriveMode = false; //False = arcade, True = tank
  boolean currentAutoCloseMode = false;

  @Override
  public void robotInit() {
    shooter = new Shooter();
    shooter.closeAll();
    drive = new DriveTrain();
    oiChooser = new SendableChooser<Integer>();
    oiChooser.setDefaultOption("Logitech F310", 0);
  }

  private void configButtonBindings() {
    selectedOI.binds.get("Barrel 1").toggleOnTrue(new InstantCommand(() -> {
      if (shooter.getSolenoidStatus(0)){
        shooter.close(0);
        System.out.println("close");
      }
      else {
        shooter.fire(0);
        System.out.println("open");
      }
    }, shooter));
    selectedOI.binds.get("Barrel 2").toggleOnTrue(new InstantCommand(() -> {
      if (shooter.getSolenoidStatus(1)) shooter.close(1);
      else shooter.fire(1);
    }, shooter));
    selectedOI.binds.get("Barrel 3").toggleOnTrue(new InstantCommand(() -> {
      if (shooter.getSolenoidStatus(2)) shooter.close(2);
      else shooter.fire(2);
    }, shooter));
    selectedOI.binds.get("Barrel 4").toggleOnTrue(new InstantCommand(() -> {
      if (shooter.getSolenoidStatus(3)) shooter.close(3);
      else shooter.fire(3);
    }, shooter));
    selectedOI.binds.get("Fire All").toggleOnTrue(new InstantCommand(() -> {
      shooter.fireAll();
    }, shooter));
    selectedOI.binds.get("Close All").toggleOnTrue(new InstantCommand(() -> {
      shooter.fireAll();
    }, shooter));
  }

  @Override
  public void teleopInit() {
    switch (oiChooser.getSelected()) {
      case 0:
        selectedOI = new OIs.LogitechController();
        break;
      default:
        selectedOI = new OIs.LogitechController();
        break;
    }
    selectedOI.setInputPrefrences();
    configButtonBindings();
    currentDriveMode = RobotPreferences.getDriveMode();
    drive.setMotorMultipliers(RobotPreferences.getLeftPowerMultiplier(), RobotPreferences.getRightPowerMultiplier());
    drive.setDefaultCommand(new RunCommand(() -> {
      if (!currentDriveMode) {
        drive.arcadeDrive(selectedOI.getThrottle(), selectedOI.getRotation());
      } else {
        drive.tankDrive(selectedOI.getLeft(), selectedOI.getRight());
      }
    }, drive));
    currentAutoCloseMode = RobotPreferences.getAutoCloseMode();
    shooter.setAutoCloseMode(currentAutoCloseMode);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putData("OI", oiChooser);
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    shooter.closeAll();
  }
}
