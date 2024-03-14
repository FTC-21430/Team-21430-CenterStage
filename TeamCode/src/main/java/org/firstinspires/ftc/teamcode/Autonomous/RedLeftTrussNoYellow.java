package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Genral.OctopusAutoFunctions;

@Autonomous(name = "RedLeftTrussNoYellow", group = "CenterStage")
public class RedLeftTrussNoYellow extends OctopusAutoFunctions {
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
        WaitFunction();
        Speed = 0.9;
        setTurn(90);
        RunToPoint(35,-61,1,5);

        RunToPoint(48,-61,1,5);
        // ParkRed();
        pixelLiftMotor.setTargetPosition(100);
        intakeMotor.setPower(0);
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(0);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 1  && opModeIsActive()){}
        pixelLiftMotor.setTargetPosition(0);
        while (stateMachineTimer >= getRuntime() - 5  && opModeIsActive()){}
    }}


