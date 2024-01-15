package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "RedLeftJellyfish" , group = "CenterStage")
public class RedLeftJellyfish extends AutonomousFunction {
    @Override
    public void runOpMode() {
        Init();
        DriverOrientationDriveMode = false;
        CamInit();
        robotHeading = 0;
        Target = 0;
        startOfsetRadians = 0;
        waitForStart();
        runtime.reset();
        RobotX = -41;
        RobotY = -62;
        InitX= -41;
        InitY = -62;
        CamRun(3);
        PurplePixelRedLeft();

         Speed = 0.6;
        CamEnd();
        AprilTagInit();
      //  RunToPoint(-37,-38,2,5);
        RunToPoint(-50,-58,2,5);
        RunToPoint(-63.7,-42,2,5);
        RunToPoint(-61,-17,2,5);
        RunToPoint(-53,-12,2,5);
        setTurn(90);
        resetRuntime();
        while (0.4 >= getRuntime() && opModeIsActive()){
            ProportionalFeedbackControl();
        }
        Speed = 0.8;
        RunToPoint(12,-12,2,5);
        RunToPoint(36,-12,2,5);
        RunToPoint(36,-36,3,5);
        Speed = 0.5;


        YellowPixelRed();
        ParkRed();

        }
    }