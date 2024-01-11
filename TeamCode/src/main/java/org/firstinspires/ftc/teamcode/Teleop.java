package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Teleop", group="Linear Opmode")
public class Teleop extends OdometryCode{
    double AlignWait;
    @Override

    public void runOpMode() {
        Init();
        AprilTagInit();
        //TODO: Add centerstage specific init
        waitForStart();
        IMUReset();

        //CamInit();
        runtime.reset();
        while (opModeIsActive()){
            CenterStageUpdateControls();
            UpdateControls();
            IMU_Update();
            liftPowerControl();
            if (gamepad1.b) scoringAngle = current;
            if (gamepad1.y) {
                IMUReset();
            }
            if (gamepad1.a) {
                AlignWithBackdrop(48.5);
            }
            if (gamepad1.a && !GamepadAOld){
                CurrentAlign = true;
                AlignWait = getRuntime();
            }else{
            if (gamepad1.dpad_up || gamepad1.dpad_down || gamepad1.left_stick_y > 0.7 || gamepad1.left_stick_y < -0.7 && AlignWait > getRuntime() - 0.8){
                CurrentAlign = false;
            }
            }
            telemetry.addData("endGameMode:", endGameMode);


           ProportionalFeedbackControl();
            GridRunner();
            if (gamepad1.a){
                drive /= 1.5;
            }
            speedControl();
            straferAlgorithm();
            UpdateEncoders();
            UpdateOdometry();

            updateCommunication();
            updateColorSensors();
            lightsUpdate();
            endGameThings();
            if (endGameMode){
                intakeServo.setPosition(0.5);
            }else{
                intakeServo.setPosition(1);
            }


            intakeMotor.setPower(0);
            transferMotor.setPower(0);
            stateControl();

            setMotorPower();
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Operator Current State", currentState);
            telemetry.addData("autoDriving", CurrentAlign);
            telemetry.addData("Color Sensor Readings", ColorSensorCheck(frontColorSensor));
            telemetry.update();
            GamepadAOld = gamepad1.a;

        }

    }

}
