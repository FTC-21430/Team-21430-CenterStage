package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.General.AutonomousFunction;

@Disabled
@Autonomous(name = "BlueRightJellyfish", group = "CenterStage")
public class BlueRightJellyfish extends AutonomousFunction {
    @Override
    public void runOpMode() {
        Init();
        DriverOrientationDriveMode = false;
        CamInit();
        robotHeading = -Math.PI;
        TargetAngle = -180;
        AutoStartAngle = -Math.PI;
        ZoneTelemetryUntilStart();

        runtime.reset();
        RobotX = -38;
        RobotY = 62;
        InitX = -38;
        InitY = 62;
        IsProgramAutonomous = true;
        pattern = RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE;
        blinkinLedDriver.setPattern(pattern);
        PurplePixelBlueRight();


        CamEnd();
        RunToPoint(-50, 58, 2, 5);
        RunToPoint(-62.5, 42, 2, 5);
        RunToPoint(-62, 13, 3, 5);
        RunToPoint(-55, 13, 2, 5);

        setTurn(90);
        double TempTimer = getRuntime();
        while (0.4 + TempTimer >= getRuntime() && opModeIsActive()) {
            ProportionalFeedbackControl();
        }

        RunToPoint(-11, 11, 2, 5);
        RunToPoint(36, 12, 2, 5);
        RunToPoint(36, 12, 2, 5);
        RunToPoint(36, 36, 3, 5);


        YellowPixelBlue();
        ParkBlue();
    }
}