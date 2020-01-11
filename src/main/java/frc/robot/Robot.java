/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.util.Color;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private CANSparkMax left1Motor;
  
  private CANSparkMax right1Motor;

  DifferentialDrive m_robotDrive;

  private XboxController m_controller;
  private final Timer m_timer = new Timer();

   ColorSensorV3 colorSensor;
   ColorMatch colorMatch;
  
   Color kBlue = ColorMatch.makeColor(0, 0, 255);
   Color kRed = ColorMatch.makeColor(255, 0, 0);
   Color kYellow = ColorMatch.makeColor(255, 255, 0);
   Color kGreen = ColorMatch.makeColor(0, 255, 0);
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    colorMatch = new ColorMatch();

    colorMatch.addColorMatch(kBlue);
    colorMatch.addColorMatch(kRed);
    colorMatch.addColorMatch(kYellow);
    colorMatch.addColorMatch(kGreen);

    right1Motor = new CANSparkMax(1, MotorType.kBrushless);
    left1Motor = new CANSparkMax(2, MotorType.kBrushless);

    left1Motor.restoreFactoryDefaults();
    right1Motor.restoreFactoryDefaults();

    m_robotDrive = new DifferentialDrive(left1Motor, right1Motor);

    m_controller = new XboxController(0);
    
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
//      //m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      //m_robotDrive.stopMotor(); // stop robot
    }
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {

  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    //Color color = colorSensor.getColor();
    //ColorMatchResult result = colorMatch.matchClosestColor(color);

    //System.out.println(result.color.toString());

    m_robotDrive.tankDrive(-m_controller.getY(Hand.kLeft), -m_controller.getY(Hand.kRight), true);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
