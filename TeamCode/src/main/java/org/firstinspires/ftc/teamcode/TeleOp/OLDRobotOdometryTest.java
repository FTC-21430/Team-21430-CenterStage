package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.General.OdometryCode;

@TeleOp(name = "OLDRobotOdometryTest", group = "Linear Opmode")
public class OLDRobotOdometryTest extends OdometryCode {
    @Override
    public void runOpMode() throws InterruptedException {

        OdometryInit(0, 0);
        waitForStart();
        IMUReset();
        RadiusX = 5.4;
        RadiusY = 7.25;

        runtime.reset();
        while (opModeIsActive()) {
            IMU_Update();
            UpdateEncoders();
            UpdateOdometry();

            telemetry.addData("RobotX", RobotX);
            telemetry.addData("RobotY", RobotY);
            double tLast = getRuntime();

            double tNow = getRuntime();
            telemetry.addData("DeltaT", tNow - tLast);
            tLast = tNow;
            telemetry.update();
        }
    }
}