/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AlignRotate extends Command {
  double error;
  double power;
  double hError;
  double hPower;
  double currentAngle;
  double wantedAngle;

  public AlignRotate() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.error = Robot.table.getEntry("Rotate error").getNumber(0).floatValue() < 0 ;
    error = Robot.table.getEntry("Rotate error").getDouble(0);
  currentAngle = RobotMap.gyro.getAngle();
  //wantedAngle = (currentAngle - currentAngle % 45);
  if(currentAngle > 0 && currentAngle < 45) {
    if(error > 0)
      wantedAngle = 0;
    else
      wantedAngle = 45;
  }
  else if(currentAngle > 45 && currentAngle < 90) {
    if(error > 0)
      wantedAngle = 45;
    else
      wantedAngle = 90;
  }
  else if(currentAngle > 90 && currentAngle < 135) {
    if(error > 0)
      wantedAngle = 90;
    else
      wantedAngle = 135;
  }
  else if(currentAngle > 135 && currentAngle < 180) {
    if(error > 0)
      wantedAngle = 135;
    else
      wantedAngle = 180;
  }else if(currentAngle > -45 && currentAngle < 0) {
    if(error > 0)
      wantedAngle = -45;
    else
      wantedAngle = 0;
  }else if(currentAngle > -90 && currentAngle < -45) {
    if(error > 0)
      wantedAngle = -90;
    else
      wantedAngle = -45;
  }else if(currentAngle > -135 && currentAngle < -90) {
    if(error > 0)
      wantedAngle = -135;
    else
      wantedAngle = -90;
  }else if(currentAngle > -180 && currentAngle < -135) {
    if(error > 0)
      wantedAngle = -180;
    else
      wantedAngle = -135;
  }else{
    wantedAngle = 0;
    
    
  }
  RobotMap.wantedAngle = wantedAngle;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  hError = Robot.table.getEntry("Horizontal error").getDouble(0);
  error = Robot.table.getEntry("Rotate error").getDouble(0);
  power = error*Robot.driveTrain.Kp*4;
  hPower = -(hError*Robot.driveTrain.Kp);
   
  if(power <= 0.05 && power > 0)
    power = 0.05;
  else if(power >= 0.2)
    power = 0.2;
  else if(power <= -0.2)
    power = -0.2;
  else if(power >= -0.05 && power < 0)
    power = -0.05;
  
  if(hPower <= 0.05 && hPower > 0)
    hPower = 0.05;
  else if(hPower >= 0.3)
    hPower = 0.3;
  else if(hPower <= -0.3)
    hPower = -0.3;
  else if(hPower >= -0.05 && hPower < 0)
    hPower = -0.05;
  
  Robot.driveTrain.gyroTurn(RobotMap.gyro, wantedAngle);
  //Robot.driveTrain.drive(0, 0, power, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(wantedAngle - RobotMap.gyro.getAngle()) <= 1 /*Math.abs(error) <= 5 */ /*&& Math.abs(hError) <= 15*/;
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
    Robot.driveTrain.drive(0, 0, 0, false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
