package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "BlueRightJellyfish" , group = "CenterStage")
public class BlueRightJellyfish extends AutonomousFunction {
    @Override
    public void runOpMode() {
        Init();
        CamInit();
        robotHeading = -Math.PI;
        Target = -180;
        startOfsetRadians = -Math.PI;
        waitForStart();
        runtime.reset();
        RobotX = -38;
        RobotY = 62;
        InitX= -38;
        InitY = 62;
        //    Zone = 2;
        CamRun(3);
        // Zone = 1;
        PurplePixelBlueRight();

        Speed = 0.6;
        CamEnd();
        AprilTagInit();
        //  RunToPoint(-37,-38,2,5);
        RunToPoint(-50,58,2,5);
        RunToPoint(-63.7,42,2,5);
        RunToPoint(-61,17,2,5);
        RunToPoint(-51,12,2,5);
        setTurn(90);
        resetRuntime();
        while (0.4 >= getRuntime() && opModeIsActive()){
            ProportionalFeedbackControl();
        }
        Speed = 0.8;
        RunToPoint(12,12,2,5);
        RunToPoint(36,12,2,5);
        RunToPoint(36,36,3,5);
        Speed = 0.5;


        YellowPixelBlue();
        ParkBlue();

    }
}