package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "BlueLeftJellyfish" , group = "CenterStage")
public class BlueLeftJellyfish extends AutonomousFunction {
    @Override
    public void runOpMode() {
        Init();
        DriverOrientationDriveMode = false;
        closeStartPos = true;
        CamInit();
        robotHeading = -Math.PI;
        Target = -180;
        startOfsetRadians = -Math.PI;
        waitForStart();
        runtime.reset();
        RobotX = 9.5;
        RobotY = 62;
        InitX= 9.5;
        InitY = 62;
        //    Zone = 2;
        CamRun(3);
         Zone = 3;


        //RunToPoint(13,46,3,1);
        PurplePixelBlueLeft();
        RunToPoint(24,48,0,2.4);
        Speed = 0.6;
        CamEnd();
        AprilTagInit();

        Speed = 0.5;
        YellowPixelBlue();
        ParkBlue();

    }
}