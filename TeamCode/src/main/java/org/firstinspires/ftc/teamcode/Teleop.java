package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Teleop", group="Linear Opmode")
public class Teleop extends OdometryCode{
    @Override
    public void runOpMode() {
        Init();
        //TODO: Add centerstage specific init
        waitForStart();
        runtime.reset();
        while (opModeIsActive()){
            CenterStageUpdateControls();
            IMUstuffs();
            if (gamepad1.y) {
                IMUReset();
            }
           // ProportionalFeedbackControl();
            GridRunner();
            straferAlgorithm();
            Climber();

            intakeMotor.setPower(gamepad2.left_stick_y);
            intakeServo.setPosition(gamepad2.left_stick_x);
            transferMotor.setPower(gamepad2.right_stick_y);
            if (gamepad2.dpad_up){
                frontDepositorServo.setPosition(1);
                backDepositorServo.setPosition(1);
            }
            if (gamepad2.dpad_down){
                frontDepositorServo.setPosition(-1);
                backDepositorServo.setPosition(-1);
            }
            telemetry.addData("error", error);
            telemetry.addData("turn", turn);
            if (gamepad1.y) {
                IMUReset();
            }
            speedControl();
            setMotorPower();
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("robotAngle", robotHeading);
            telemetry.update();
        }

    }

}
