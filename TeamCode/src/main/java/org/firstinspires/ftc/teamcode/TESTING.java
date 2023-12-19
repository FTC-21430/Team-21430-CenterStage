package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name="TESTING", group="Linear Opmode")
public class TESTING extends GeneralCode {

    @Override
    public void runOpMode() {
        double height = 0.96;
        boolean old = false;
        boolean oldB = false;
        Init();
        waitForStart();
        while (opModeIsActive()){
            // .99
            // .015
            if (gamepad2.a) height += 0.0008;
            if (gamepad2.b) height -= 0.0008;
            if (height >= 0.96) height = 0.96;
            if (height <= 0) height = 0;
            fourBarServo.setPosition(height);
            telemetry.addData("currentSate", currentState);
            telemetry.addData("height", height);
            telemetry.addData("oldA", old);
            telemetry.addData("oldB", oldB);
            //  telemetry.addData("LiftEncoder count:", pixelLiftMotor.getCurrentPosition());
            telemetry.update();
            old = gamepad2.a;
            oldB = gamepad2.b;
        }


        }
    }


