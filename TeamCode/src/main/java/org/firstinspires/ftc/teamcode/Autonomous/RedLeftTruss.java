package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.General.OctopusAutoFunctions;

@Autonomous(name = "RedLeftTruss", group = "CenterStage")
@Disabled
public class RedLeftTruss extends OctopusAutoFunctions {
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
        RunToPoint(35, -60, 1, 5);
        setTurn(90);
        if (Zone != 2) {
            RunToPoint(36, -36, 1, 5);


            YellowPixelRed(false);
        }


        RunToPoint(48, -57, 1, 5);
        if (Zone == 2) {
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
}