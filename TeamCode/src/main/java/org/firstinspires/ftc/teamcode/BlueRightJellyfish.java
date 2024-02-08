package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "BlueRightJellyfish" , group = "CenterStage")
public class BlueRightJellyfish extends AutonomousFunction {
    @Override
    public void runOpMode() {
        Init();
        DriverOrientationDriveMode = false;
        CamInit();
        robotHeading = -Math.PI;
        Target = -180;
        startOfsetRadians = -Math.PI;
        ZoneTelemetryUntilStart();

        runtime.reset();
        RobotX = -38;
        RobotY = 62;
        InitX= -38;
        InitY = 62;
        IsProgramAutonomous = true;
        pattern = RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE;
        blinkinLedDriver.setPattern(pattern);
        PurplePixelBlueRight();

        Speed = 0.6;
        CamEnd();
       // AprilTagInit();
        //  RunToPoint(-37,-38,2,5);
        RunToPoint(-50,58,2,5);
        RunToPoint(-62.5,42,2,5);
        RunToPoint(-62,13,3,5);
        RunToPoint(-55,13,2,5);

        setTurn(90);
        double TempTimer = getRuntime();
        while (0.4 + TempTimer >= getRuntime() && opModeIsActive()){
            ProportionalFeedbackControl();
        }
        Speed = 0.8;
      //  RunToPoint(-40,-17,2,5);
        RunToPoint(-11,11,2,5);
        RunToPoint(36,12,2,5);
        RunToPoint(36,12,2,5);
        RunToPoint(36,36,3,5);
        Speed = 0.5;


        YellowPixelBlue();
        ParkBlue();

    }
}