package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="OLDRobotOdometryTest", group="Linear Opmode")
public class OLDRobotOdometryTest extends OdometryCode{
    @Override
    public void runOpMode() throws InterruptedException {
        OdometrypodInit();
        OdometryInit(0,0);
        waitForStart();
        IMUReset();
        RadiusX = 5.4;
        RadiusY = 7.25;

        //CamInit();
        runtime.reset();
        while (opModeIsActive()){
            IMU_Update();
            UpdateEncoders();
            UpdateOdometry();
            telemetry.addData("PodX", odometrypodx.getCurrentPosition());
            telemetry.addData("PodY", odometrypody.getCurrentPosition());
            telemetry.addData("RobotX", RobotX);
            telemetry.addData("RobotY", RobotY);
            double tLast = getRuntime();
//            imu.getRobotYawPitchRollAngles();
            odometrypodx.getCurrentPosition();
            double tNow = getRuntime();
            telemetry.addData("DeltaT", tNow-tLast);
            tLast = tNow;
            telemetry.update();
        }
    }
}