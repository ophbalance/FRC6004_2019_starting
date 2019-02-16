/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Robot;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Onering extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  VictorSP climbFront = new VictorSP(0);
    VictorSP climbBack = new VictorSP(1);

    VictorSP climbDrive = new VictorSP(2);
    VictorSP elevatorDrive = new VictorSP(3);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void climbup() {
    // Update motor speed to passed in value
    climbFront.set(.20);
    climbBack.set(.20*.75);
  }

  public void climbdown() {
    // Update motor speed to passed in value
    climbFront.set(-.20);
    climbBack.set(-.20);
  }
  
    public void climbdriveforward() {
      // Update motor speed to passed in value
      climbDrive.set(.35);
    }

    public void climbdriveback() {
      // Update motor speed to passed in value
      climbDrive.set(-.35);
    }

    public void elevatoraxis() {
      // Update motor speed to passed in value
      elevatorDrive.set(-Robot.m_oi._game2.getRawAxis(5));
    }
}
