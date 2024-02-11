package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


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
            Target = -180;
            startOfsetRadians = -Math.PI;
            IsProgramAutonomous = true;

            ZoneTelemetryUntilStart();

            runtime.reset();
            RobotX = -38;
            RobotY = 62;
            InitX= -38;
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

            ParkBlue();
        }
    }
