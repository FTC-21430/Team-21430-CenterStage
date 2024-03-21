package org.firstinspires.ftc.teamcode.Genral;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public abstract class OdometryCode extends CameraVision {
    public double InitX, InitY;
    public double DForward, DSideways;
    public double correctionFactor = 1;
    public double ticksPerRevolution = 2000;
    public double MMPerRevolution = 48 * Math.PI;
    public double MMPerInch = 25.4;
    public double FrontLeft = 0;
    public double FrontRight = 0;
    public double BackLeft = 0;
    public double BackRight = 0;
    public double scalingDouble;
    public double FrontLeftOld, FrontRightOld, BackLeftOld, BackRightOld;
    public float TESTfLeft, TESTfRight, TESTbLeft, TESTbRight;
    public boolean GamepadAOld;
    //TODO: CONFIGURE STUFF
    public double OdometryPodOldX, OdometryPodOldY;
    public double OdometryPodX, OdometryPodY;
    public double RadiusX, RadiusY;
    public double Speed = 0.5;

    public void ProportionalFeedbackControlAuto() {
        error = Wrap(((TargetAngle - ((180 * RobotAngle) / Math.PI))));
        turn -= error / 20;
    }

    public static void rotatePoints(double[] xPoints, double[] yPoints, double angle) {
        for (int i = 0; i < xPoints.length; i++) {
            double x = xPoints[i];
            double y = yPoints[i];
            xPoints[i] = x * Math.cos(angle) - y * Math.sin(angle);
            yPoints[i] = x * Math.sin(angle) + y * Math.cos(angle);
        }
    }

    public void setTurn(float angle) {
        TargetAngle = angle;
    }

    public void OdometryInit(float x, float y) {
        RobotX = x;
        RobotY = y;
        InitX = x;
        InitY = y;
    }

    double OldAngle = RobotAngle;

    public void UpdateOdometry() {

        double DeltaRotation = RobotAngle - OldAngle;
        telemetry.addData("Delta Rotation", DeltaRotation);
        DeltaRotation = Wrap(DeltaRotation);
        DForward = OdometryPodY - RadiusY * DeltaRotation;
        DSideways = OdometryPodX - RadiusX * DeltaRotation;
        RobotX = (InitX - DForward * Math.sin(RobotAngle) + DSideways * Math.cos(RobotAngle));
        RobotY = (InitY + DForward * Math.cos(RobotAngle) + DSideways * Math.sin(RobotAngle));
        InitX = RobotX;
        InitY = RobotY;
        OldAngle = RobotAngle;
    }

    public void keepAtPoint(double Tx, double Ty) {

        distanceX = RobotX - Tx;
        distanceY = RobotY - Ty;

        PowerX = -distanceX * .55 / 3;
        PowerY = -distanceY * .4 / 3;

        PowerS = PowerX * Math.cos(-RobotAngle) - PowerY * Math.sin(-RobotAngle);
        PowerF = PowerX * Math.sin(-RobotAngle) + PowerY * Math.cos(-RobotAngle);


        scalingDouble = Math.max(Math.abs(PowerS), Math.abs(PowerF));
        if (scalingDouble >= 1) {
            PowerF /= scalingDouble;
            PowerS /= scalingDouble;
        }
        drive = PowerF;
        slide = PowerS;
        straferAlgorithm();
    }

    public void RunToPoint(double TargetX, double TargetY, double Timeout) {
        RunToPoint(TargetX, TargetY, 0.4, Timeout);
    }

    public void RunToPoint(double TargetX, double TargetY, double Circle, double Timeout) {
        double StartTime = runtime.seconds();
        while (distanceCircle(TargetX, TargetY) > Circle && opModeIsActive() && StartTime >= (runtime.seconds() - Timeout)) {
            TESTfLeft = leftFrontMotor.getCurrentPosition();
            TESTfRight = rightFrontMotor.getCurrentPosition();
            TESTbLeft = leftBackMotor.getCurrentPosition();
            TESTbRight = rightBackMotor.getCurrentPosition();
            IMU_Update();
            UpdateEncoders();
            UpdateOdometry();
            if (aprilTagProcessorActive) {
                float[] arrayOutput = aprilTagFindRobotPosition();
                if (arrayOutput[0] != 0 && arrayOutput[1] != 0 && arrayOutput[2] != 0) {
                    RobotX = arrayOutput[0];
                    RobotY = arrayOutput[1];
                    InitX = arrayOutput[0];
                    InitY = arrayOutput[1];
                    AutoStartAngle -= RobotAngle - arrayOutput[2];
                }
            }

            double l = 17.5 / 2;
            double[] bxPoints = {l, -l, -l, l};
            double[] byPoints = {l, l, -l, -l};
            rotatePoints(bxPoints, byPoints, RobotAngle);
            for (int i = 0; i < 4; i++) {
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
            telemetry.addData("distance", distanceCircle(TargetX, TargetY));
            telemetry.addData("Y", RobotY);
            telemetry.addData("X", RobotX);
            telemetry.addData("Angle", RobotAngle);
            telemetry.addData("a motor", FrontLeft);

            telemetry.addData("Zone", Zone);
            telemetry.update();
            TESTfLeft = leftFrontMotor.getCurrentPosition();
            TESTfRight = rightFrontMotor.getCurrentPosition();
            TESTbLeft = leftBackMotor.getCurrentPosition();
            TESTbRight = rightBackMotor.getCurrentPosition();
            turn = 0;
//THIS IS HERE FOR AUTONOMOUS PURPOSES
            if (ColorSensorCheck(frontColorSensor) != "None") {
                frontDepositorServo.setPosition(.5);
            }
            if (ColorSensorCheck(frontColorSensor) != "None" && ColorSensorCheck(backColorSensor) != "None") {
                frontDepositorServo.setPosition(.5);
                backDepositorServo.setPosition(.5);
            }

            ProportionalFeedbackControl();
            UpdateEncoders();

            UpdateOdometry();
            keepAtPoint(TargetX, TargetY);
            GridRunner();

            straferAlgorithm();
            leftFrontPower = leftFrontPower * Speed;
            leftBackPower = leftBackPower * Speed;
            rightFrontPower = rightFrontPower * Speed;
            rightBackPower = rightBackPower * Speed;
            setMotorPower();


        }
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);

    }

    public double distanceCircle(double x, double y) {
        return (Math.sqrt((x - RobotX) * (x - RobotX) + (y - RobotY) * (y - RobotY)));
    }

    public void AlignWithBackdrop(double TargetX) {
        TESTfLeft = leftFrontMotor.getCurrentPosition();
        TESTfRight = rightFrontMotor.getCurrentPosition();
        TESTbLeft = leftBackMotor.getCurrentPosition();
        TESTbRight = rightBackMotor.getCurrentPosition();
        TargetAngle = 90;
        UpdateControls();
        IMU_Update();
        UpdateEncoders();
        UpdateOdometry();
        float[] arrayOutput = aprilTagFindRobotPosition();
        if (arrayOutput[0] != 0 && arrayOutput[1] != 0 && arrayOutput[2] != 0) {
            RobotX = arrayOutput[0];
            RobotY = arrayOutput[1];
            InitX = arrayOutput[0];
            InitY = arrayOutput[1];
            AutoStartAngle -= RobotAngle - arrayOutput[2];
        }
        telemetry.update();
        double l = 17.5 / 2;
        double[] bxPoints = {l, -l, -l, l};
        double[] byPoints = {l, l, -l, -l};
        rotatePoints(bxPoints, byPoints, RobotAngle);
        for (int i = 0; i < 4; i++) {
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
        telemetry.addData("zone", Zone);
        telemetry.addData("Y", RobotY);
        telemetry.addData("X", RobotX);
        telemetry.addData("Angle", RobotAngle);
        telemetry.addData("a motor", FrontLeft);

        TESTfLeft = leftFrontMotor.getCurrentPosition();
        TESTfRight = rightFrontMotor.getCurrentPosition();
        TESTbLeft = leftBackMotor.getCurrentPosition();
        TESTbRight = rightBackMotor.getCurrentPosition();
        turn = 0;

        if (CurrentAlign) keepAtPoint(TargetX, RobotY);
        leftFrontPower = leftFrontPower / 2;
        leftBackPower = leftBackPower / 2;
        rightFrontPower = rightFrontPower / 2;
        rightBackPower = rightBackPower / 2;
    }

    public void WaitFunction() {
        telemetry.addData("runtime", runtime.seconds());
        while (Delay >= runtime.seconds() && opModeIsActive()) {
            ProportionalFeedbackControl();
            telemetry.addData("runtime", getRuntime());
            telemetry.update();
        }
    }

    public void UpdateEncoders() {
        OdometryPodX = odometrypodx.getCurrentPosition();
        OdometryPodY = odometrypody.getCurrentPosition();
        double tempX = OdometryPodX - OdometryPodOldX;
        double tempY = OdometryPodY - OdometryPodOldY;

        OdometryPodOldX = OdometryPodX;
        OdometryPodOldY = OdometryPodY;
        OdometryPodX = tempX;
        OdometryPodY = tempY;

        OdometryPodX = OdometryPodX / ticksPerRevolution;
        OdometryPodX = MMPerRevolution * OdometryPodX;
        OdometryPodX = OdometryPodX / MMPerInch;
        OdometryPodX *= correctionFactor;

        OdometryPodY = OdometryPodY / ticksPerRevolution;
        OdometryPodY = MMPerRevolution * OdometryPodY;
        OdometryPodY = OdometryPodY / MMPerInch;
        OdometryPodY *= correctionFactor;
    }

    YawPitchRollAngles orientation;
    AngularVelocity angularVelocity;
}