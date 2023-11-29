package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Left_OctopusBlueAutoV1" , group = "CenterStage")
public class Left_OctopusBlueAutoV1 extends OdometryCode {
    @Override
    public void runOpMode() throws InterruptedException {
        Init();
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
        OdometryInit(-63, 9);
        startOfsetRadians = -Math.PI/2;
        RunToPoint(-63, 9);
        RunToPoint(-50,30);
        setTurn(180);
        RunToPoint(-37,44);

        RunToPoint(-12,44);
        RunToPoint(-11,-8);
        RunToPoint(-12,-57);
        RunToPoint(-11,44);
        RunToPoint(-37,43);
        RunToPoint(-61,43);
        RunToPoint(-61,60);




        telemetry.addData("Y", RobotY);
        telemetry.addData("X", RobotX);
        telemetry.addData("Angle", RobotAngle);
        telemetry.addData( "a motor", FrontLeft);
        telemetry.addData("leftfrontmotorencoder", leftFrontMotor.getCurrentPosition());
        telemetry.addData("leftbackmotorencoder", leftBackMotor.getCurrentPosition());
        telemetry.addData("rightfrontmotorencoder", rightFrontMotor.getCurrentPosition());
        telemetry.addData("rightbackmoterencoder", rightBackMotor.getCurrentPosition());
        telemetry.addData("stick data",(leftFrontPower));
        telemetry.addData("stick Data Y", drive);
        telemetry.addData("dpad",gamepad1.dpad_up);
        telemetry.update();
    }
}