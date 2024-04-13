package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.General.OctopusAutoFunctions;

@Autonomous(name = "BlueRightTruss", group = "CenterStage")
public class BlueRightTruss extends OctopusAutoFunctions {
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

        PurplePixelBlueRightTruss();



        CamEnd();

        AprilTagInit();
        WaitFunction();

        setTurn(90);
        RunToPoint(35, 60, 1, 5);
        setTurn(90);
        if (Zone != 2) {
            RunToPoint(36, 36, 1, 5);

            YellowPixelBlue(false);
        }


        RunToPoint(48, 57, 1, 5);
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
