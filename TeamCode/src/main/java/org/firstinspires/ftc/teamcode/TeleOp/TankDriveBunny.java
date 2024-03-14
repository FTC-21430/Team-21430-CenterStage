package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
@Disabled
public class TankDriveBunny extends LinearOpMode {
    //Defining variables
    DcMotor LeftMotor;
    DcMotor RightMotor;
    double LeftPower;
    double RightPower;

    public void ArcadeDrive(double Forwards, double Turn) {
        LeftPower = Forwards - Turn;
        RightPower = -(Forwards + Turn);
    }

    public void DriveSpeed(double SpeedScale) {
        LeftPower *= SpeedScale;
        RightPower *= SpeedScale;
    }

    //runOpMode
    @Override
    public void runOpMode() {
        //Initialize
        LeftMotor = hardwareMap.get(DcMotor.class, "Left Motor");
        RightMotor = hardwareMap.get(DcMotor.class, "Right Motor");
        //While op mode is active
        waitForStart();
        while (opModeIsActive()) {
            DriveSpeed(.5);
            ArcadeDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
            if (gamepad1.left_bumper) {
                DriveSpeed(.5);
            }
            if (gamepad1.right_bumper) {
                DriveSpeed(2);
            }
            LeftMotor.setPower(LeftPower);
            RightMotor.setPower(RightPower);
        }
    }
}
