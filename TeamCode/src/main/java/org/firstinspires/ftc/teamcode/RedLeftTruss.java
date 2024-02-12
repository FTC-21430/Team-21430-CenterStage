package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RedLeftTruss", group = "CenterStage")
public class RedLeftTruss extends OctopusAutoFunctions{
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

        PurplePixelRedLeftTruss();

        Speed =0.8;

        CamEnd();

        AprilTagInit();

        Speed = 0.9;
        setTurn(90);
        RunToPoint(35,-60,1,5);
        setTurn(90);
        RunToPoint(36,-36,1,5);
Speed = 0.6;
        YellowPixelRed();
        Speed = 0.9;
        RunToPoint(48,-57,1,5);
        // ParkRed();

    }}
