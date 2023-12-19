package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.FtcDashboard;

@TeleOp(name="AprilTagTesting", group="Linear Opmode")
public class AprilTagTesting extends OdometryCode{

    @Override
    public void runOpMode() throws InterruptedException {

        AprilTagInit();
        Init();
        waitForStart();
        while (opModeIsActive()){
            TESTfLeft = leftFrontMotor.getCurrentPosition();
            TESTfRight = rightFrontMotor.getCurrentPosition();
            TESTbLeft = leftBackMotor.getCurrentPosition();
            TESTbRight = rightBackMotor.getCurrentPosition();
            RobotAngles();
            UpdateEncoders();
            UpdateOdometry();
            aprilTagFindRobotPosition();
            telemetry.update();
            double l = 17.5/2;
            double[] bxPoints = {l,-l,-l,l};
            double[] byPoints = {l,l,-l,-l};
            rotatePoints(bxPoints, byPoints, RobotAngle);
            for (int i = 0; i < 4; i++){
                bxPoints[i] += RobotX;
                byPoints[i] += RobotY;
            }
            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay()
                    .setStrokeWidth(1)
                    .setStroke("goldenrod")
                    .setFill("black")
                    .fillPolygon(bxPoints, byPoints);
            dashboard.sendTelemetryPacket(packet);
            telemetry.addData("RobotX:", RobotX);
            telemetry.addData("Angle:", RobotAngle);
            telemetry.addData("RobotY:", RobotY);
        }
    }
}
