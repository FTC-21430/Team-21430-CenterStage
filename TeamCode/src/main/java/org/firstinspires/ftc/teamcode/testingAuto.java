package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "testingAuto", group = "CenterStage")
public class testingAuto extends AutonomousFunction {
    @Override
    public void runOpMode() {
        Init();
        AprilTagInit();


        robotHeading = 0;
        Target = 0;
        startOfsetRadians = 0;
        waitForStart();
        runtime.reset();
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IMUReset();
        RobotX = -32;
        RobotY = -60;
        InitX= -32;
        InitY = -60;


        RunToPoint(-37,-37,1);
        setTurn(90);
        RunToPoint(-59,-36,1);
        RunToPoint(-57,-36,1);
        RunToPoint(-37,-12,1);
        RunToPoint(11,-9,1);
        RunToPoint(37,-12,1);
        RunToPoint(37,-36,1);
        RunToPoint(48,-36,1);

        RunToPoint(11,-60,1);
        RunToPoint(-38,-60,1);
        RunToPoint(-60,-24,1);
        RunToPoint(-49,-13,1);
        RunToPoint(-30,-12,1);
        RunToPoint(12,-11,1);
        RunToPoint(61,-12,1);


        //YellowPixel();
        //Park();

    }
}