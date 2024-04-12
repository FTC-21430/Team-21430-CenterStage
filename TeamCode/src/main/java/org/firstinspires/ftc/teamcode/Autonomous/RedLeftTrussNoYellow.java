package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.General.OctopusAutoFunctions;
import org.firstinspires.ftc.teamcode.General.Robot;

@Autonomous(name = "RedLeftTrussNoYellow", group = "CenterStage")
public class RedLeftTrussNoYellow extends OctopusAutoFunctions {
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

        PurplePixelRedLeftTruss();



        CamEnd();

        AprilTagInit();
        WaitFunction();

        setTurn(90);
        RunToPoint(35, -61, 1, 0.3);

        RunToPoint(48, -61, 1, 1);
        pixelLiftMotor.setTargetPosition(100);
        intakeMotor.setPower(0);
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(1);
        stateMachineTimer = getRuntime();
        AutoColorSensorUse = false;
        RunToPoint(RobotX, RobotY, -1, 2);
        pixelLiftMotor.setTargetPosition(0);
        RunToPoint(RobotX, RobotY, -1, 2);
    }
}


