package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.General.OctopusAutoFunctions;
@Disabled
@Autonomous(name = "TrussRedLeftCycleGround", group = "CenterStage")
public class TrussRedLeftCycleGround extends OctopusAutoFunctions {
    @Override
    public void runOpMode() {
        Init();

        DriverOrientationDriveMode = false;

        CamInit();

        pattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE;
        blinkinLedDriver.setPattern(pattern);
        TargetAngle = 0;
        AutoStartAngle = 0;
        OldAngle = AutoStartAngle;
        IsProgramAutonomous = true;

        ZoneTelemetryUntilStart();

        runtime.reset();
        RobotX = -41;
        RobotY = -62;
        InitX = -41;
        InitY = -62;
        Speed = 1;
        PurplePixelRedLeftTruss();

        CamEnd();

        AprilTagInit();
        WaitFunction();

        setTurn(90);

        RunToPoint(48, -61, 1, 1);
        RobotX = 48;
        RobotY = -62;
        InitX = 48;
        InitY = -62;
        pixelLiftMotor.setTargetPosition(100);
        intakeMotor.setPower(0);
        frontDepositorServo.setPosition(1);
        backDepositorServo.setPosition(1);
        stateMachineTimer = getRuntime();
        AutoColorSensorUse = false;
        RunToPoint(RobotX, RobotY, -1, 2);
        pixelLiftMotor.setTargetPosition(0);

        if (Zone == 1 || Zone == 2){
            RunToPoint(34,-60,1,5);

            RunToPoint(-36,-60,1,5);
            intakeServo.setPosition(PixelPickerBottom);

            transferMotor.setPower(1);
            AutoColorSensorUse = true;
            frontDepositorServo.setPosition(0.7);
            backDepositorServo.setPosition(0.7);
            intakeMotor.setPower(-0.4);
            RunToPoint(-57.5,-36,1,2);
            intakeMotor.setPower(-1);
            intakeServo.setPosition(PixelPickerBottom-0.015);
            RunToPoint(-57.5,-33,1,1.3);

            intakeServo.setPosition(PixelPickerTop);
            if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None") {intakeMotor.setPower(1);transferMotor.setPower(-1);}

            RunToPoint(-58,-32,1,1);


            if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None") {intakeMotor.setPower(1);transferMotor.setPower(-1);}
            RunToPoint(-58,-47,2,1);


            if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None") {intakeMotor.setPower(1);transferMotor.setPower(-1);}
            setTurn(90);
            RunToPoint(-40, -62, 1, 1);
            if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None") {
                intakeMotor.setPower(1);
                transferMotor.setPower(-1);
            }
            RunToPoint(48, -61, 2, 3);
        }
        else{
            RunToPoint(34,-61,1,5);

            RunToPoint(-36,-61,1,5);
            intakeServo.setPosition(PixelPickerBottom);

            transferMotor.setPower(1);
            AutoColorSensorUse = true;
            frontDepositorServo.setPosition(0.7);
            backDepositorServo.setPosition(0.7);
            intakeMotor.setPower(-0.4);
            RunToPoint(-57.5,-39,1,2);
            intakeMotor.setPower(-1);
            intakeServo.setPosition(PixelPickerBottom-0.015);
            RunToPoint(-57.5,-36,1,1.3);

            intakeServo.setPosition(PixelPickerTop);
            if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None") {intakeMotor.setPower(1);transferMotor.setPower(-1);}

            RunToPoint(-58,-36,1,1);


            if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None") {intakeMotor.setPower(1);transferMotor.setPower(-1);}
            RunToPoint(-58,-51,2,1);


            if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None") {intakeMotor.setPower(1);transferMotor.setPower(-1);}
            setTurn(90);
            RunToPoint(-40, -64, 1, 1);
            if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None") {
                intakeMotor.setPower(1);
                transferMotor.setPower(-1);
            }
            RunToPoint(48, -64, 2, 3);
            pixelLiftMotor.setTargetPosition(100);
            intakeMotor.setPower(0);
            frontDepositorServo.setPosition(1);
            backDepositorServo.setPosition(1);
            stateMachineTimer = getRuntime();
            AutoColorSensorUse = false;
            RunToPoint(RobotX, RobotY, -1, 4);
            pixelLiftMotor.setTargetPosition(0);
        }
    }
}

