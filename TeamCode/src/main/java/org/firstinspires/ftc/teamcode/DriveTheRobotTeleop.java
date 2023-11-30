package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
@TeleOp(name="DriveTheRobotTeleop", group="Linear Opmode")
public class DriveTheRobotTeleop extends GeneralCode {
    // Wait for the game to start (driver presses PLAY)
    @Override
    public void runOpMode() {
        Init();
        waitForStart();
        runtime.reset();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            UpdateControls();
            IMU_Update();
            if (gamepad1.y) {
                IMUReset();
            }

            leftTurnold = gamepad1.b;
            rightTurnold = gamepad1.x;
            ProportionalFeedbackControl();
            GridRunner();
            straferAlgorithm();
            telemetry.addData("error", error);
            telemetry.addData("turn", turn);

            speedControl();
            setMotorPower();

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("robotAngle", robotHeading);
            telemetry.update();
        }
    }
}