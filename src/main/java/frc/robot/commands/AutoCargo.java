/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoCargo(int level) {
    // addSequential(new Align());
    addSequential(new ChangeMode(false));
    switch(level) {
      case 1:
        addSequential(new SetElevatorHeight(77));
        addSequential(new Intake(0.6), 1);
        break;
    
      case 2:
        addSequential(new SetElevatorHeight(142));
        addSequential(new Intake(0.6), 1);
        break;
      case 3:
        addSequential(new SetElevatorHeight(180));
        addSequential(new SetJointAngle(33));
        addSequential(new Intake(0.8), 1.5);
      default:
        break;
    }
    addSequential(new SetJointAngle(0));
    addSequential(new SetElevatorHeight(26));
    addSequential(new DisableElevator());
    addSequential(new DisableJoint());
    addSequential(new ChangeMode(true));
  }
}
