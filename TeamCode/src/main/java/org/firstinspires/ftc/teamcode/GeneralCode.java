package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;

public abstract class GeneralCode extends Robot {
    int LiftManual = 0;
    boolean calabrate_Lift = false;

    boolean leftTurnold = false;
    boolean rightTurnold = false;


    double LiftAdd;
    boolean slowMode;
    boolean Intake;
    boolean highJunction;
    boolean mediumJunction;
    boolean lowJunction;
    boolean groundJunction;
    float fastMode;
    boolean upStack;
    boolean TurnLeft;
    boolean TurnRight;
    boolean bumper_old;
    float IntakeInput;


    public void UpdateControls() {
        drive = -gamepad1.left_stick_y;
        slide = gamepad1.left_stick_x;
        turn = gamepad1.right_stick_x;
        slowMode = gamepad1.right_bumper;
        fastMode = gamepad1.right_trigger;
        LiftAdd = gamepad2.left_stick_y;
        Intake = gamepad2.b;
        highJunction = gamepad2.dpad_up;
        mediumJunction = gamepad2.dpad_right;
        lowJunction = gamepad2.dpad_left;
        groundJunction = gamepad2.dpad_down;
        upStack = gamepad2.left_bumper;
        //toggle Driver Orientation Mode
        Driver1Leftbumper = gamepad1.left_bumper;
        if (Driver1Leftbumper && !bumper_old) {
            if (!DriverOrientationDriveMode) {
                DriverOrientationDriveMode = true;
            } else {
                DriverOrientationDriveMode = false;
            }
        }
        bumper_old = Driver1Leftbumper;
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

    public void speedControl() {
        leftFrontPower = leftFrontPower / 2;
        leftBackPower = leftBackPower / 2;
        rightFrontPower = rightFrontPower / 2;
        rightBackPower = rightBackPower / 2;
        if (fastMode == 1) {
            leftFrontPower = leftFrontPower * 2;
            leftBackPower = leftBackPower * 2;
            rightFrontPower = rightFrontPower * 2;
            rightBackPower = rightBackPower * 2;
        }
        if (slowMode) {
            leftFrontPower = leftFrontPower / 2;
            leftBackPower = leftBackPower / 2;
            rightFrontPower = rightFrontPower / 2;
            rightBackPower = rightBackPower / 2;
        }
    }

    public void setMotorPower() {
        // Send calculated power to wheels
        leftFrontMotor.setPower(leftFrontPower);
        leftBackMotor.setPower(leftBackPower);
        rightFrontMotor.setPower(rightFrontPower);
        rightBackMotor.setPower(rightBackPower);
        //Set the servo to the new position and pause;
    }

    public void CenterStageUpdateControls() {
        drive = -gamepad1.left_stick_y;
        slide = gamepad1.left_stick_x;
        turn = gamepad1.right_stick_x;
        slowMode = gamepad1.right_bumper;
        fastMode = gamepad1.right_trigger;
        IntakeInput = gamepad2.left_stick_y/ 1;
    }
//ClimberLimitSwitchBottom.getState() == false
// && climberMotor.getCurrentPosition()<=100
    public void Climber() {
        if (gamepad2.dpad_down) {
            climberMotor.setPower(1);
        } else if (gamepad2.dpad_up) {
            climberMotor.setPower(-0.9);
        } else {
            climberMotor.setPower(0);
        }
    }
    public void Intake(){
        intakeMotor.setPower(IntakeInput);
    }
}

