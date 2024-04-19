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
        TargetAngle = 180;
        AutoStartAngle = Math.PI;
        OldAngle = AutoStartAngle;
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

        //first point on the other side of the truss
        RunToPoint(39, 63, 2, 1.4);
        stateMachineTimer = getRuntime();
        pixelLiftMotor.setTargetPosition(safeLiftHeight);
        while (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight && opModeIsActive()) {
            keepAtPoint(RobotX, RobotY);
            ProportionalFeedbackControlAuto();
        }
        fourBarServo.setPosition(0.015);
        pixelLiftMotor.setTargetPosition(safeLiftHeight+70);
        //move in
        RunToPoint(39, 39, 3, 1.4);
        //start of yellow pixel

        Speed = 1;
        if (Zone == 3) {
            RunToPoint(51.5, 25.5, 1, 1.6);
        } else if (Zone == 2) {
            RunToPoint(52, 32.5, 1, 1.2);
        } else {
            RunToPoint(51.2, 39.2, 1, 1);


        }
        Speed = 1;
        backDepositorServo.setPosition(0);
        frontDepositorServo.setPosition(0);
        stateMachineTimer = getRuntime();
        RunToPoint(RobotX, RobotY,-1,1);
        backDepositorServo.setPosition(0.5);
        frontDepositorServo.setPosition(0.5);
        RunToPoint(36, 46, 3, 1.2);
        fourBarServo.setPosition(0.92);
        stateMachineTimer = getRuntime();
        RunToPoint(RobotX,RobotY,-1,1);
        pixelLiftMotor.setTargetPosition(0);

        //end of yellow pixel

        RunToPoint(39, 39, 3, 1.4);
        setTurn(-180);
        RunToPoint(32, 63, 3, 1.4);
        RunToPoint(32, 39, 1, 1.4);
    }
}
