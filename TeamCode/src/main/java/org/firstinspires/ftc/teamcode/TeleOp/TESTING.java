package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TESTING", group = "Linear Opmode")
public class TESTING extends LinearOpMode {
    public DcMotor transferMotor = null;

    @Override

    public void runOpMode() {
        transferMotor = hardwareMap.get(DcMotor.class, "TransferMotor");
        waitForStart();
        while (opModeIsActive()) {
            float motorPower = gamepad1.left_stick_y;
            if (gamepad1.a) {
                transferMotor.setPower(motorPower);
            }else{
                transferMotor.setPower(motorPower/2);
            }

    }
    }
}
