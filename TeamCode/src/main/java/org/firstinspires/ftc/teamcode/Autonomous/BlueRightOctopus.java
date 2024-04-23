package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.General.OctopusAutoFunctions;

@Autonomous(name = "BlueRightOctopus", group = "CenterStage")
public class BlueRightOctopus extends OctopusAutoFunctions {
    @Override
    public void runOpMode() {
        Init();

        DriverOrientationDriveMode = false;

        CamInit();

        pattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE;
        blinkinLedDriver.setPattern(pattern);

        TargetAngle = 180;
        AutoStartAngle = Math.PI;
        OldAngle = AutoStartAngle;
        IsProgramAutonomous = true;

        ZoneTelemetryUntilStart();

        runtime.reset();

        RobotX = -34;
        RobotY = 62;
        InitX = -34;
        InitY = 62;
        Speed = 1;

        PurplePixelBlueRight();

        CamEnd();

        AprilTagInit();
        RunToPoint(RobotX,RobotY,-1,7);

        if (Zone == 1){
            RunToPoint(30, 13.2, 5, 4);

            RunToPoint(30, 39, 2, 0.8);
        }else{
            RunToPoint(27, 14, 5, 5);

            RunToPoint(34.3, 40, 2, 1.2);
        }

        stateMachineTimer = getRuntime();
        while (!HasSeenTag && stateMachineTimer >= getRuntime() - 3 && opModeIsActive()){
            RunToPoint(RobotX,RobotY,-1,0.01);
        }
        RunToPoint(39.3, 33, 3, 1.3);
        YellowPixelBlue(false);

        RunToPoint(38, 37, 5, 5);

//        RunToPoint(36, 9, 1, 1.3);
//        RunToPoint(5, 9, 5, 3);
//        intakeMotor.setPower(-0.9);
//        transferMotor.setPower(1);
//        AutoColorSensorUse = true;
//        backDepositorServo.setPosition(1);
//        frontDepositorServo.setPosition(1);
//        RunToPoint(-40, 10, 3, 2);
//        intakeServo.setPosition(0.595);
//        RunToPoint(-57, 9.5, 0, 0.5);
//        RunToPoint(RobotX,RobotY,-1,0.2);//wait
//        RunToPoint(-55, 9.5, 0, 0.4);
//        intakeServo.setPosition(0.585);
//        RunToPoint(-57.5, 9.5, 0, 0.15);
//        RunToPoint(-56.2,9.5 ,0, 0.2);
//        intakeServo.setPosition(0.8);
//        RunToPoint(-55, 9.5, 0, 0.4);
//        RunToPoint(-58.4, 9.5, 0, 0.4);
//        RunToPoint(-40, 9.5, 3, 0.8);
//        RunToPoint(40, 9.5, 3, 1.5);
//        pixelLiftMotor.setTargetPosition(100);
//        intakeMotor.setPower(0);
//        transferMotor.setPower(0);
        RunToPoint(48, 19, 2, 1.5);
        AutoColorSensorUse = false;
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(1);

        RunToPoint(RobotX,RobotY,-1,1.0);
        pixelLiftMotor.setTargetPosition(0);
        setTurn(-180);
        RunToPoint(RobotX,RobotY,-1,1);//wait
    }
}
