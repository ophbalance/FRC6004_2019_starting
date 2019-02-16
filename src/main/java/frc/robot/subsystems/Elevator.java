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
import frc.robot.commands.*;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
    VictorSP elevatorDrive = new VictorSP(3);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new elevatoraxis());
  }


    public void elevatoraxis() {
      // Update motor speed to passed in value
      elevatorDrive.set(-Robot.m_oi._game2.getRawAxis(5));
    }

    public void elevatorup() {
        // Update motor speed to passed in value
        elevatorDrive.set(.3);
      }

      public void elevatordown() {
        // Update motor speed to passed in value
        elevatorDrive.set(-.3);
      }
}
