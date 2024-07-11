package org.firstinspires.ftc.teamcode.General;

import android.view.View;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Config
public abstract class Robot {
    private IMU imu;
    private double TargetAngle = 0;
    private double RobotAngle = 0;
    private double drive;
    private double slide;
    private double turn;
    private double RobotX, RobotY;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor rightBackMotor = null;
    FtcDashboard dashboard;
    private double robotHeading;
    private double lastTimeAngle;
    private boolean CurrentAlign = true;
    private boolean DriverOrientationDriveMode = true;
    private double leftFrontPower;
    private double leftBackPower;
    private double rightFrontPower;
    private double rightBackPower;
    public void straferAlgorithm() {

        if (DriverOrientationDriveMode == true) {

            double temp = drive * Math.cos(-robotHeading) + slide * Math.sin(-robotHeading);
            slide = -drive * Math.sin(-robotHeading) + slide * Math.cos(-robotHeading);
            if (!CurrentAlign) drive = temp;
        }

        leftFrontPower = Range.clip(drive + slide + turn, -1.0, 1.0);
        leftBackPower = Range.clip(drive - slide + turn, -1.0, 1.0);
        rightFrontPower = Range.clip(drive - slide - turn, -1.0, 1.0);
        rightBackPower = Range.clip(drive + slide - turn, -1.0, 1.0);

    }

    public void setMotorPower() {
        // Send calculated power to wheels
        leftFrontMotor.setPower(leftFrontPower);
        leftBackMotor.setPower(leftBackPower);
        rightFrontMotor.setPower(rightFrontPower);
        rightBackMotor.setPower(rightBackPower);
        //Set the servo to the new position and pause;
    }
    private boolean resettingImu = false;
    private double AutoStartAngle = 0;

    public void IMU_Update() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        if (orientation.getRoll(AngleUnit.DEGREES) == 0 && orientation.getPitch(AngleUnit.DEGREES) == 0
                && orientation.getYaw(AngleUnit.DEGREES) == 0) {
            if (!resettingImu) {
                telemetry.addData("IMU failed?", "Re-initializing!");
                resettingImu = true;
                RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
                RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
                RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
                imu.initialize(new IMU.Parameters(orientationOnRobot));
            }
        } else {
            resettingImu = false;
        }
        telemetry.addData("resettingIMU", resettingImu);
        AngularVelocity angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);

        robotHeading = orientation.getYaw(AngleUnit.RADIANS);
        RobotAngle = orientation.getYaw(AngleUnit.RADIANS);
        RobotAngle += AutoStartAngle;
        telemetry.addData("Yaw (Z)", "%.2f Rad. (Heading)", RobotAngle);
    }

    public void IMUReset() {
        telemetry.addData("Yaw", "Reset" + "ing\n");
        imu.resetYaw();
        TargetAngle = 0;
    }

    private double lastErrorAngle;
    private boolean IsProgramAutonomous;

    public void ProportionalFeedbackControl() {
        double currentTime = getRuntime();
        double derivativeAngle;
        double error = 0;
        if (resettingImu)
            return;
        telemetry.addData("target", TargetAngle);
        error = Wrap((TargetAngle/180)*Math.PI - RobotAngle)*180/Math.PI;
        derivativeAngle = (error - lastErrorAngle)/(currentTime - lastTimeAngle);
        if (gamepad1.right_stick_x != 0 || turnTimer + 0.3 >= getRuntime()) {
            if (!IsProgramAutonomous) {
                TargetAngle = (RobotAngle * 180 / Math.PI);
            }
        }
        if (gamepad1.right_stick_x == 0 && !TurnOLD) {
            turnTimer = getRuntime();
        }

        if (gamepad1.right_stick_x != 0) TurnOLD = false;
        if (gamepad1.right_stick_x == 0) TurnOLD = true;
        telemetry.addData("ERROR", error);
        telemetry.addData("BEFORE", turn);
        turn -= error * proportionalConstantAngle + (derivativeConstantAngle * derivativeAngle);
        telemetry.addData("AFTER", turn);
        lastTimeAngle = currentTime;
        lastErrorAngle = error;
    }

    double Wrap(double angle) {
        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    public void GridRunner() {
        if (gamepad1.dpad_up) {
            drive = 1;
            slide = 0;
        }
        if (gamepad1.dpad_left) {
            drive = 0;
            slide = -1;
        }
        if (gamepad1.dpad_right) {
            drive = 0;
            slide = 1;
        }
        if (gamepad1.dpad_down) {
            drive = -1;
            slide = 0;
        }
    }

    public boolean slowMode;

    public void speedControl() {
        drive /= 2;
        slide /= 2;
        turn /= 2;
        if (fastMode == 1) {
            drive *= 2;
            slide *= 2;
            turn *= 2;
        }
        if (slowMode) {
            drive /= 2;
            slide /= 2;
            turn *= 0.8;

        }
    }
}