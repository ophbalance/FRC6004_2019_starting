/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.Robot;
import frc.robot.commands.*;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Squishy extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
    Spark leftendo = new Spark(5);
    Spark rightendo = new Spark(6);
    Spark open = new Spark(7);
    Spark tilt = new Spark(8);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new squishyaxis());
  }


    public void suckin() {
      // Update motor speed to passed in value
      leftendo.set(.2);
      rightendo.set(.2);
    }

    public void blowout() {
      // Update motor speed to passed in value
      leftendo.set(-.2);
      rightendo.set(-.2);
    }

    public void tilt() {
      // Update motor speed to passed in value
      tilt.set(-Robot.m_oi._game2.getRawAxis(1));
    }

    public void openup() {
      // Update motor speed to passed in value
      open.set(.2);
    }

    public void closeup() {
      // Update motor speed to passed in value
      open.set(-.2);
    }
}
