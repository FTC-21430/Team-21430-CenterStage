package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "RedLeftJellyfish" , group = "CenterStage")
public class RedLeftJellyfish extends AutonomousFunction {
    @Override
    public void runOpMode() {
        Init();
      //  CamInit();
        AprilTagInit();
        robotHeading = 0;
        Target = 0;
        startOfsetRadians = 0;
        waitForStart();
        runtime.reset();
      //  IMUReset();
        RobotX = -41;
        RobotY = -62;
        InitX= -41;
        InitY = -62;
        //CamRun(3);
        Zone = 2;
        PurplePixelRed();

        RunToPoint(-37,-38,1,10);
        RunToPoint(-50,-58,1,10);
        RunToPoint(-60,-42,1,10);
        RunToPoint(-59,-17,2,10);
        RunToPoint(-41,-11,3,10);
        RunToPoint(12,-11,1,10);
        RunToPoint(36,-13,1,10);
        setTurn(90);
        RunToPoint(36,-36,2,10);



        YellowPixel();
        //Park();

        }
    }