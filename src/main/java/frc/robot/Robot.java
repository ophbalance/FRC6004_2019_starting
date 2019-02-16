/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.subsystems.*; 
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Onering one = null;
  public static Elevator elevator = null;
  public static Squishy squishy = null;
  public static OI m_oi;
  WPI_TalonSRX _leftMaster = new WPI_TalonSRX(11);
    WPI_TalonSRX _rightMaster = new WPI_TalonSRX(10);
    WPI_VictorSPX  _leftFollow = new WPI_VictorSPX (13);
    WPI_VictorSPX  _rightFollow = new WPI_VictorSPX (12);
    DifferentialDrive _drive = new DifferentialDrive(_leftMaster, _rightMaster);
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    one = new Onering();
    elevator = new Elevator();
    squishy = new Squishy();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    _leftMaster.configFactoryDefault();
        _rightMaster.configFactoryDefault();
        _leftFollow.configFactoryDefault();
        _rightFollow.configFactoryDefault();
        
        _leftFollow.follow(_leftMaster);
        _rightFollow.follow(_rightMaster);
        
        _leftMaster.setInverted(false); // <<<<<< Adjust this until robot drives forward when stick is forward
        _rightMaster.setInverted(true); // <<<<<< Adjust this until robot drives forward when stick is forward
        _leftFollow.setInverted(InvertType.FollowMaster);
        _rightFollow.setInverted(InvertType.FollowMaster);
        _drive.setRightSideInverted(false); // do not change this
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    double forward = 1 * m_oi._gamepad.getY();
        double turn = m_oi._gamepad.getTwist();
        double liftup = m_oi._game2.getY();
        double driveLift = m_oi._game2.getRawAxis(5);
        forward = Deadband(forward);
        turn = Deadband(turn);
  }

/** Deadband 5 percent, used on the gamepad */
double Deadband(double value) {
  /* Upper deadband */
  if (value >= +0.25) 
      return value;
  
  /* Lower deadband */
  if (value <= -0.25)
      return value;
  
  /* Outside deadband */
  return 0;
}

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
