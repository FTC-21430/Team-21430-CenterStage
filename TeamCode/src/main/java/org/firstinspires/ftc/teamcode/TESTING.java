package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TESTING", group="Linear Opmode")
public class TESTING extends OdometryCode{
    double AlignWait;
    @Override

    public void runOpMode() {
        Init();

        //TODO: Add centerstage specific init
        waitForStart();
        IMUReset();

        //CamInit();
        runtime.reset();
        while (opModeIsActive()){

            telemetry.addData("wor","king");
            CenterStageUpdateControls();
            UpdateControls();
            IMU_Update();
          if (gamepad2.a){
              frontDepositorServo.setPosition(1);
              telemetry.addData("pos ", 1);
          }
          if (gamepad2.b){
              frontDepositorServo.setPosition(0);
              telemetry.addData("pos ", 0);
          }
            telemetry.update();
        }
    }

}
