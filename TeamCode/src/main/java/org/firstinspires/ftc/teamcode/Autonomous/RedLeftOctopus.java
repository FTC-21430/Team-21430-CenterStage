package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.General.OctopusAutoFunctions;
import org.firstinspires.ftc.teamcode.General.Robot;

import java.sql.Time;
import java.util.concurrent.TimeoutException;

@Config
@Autonomous(name = "RedLeftOctopus", group = "CenterStage")
public class RedLeftOctopus extends OctopusAutoFunctions {
    public static double travelDistance = 12;
    public static double circleSize = 0;
    public static double Timeout = 5;

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
        RobotX = -41;
        RobotY = -62;
        InitX = -41;
        InitY = -62;
        Speed = 1;
        PurplePixelRedLeft();

        CamEnd();

        AprilTagInit();


        if (Zone == 1){
            RunToPoint(38, -4, 5, 4);

            RunToPoint(38, -33, 2, 0.8);
        }else{
            RunToPoint(27, -3, 5, 5);

            RunToPoint(27.3, -33, 2, 1.2);
        }

        stateMachineTimer = getRuntime();
        while (!HasSeenTag && stateMachineTimer >= getRuntime() - 3 && opModeIsActive()){
            RunToPoint(RobotX,RobotY,-1,0.01);
        }
        RunToPoint(39.3, -33, 3, 1.3);
        YellowPixelRed();

        RunToPoint(38, -37, 5, 5);

        RunToPoint(36, -10, 1, 1.3);
        RunToPoint(5, -10, 5, 3);
        intakeMotor.setPower(-0.9);
        transferMotor.setPower(1);
        AutoColorSensorUse = true;
        backDepositorServo.setPosition(1);
        frontDepositorServo.setPosition(1);
        RunToPoint(-40, -10, 3, 2);
        intakeServo.setPosition(0.595);
        RunToPoint(-56, -14, 0, 0.5);
        RunToPoint(RobotX,RobotY,-1,0.2);//wait
        RunToPoint(-53, -14, 0, 0.4);
        intakeServo.setPosition(0.585);
        RunToPoint(-57.5, -14, 0, 0.15);
        RunToPoint(-55.5, -14, 0, 0.2);
        intakeServo.setPosition(0.8);
        RunToPoint(-53, -14, 0, 0.4);
        RunToPoint(-59.5, -14, 0, 0.4);
        RunToPoint(-40, -11, 3, 0.8);
        RunToPoint(40, -11, 3, 1.5);
        pixelLiftMotor.setTargetPosition(100);
        intakeMotor.setPower(0);
        transferMotor.setPower(0);
        RunToPoint(48, -19, 2, 1.5);
        AutoColorSensorUse = false;
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(1);

        RunToPoint(RobotX,RobotY,-1,1.0);
        pixelLiftMotor.setTargetPosition(0);
        setTurn(0);
        RunToPoint(RobotX,RobotY,-1,1);//wait
    }
}