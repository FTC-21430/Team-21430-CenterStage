package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name="TESTING", group="Linear Opmode")
public class TESTING extends GeneralCode {

    @Override
    public void runOpMode() {
        double height = 1;
        boolean old = false;
        Init();
fourBarServo.setPosition(1);
        waitForStart();

        while (opModeIsActive())
        {

if (gamepad2.a && !old) height = 0.83;

old = gamepad2.a;
    fourBarServo.setPosition(height);
            telemetry.addData("currentSate", currentState);
          //  telemetry.addData("LiftEncoder count:", pixelLiftMotor.getCurrentPosition());
            telemetry.update();

        }
    }

}
