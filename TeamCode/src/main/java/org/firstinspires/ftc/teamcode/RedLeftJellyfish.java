package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "RedLeftJellyfish" , group = "CenterStage")
public class RedLeftJellyfish extends AutonomousFunction {
    @Override
    public void runOpMode() {
        Init();
        CamInit();
        robotHeading = 0;
        Target = 0;
        startOfsetRadians = 0;
        waitForStart();
        runtime.reset();
        IMUReset();
        RobotX = -32;
        RobotY = -60;
        InitX= -37;
        InitY = -60;
        CamRun(3);

        PurplePixelRed();
        //YellowPixel();
        //Park();

        }
    }