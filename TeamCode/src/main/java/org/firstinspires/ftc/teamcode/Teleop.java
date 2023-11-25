package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Teleop", group="Linear Opmode")
public class Teleop extends OdometryCode{
    @Override
    public void runOpMode() {
        Init();
        //TODO: Add centerstage specific init
        waitForStart();
        IMUReset();
        runtime.reset();
        while (opModeIsActive()){
            CenterStageUpdateControls();
            UpdateControls();
            IMU_Update();
            if (gamepad1.y) {
                IMUReset();
            }
            telemetry.addData("endGameMode:", endGameMode);


           ProportionalFeedbackControl();
            GridRunner();
            straferAlgorithm();


            updateCommunication();
            updateColorSensors();
            lightsUpdate();
            endGameThings();
            if (endGameMode){
                intakeServo.setPosition(0.5);
            }else{
                intakeServo.setPosition(1);
            }


            stateControl();

            speedControl();

            setMotorPower();
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Operator Current State", currentState);

            telemetry.update();

        }

    }

}
