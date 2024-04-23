package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.General.OctopusAutoFunctions;
import org.firstinspires.ftc.teamcode.General.Robot;

@Autonomous(name = "RedRightBackdrdopStartV1", group = "CenterStage")
public class RedRightBackdropStartV1 extends OctopusAutoFunctions {
    @Override
    public void runOpMode() {
        Init();

        DriverOrientationDriveMode = false;

        CamInit();

        pattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE;
        blinkinLedDriver.setPattern(pattern);
        robotHeading = 0;
        TargetAngle = 0;
        AutoStartAngle = 0;
        IsProgramAutonomous = true;

        ZoneTelemetryUntilStart();

        runtime.reset();
        RobotX = 11.5;
        RobotY = -62;
        InitX = 11.5;
        InitY = -62;
Speed = 1;
        CamEnd();

        AprilTagInit();
        WaitFunction();

        RunToPoint(17,-57,2,0.8);
        setTurn(90);
        intakeServo.setPosition(PixelPickerBottom+0.02);
        RunToPoint(36,-45,3,1.3);
        pixelLiftMotor.setTargetPosition(safeLiftHeight+5);
        RunToPoint(39,-37,2,0.8);

        //for stage side route

        stateMachineTimer = getRuntime();

        while (pixelLiftMotor.getCurrentPosition() <= safeLiftHeight && opModeIsActive()) {
            keepAtPoint(RobotX, RobotY);
            ProportionalFeedbackControlAuto();
        }

        fourBarServo.setPosition(0.015);
        stateMachineTimer = getRuntime();
        pixelLiftMotor.setTargetPosition(safeLiftHeight+5);
        stateMachineTimer =getRuntime();
        while (stateMachineTimer >= getRuntime()-0.2){
            keepAtPoint(RobotX, RobotY);
            ProportionalFeedbackControlAuto();
        }
        Speed = 0.6;
        if (Zone == 3) {
            RunToPoint(53.5, -46.6, 1, 1.6);

        } else if (Zone == 2) {
            RunToPoint(53, -38, 1, 1.2);

        } else {
//            pixelLiftMotor.setTargetPosition(460);
            RunToPoint(52.5, -28, 1, 1);
        }
        Speed = 1;
        backDepositorServo.setPosition(0);
        frontDepositorServo.setPosition(0);
        stateMachineTimer = getRuntime();
        while (stateMachineTimer > getRuntime() - 0.5 && opModeIsActive()) {
            keepAtPoint(RobotX, RobotY);
        }
        backDepositorServo.setPosition(0.5);
        frontDepositorServo.setPosition(0.5);
        if (Zone == 3){
            RunToPoint(35,-29.5,2,2);
        }else if(Zone == 2){
            RunToPoint(27,-24,2,1);
        }else{
            RunToPoint(13,-31,2,1.8);
        }
        fourBarServo.setPosition(0.92);

       if (Zone == 3){
           RunToPoint(35,-29.5,2,2);
           pixelLiftMotor.setTargetPosition(safeLiftHeight);
           intakeMotor.setPower(0.5);
           RunToPoint(35,-29.5,-1,.4);
           intakeMotor.setPower(0);
           intakeServo.setPosition(PixelPickerTop);

       }else if(Zone == 2){
           RunToPoint(27,-24,2,1);
           pixelLiftMotor.setTargetPosition(safeLiftHeight);
           intakeMotor.setPower(0.5);
           RunToPoint(27,-24,-1,.4);
           intakeMotor.setPower(0);
           intakeServo.setPosition(PixelPickerTop);


       }else{
           RunToPoint(13,-31,2,1.8);
           pixelLiftMotor.setTargetPosition(safeLiftHeight);
           intakeMotor.setPower(0.5);
           RunToPoint(13,-31,-1,.4);
           intakeMotor.setPower(0);
           intakeServo.setPosition(PixelPickerTop);

       }

        if (Zone == 2 || Zone ==3){
            RunToPoint(31,-37,4,2);
            pixelLiftMotor.setTargetPosition(1);
        }else{
            pixelLiftMotor.setTargetPosition(1);
            RunToPoint(48,-58,4,2);
        }
            setTurn(0);
        RunToPoint(46,-58,1,1);

    }
}


