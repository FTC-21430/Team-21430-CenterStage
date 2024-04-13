package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.General.OctopusAutoFunctions;
import org.firstinspires.ftc.teamcode.General.Robot;
@Autonomous(name = "RedRightWhitePixelBackdrop", group = "CenterStage")
@Disabled
public class RedRightWhitePixelBackdropV1 extends OctopusAutoFunctions {
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
        RobotX = 14;
        RobotY = 62;
        InitX = 14;
        InitY = 62;
        Speed = 1;
        CamEnd();

        AprilTagInit();
        WaitFunction();

        RunToPoint(17,57,2,0.8);
        setTurn(90);
        intakeServo.setPosition(PixelPickerBottom+0.04);
        RunToPoint(36,45,3,1.3);

        RunToPoint(39,37,2,0.8);

        YellowPixelBlue(true);
        if (Zone == 3){
            RunToPoint(13.8,31,2,3);
            intakeMotor.setPower(0.5);
            RunToPoint(RobotX,RobotY,-1,.4);
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
        }else if(Zone == 2){
            RunToPoint(27,22,2,2.5);
            intakeMotor.setPower(0.5);
            RunToPoint(RobotX,RobotY,-1,.4);
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);
        }else{
            RunToPoint(35,29.5,2,2);
            intakeMotor.setPower(0.5);
            RunToPoint(RobotX,RobotY,-1,.4);
            intakeMotor.setPower(0);
            intakeServo.setPosition(PixelPickerTop);

        }
        if (Zone == 2 || Zone ==3){
            RunToPoint(31,44,4,2);
        }else{
            RunToPoint(48,58,4,2);
        }
        setTurn(-180);
        RunToPoint(46,58,1,2);

    }
}

