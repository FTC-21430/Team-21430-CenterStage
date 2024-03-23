package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.General.AutoFunctionsWORLDS;
import org.firstinspires.ftc.teamcode.General.OctopusAutoFunctions;

@Autonomous(name = "BlueRightOctopusasdfasdf", group = "CenterStage")
public class BlueRightAdvancedWORLDS extends AutoFunctionsWORLDS {
    @Override
    public void runOpMode() {
        Init();

        DriverOrientationDriveMode = false;

        CamInit();

        pattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE;
        blinkinLedDriver.setPattern(pattern);
        robotHeading = -Math.PI;
        TargetAngle = -180;
        AutoStartAngle = -Math.PI;
        IsProgramAutonomous = true;

        ZoneTelemetryUntilStart();

        runtime.reset();
        RobotX = -38;
        RobotY = 62;
        InitX = -38;
        InitY = 62;
        Speed = 0.8;


        CamEnd();
        AprilTagInit();


        Speed = 0.5;

        Speed = 0.8;


        pixelLiftMotor.setTargetPosition(0);
        while (stateMachineTimer >= getRuntime() - 5 && opModeIsActive()) {
        }
    }
}
