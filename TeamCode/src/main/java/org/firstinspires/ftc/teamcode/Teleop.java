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
            ProportionalFeedbackControl();
            GridRunner();
            straferAlgorithm();
            Climber();
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
