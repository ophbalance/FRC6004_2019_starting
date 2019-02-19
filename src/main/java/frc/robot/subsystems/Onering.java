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
import frc.robot.commands.climbaxispowers;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Onering extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  VictorSP climbFront = new VictorSP(0);
    VictorSP climbBack = new VictorSP(1);

    VictorSP climbDrive = new VictorSP(2);

  @Override
  public void initDefaultCommand() {
    
    setDefaultCommand(new climbaxispowers());
  }
  
  public void fourthreich() {
    climbFront.set(Robot.m_oi._game2.getRawAxis(1));
    climbBack.set(Robot.m_oi._game2.getRawAxis(1)*.675);
  }
  public void climbup(double steve) {
    // Update motor speed to passed in value
    climbFront.set(steve);
  }

  public void climbdown(double steve) {
    // Update motor speed to passed in value
    climbBack.set(steve);
  }
  
    public void climbdriveforward() {
      // Update motor speed to passed in value
      climbDrive.set(.55);
    }

    public void climbdriveback() {
      // Update motor speed to passed in value
      climbDrive.set(-.55);
    }

    public void hardstop() {
      climbFront.set(0);
      climbBack.set(0);
      climbDrive.set(0);
    }

    
}
