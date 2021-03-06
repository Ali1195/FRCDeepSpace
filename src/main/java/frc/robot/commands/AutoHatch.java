/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoHatch(int level) {
    this(level, true);
  }

  public AutoHatch(int level, boolean eject) {
    addSequential(new ChangeMode(true));
    switch (level) {
    case 1:
      addSequential(new SetElevatorHeight(30));
      break;
    case 2:
      addSequential(new SetElevatorHeight(105));
      break;
    case 3:
      addSequential(new SetElevatorHeight(175));
    }
    if (eject)
      addSequential(new Eject());

    addSequential(new SetElevatorHeight(26));
    addSequential(new DisableElevator());
  }
}
