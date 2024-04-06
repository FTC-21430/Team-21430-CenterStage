package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.General.AutonomousFunction;

@Autonomous(name = "RedLeftJellyfish", group = "CenterStage")
public class RedLeftJellyfish extends AutonomousFunction {
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

        RunToPoint(-50, -58, 2, 5);
        RunToPoint(-63.7, -42, 2, 5);
        RunToPoint(-61, -12, 2, 5);
        RunToPoint(-53, -12, 2, 5);
        setTurn(90);
        resetRuntime();
        while (0.4 >= getRuntime() && opModeIsActive()) {
            ProportionalFeedbackControl();
        }

        RunToPoint(12, -12, 2, 5);
        RunToPoint(36, -12, 2, 5);
        RunToPoint(36, -36, 3, 5);


        YellowPixelRed();
        ParkRed();

    }
}