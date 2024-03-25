package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Genral.OctopusAutoFunctions;

@Autonomous(name = "BlueRightOctopus", group = "CenterStage")
public class BlueRightOctopus extends OctopusAutoFunctions {
    @Override
    public void runOpMode() {
        Init();

        DriverOrientationDriveMode = false;

        CamInit();

        pattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE;
        blinkinLedDriver.setPattern(pattern);
        robotHeading = -Math.PI;
        TargetAngle = -180;
        AutoStartAngle = -Math.PI;
        IsProgramAutonomous = true;

        ZoneTelemetryUntilStart();

        runtime.reset();
        RobotX = -38;
        RobotY = 62;
        InitX = -38;
        InitY = 62;

        PurplePixelBlueRight();

        Speed = 0.8;

        CamEnd();

        AprilTagInit();

        RunToPoint(12, 9, 2, 5);

        RunToPoint(36, 9, 2, 5);

        RunToPoint(36, 36, 3, 5);

        Speed = 0.5;

        YellowPixelBlue();

        Speed = 0.8;
        RunToPoint(40, 37, 3, 5);
        RunToPoint(40, 9, 3, 3);
        RunToPoint(5, 8, 5, 3);
        intakeMotor.setPower(-0.9);
        transferMotor.setPower(1);
        backDepositorServo.setPosition(-1);
        frontDepositorServo.setPosition(1);
        intakeServo.setPosition(0.371);
        RunToPoint(-46, 9, 3, 5);
        Speed = 0.4;
        RunToPoint(-52.4, 11, 1.4, 1.4);

        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 0.1 && opModeIsActive()) {

        }
        intakeServo.setPosition(0.346);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 0.2 && opModeIsActive()) {

        }
        RunToPoint(-54, 9, 1, 1);
        intakeServo.setPosition(0.8);
        Speed = 0.3;
        RunToPoint(-52, 9, 1, 1);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer >= getRuntime() - 1 && opModeIsActive()) {

        }

        Speed = 1;
        RunToPoint(48, 9, 1, 5);
        Speed = 0.4;
        RunToPoint(53, 14, 3, 2);
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