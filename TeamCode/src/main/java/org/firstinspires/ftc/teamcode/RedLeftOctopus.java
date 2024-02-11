package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RedLeftOctopus", group = "CenterStage")
public class RedLeftOctopus extends OctopusAutoFunctions{
    @Override
    public void runOpMode() {
    Init();

    DriverOrientationDriveMode =false;

    CamInit();

    pattern =RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE;
        blinkinLedDriver.setPattern(pattern);
    robotHeading =0;
    Target =0;
    startOfsetRadians =0;
    IsProgramAutonomous =true;

    ZoneTelemetryUntilStart();

        runtime.reset();
    RobotX =-41;
    RobotY =-62;
    InitX=-41;
    InitY =-62;

    PurplePixelRedLeft();

    Speed =0.8;

    CamEnd();

    AprilTagInit();

    RunToPoint(12,-9,2,5);

    RunToPoint(36,-9,2,5);

    RunToPoint(36,-36,3,5);

    Speed =0.5;


    YellowPixelRed();

   // ParkRed();

       Speed = 0.8;
        RunToPoint(53,-9,1,5);

        RunToPoint(5,-9,1,5);
        intakeMotor.setPower(-0.9);
        transferMotor.setPower(1);
        backDepositorServo.setPosition(-1);
        frontDepositorServo.setPosition(1);
        intakeServo.setPosition(0.371);
        RunToPoint(-54,-9,3,5);
        Speed = 0.6;
        RunToPoint(-56.5,-9,1,5);

        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 0.1  && opModeIsActive()){

        }
        intakeServo.setPosition(0.346);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 0.2  && opModeIsActive()){

        }
        RunToPoint(-54,-9,1,5);
        intakeServo.setPosition(0.8);
        RunToPoint(-59,-9,1,5);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 3  && opModeIsActive()){

        }
        Speed = 0.9;
        RunToPoint(48,-9,1,5);
        Speed = 0.4;
        RunToPoint(53,-9,1,5);
    }
}
