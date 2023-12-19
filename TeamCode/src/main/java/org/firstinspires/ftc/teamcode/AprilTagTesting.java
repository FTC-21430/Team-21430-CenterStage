package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="AprilTagTesting", group="Linear Opmode")
public class AprilTagTesting extends CameraVision{

    @Override
    public void runOpMode() throws InterruptedException {
        AprilTagInit();
        waitForStart();
        while (opModeIsActive()){
            aprilTagFindRobotPosition();
            telemetry.update();
        }
    }
}
