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
            UpdateControls();
            IMUstuffs();
            if (gamepad1.y) {
                IMUReset();
            }
           // ProportionalFeedbackControl();
            GridRunner();
            straferAlgorithm();


            updateCommunication();
            updateColorSensors();
            lightsUpdate();


            stateControl();

            speedControl();

            setMotorPower();
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Operator Current State", currentState);

            telemetry.update();
        }

    }

}
