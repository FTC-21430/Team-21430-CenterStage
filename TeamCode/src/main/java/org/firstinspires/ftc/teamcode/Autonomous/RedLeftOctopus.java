package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.General.OctopusAutoFunctions;

import java.sql.Time;
import java.util.concurrent.TimeoutException;

@Config
@Autonomous(name = "RedLeftOctopus", group = "CenterStage")
public class RedLeftOctopus extends OctopusAutoFunctions {
    public static double travelDistance = 12;
    public static double circleSize = 0;
    public static double Timeout = 5;

    @Override
    public void runOpMode() {
        Init();

        DriverOrientationDriveMode = false;

        CamInit();

        pattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE;
        blinkinLedDriver.setPattern(pattern);
        robotHeading = 0;
        TargetAngle = 0;
        AutoStartAngle = 0;
        IsProgramAutonomous = true;

        ZoneTelemetryUntilStart();

        runtime.reset();
        RobotX = -41;
        RobotY = -62;
        InitX = -41;
        InitY = -62;

        PurplePixelRedLeft();

        CamEnd();

        AprilTagInit();

        RunToPoint(12, -8, 2, 5);

        RunToPoint(36, -8, 2, 5);

        RunToPoint(36, -36, 3, 5);

        Speed = 0.5;

        YellowPixelRed();

        Speed = 0.8;
        RunToPoint(38, -37, 3, 5);
        RunToPoint(36, -9, 3, 3);
        RunToPoint(5, -8, 5, 3);
        intakeMotor.setPower(-0.9);
        transferMotor.setPower(-1);
        backDepositorServo.setPosition(-1);
        frontDepositorServo.setPosition(1);
        intakeServo.setPosition(0.604);
        RunToPoint(-56, -9, 3, 5);
        Speed = 0.4;
        RunToPoint(-60, -9, 1.4, 1.4);

        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 0.1 && opModeIsActive()) {

        }
        intakeServo.setPosition(0.583);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 0.2 && opModeIsActive()) {

        }
        RunToPoint(-59, -9, 1, 1);
        intakeServo.setPosition(0.8);
        Speed = 0.3;
        RunToPoint(-60, -9, 1, 1);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 1 && opModeIsActive()) {

        }

        Speed = 1;
        RunToPoint(48, -9, 1, 5);
        Speed = 0.4;
        RunToPoint(49, -10, 3, 2);
        pixelLiftMotor.setTargetPosition(100);
        intakeMotor.setPower(0);
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(0);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 1 && opModeIsActive()) {
        }
        pixelLiftMotor.setTargetPosition(0);
        while (stateMachineTimer >= getRuntime() - 5 && opModeIsActive()) {
        }
    }
}