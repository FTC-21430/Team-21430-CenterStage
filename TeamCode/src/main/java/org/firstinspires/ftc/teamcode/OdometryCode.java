package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import java.sql.Time;

public abstract class OdometryCode extends CameraVision {
    public double InitX, InitY;
    public double DForward, DSideways;
    public double correctionFactor = 1.016723060905778;
    public double ticksPerRevolution = 537.7;
    public double MMPerRevolution = 96*Math.PI;
    public double MMPerInch = 25.4;
    public double FrontLeft = 0;
    public double FrontRight=0;
    public double BackLeft=0;
    public double BackRight=0;
    public double scalingDouble;
    public double FrontLeftOld,FrontRightOld,BackLeftOld,BackRightOld;
    public float TESTfLeft, TESTfRight, TESTbLeft, TESTbRight;

    public boolean GamepadAOld;
public double Speed = 0.5;

    public void ProportionalFeedbackControlAuto(){
        error = Wrap(((Target - ((180 * RobotAngle) /Math.PI))));
        turn -= error/20;
    }
    public static void rotatePoints(double[] xPoints, double[] yPoints, double angle){
        for (int i = 0; i < xPoints.length; i++) {
            double x = xPoints[i];
            double y = yPoints[i];
            xPoints[i] = x * Math.cos(angle) - y * Math.sin(angle);
            yPoints[i] = x * Math.sin(angle) + y * Math.cos(angle);
        }
    }

public void setTurn(float angle){
    Target = angle;
}

    public void OdometryInit(float x, float y){
        RobotX = x;
        RobotY = y;
        InitX = x;
        InitY = y;
    }

    public void UpdateOdometry(){
        DForward = (FrontRight + FrontLeft + BackRight + BackLeft)/4;
        DSideways = (-FrontRight + FrontLeft + BackRight - BackLeft)/4/1.2;
        RobotX = (InitX - DForward * Math.sin(RobotAngle)+ DSideways * Math.cos(RobotAngle));
        RobotY = (InitY + DForward * Math.cos(RobotAngle)+ DSideways * Math.sin(RobotAngle));
        InitX = RobotX;
        InitY = RobotY;
    }
    public void keepAtPoint(double Tx, double Ty) {

        distanceX = RobotX - Tx;
        distanceY = RobotY - Ty;

        PowerX = -distanceX * .55 /3;
        PowerY = -distanceY * .4 /3;

        PowerS = PowerX * Math.cos(-RobotAngle) - PowerY * Math.sin(-RobotAngle);
        PowerF = PowerX * Math.sin(-RobotAngle) + PowerY * Math.cos(-RobotAngle);


        scalingDouble = Math.max(Math.abs(PowerS),Math.abs(PowerF));
        if (scalingDouble >= 1){
            PowerF/= scalingDouble;
            PowerS/= scalingDouble;
        }
        drive = PowerF;
        slide = PowerS;
        straferAlgorithm();


    }
    public void RunToPoint(double TargetX, double TargetY, double Timeout) {
        RunToPoint(TargetX, TargetY, 0.4, Timeout);
    }
    public void RunToPoint(double TargetX, double TargetY, double Circle, double Timeout){
        double StartTime = runtime.seconds();
        while(distanceCircle(TargetX, TargetY) > Circle && opModeIsActive() && StartTime >= (runtime.seconds() - Timeout))
        {
            TESTfLeft = leftFrontMotor.getCurrentPosition();
            TESTfRight = rightFrontMotor.getCurrentPosition();
            TESTbLeft = leftBackMotor.getCurrentPosition();
            TESTbRight = rightBackMotor.getCurrentPosition();
            IMU_Update();
            UpdateEncoders();
            UpdateOdometry();
            if(aprilTagProcessorActive) {
                float[] arrayOutput = aprilTagFindRobotPosition();
                if (arrayOutput[0] != 0 && arrayOutput[1] != 0 && arrayOutput[2] != 0) {
                    RobotX = arrayOutput[0];
                    RobotY = arrayOutput[1];
                    InitX = arrayOutput[0];
                    InitY = arrayOutput[1];
                    startOfsetRadians -= RobotAngle - arrayOutput[2];
                }
            }
          //  telemetry.update();
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
          //  telemetry.addData("zone", Zone);
            telemetry.addData("distance",distanceCircle(TargetX,TargetY));
            telemetry.addData("Y", RobotY);
            telemetry.addData("X", RobotX);
            telemetry.addData("Angle", RobotAngle);
            telemetry.addData( "a motor", FrontLeft);

            telemetry.addData("Zone",Zone);
            telemetry.update();
            TESTfLeft = leftFrontMotor.getCurrentPosition();
            TESTfRight = rightFrontMotor.getCurrentPosition();
            TESTbLeft = leftBackMotor.getCurrentPosition();
            TESTbRight = rightBackMotor.getCurrentPosition();
            //UpdateControls();
//            drive = -gamepad1.left_stick_y;
//            slide = gamepad1.left_stick_x;
             turn = 0;



            // IMU_Update();
            ProportionalFeedbackControl();
//            ProportionalFeedbackControlAuto();
            UpdateEncoders();

            UpdateOdometry();
            keepAtPoint(TargetX, TargetY);
            GridRunner();

            straferAlgorithm();
            leftFrontPower=leftFrontPower * Speed;
            leftBackPower = leftBackPower * Speed;
            rightFrontPower = rightFrontPower * Speed;
            rightBackPower = rightBackPower *Speed;
//            if (leftFrontPower <= 0.18 && leftFrontPower>=-0.07) leftFrontPower = 0.07;
//            if (leftBackPower <= 0.18  && rightFrontPower>=-0.07) leftBackPower = 0.07;
//            if (rightFrontPower <= 0.18  && leftBackPower>=-0.07) rightFrontPower = 0.07;
//            if (rightBackPower <= 0.18  && rightBackPower>=-0.07) rightBackPower = 0.07;
//            if (leftFrontPower >= -0.18 && leftFrontPower<=-0.07) leftFrontPower = -0.07;
//            if (leftBackPower >= -0.18  && rightFrontPower<=-0.07) leftBackPower = -0.07;
//            if (rightFrontPower >= -0.18  && leftBackPower<=-0.07) rightFrontPower = -0.07;
//            if (rightBackPower >= -0.18  && rightBackPower<=-0.07) rightBackPower = -0.07;
            setMotorPower();


        }
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);

    }

    public double distanceCircle(double x, double y){
        return(Math.sqrt((x-RobotX)*(x-RobotX) + (y-RobotY)*(y-RobotY)));
    }


    public void AlignWithBackdrop (double TargetX){


            TESTfLeft = leftFrontMotor.getCurrentPosition();
            TESTfRight = rightFrontMotor.getCurrentPosition();
            TESTbLeft = leftBackMotor.getCurrentPosition();
            TESTbRight = rightBackMotor.getCurrentPosition();
            Target = 90;
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
                startOfsetRadians -= RobotAngle - arrayOutput[2];
            }
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
            telemetry.addData("zone", Zone);
          //  telemetry.addData("distance",distanceCircle(TargetX,TargetY));
            telemetry.addData("Y", RobotY);
            telemetry.addData("X", RobotX);
            telemetry.addData("Angle", RobotAngle);
            telemetry.addData( "a motor", FrontLeft);

            TESTfLeft = leftFrontMotor.getCurrentPosition();
            TESTfRight = rightFrontMotor.getCurrentPosition();
            TESTbLeft = leftBackMotor.getCurrentPosition();
            TESTbRight = rightBackMotor.getCurrentPosition();

//            drive = -gamepad1.left_stick_y;
//            slide = gamepad1.left_stick_x;
            turn = 0;

            if (CurrentAlign) keepAtPoint(TargetX, RobotY);
            leftFrontPower=leftFrontPower / 2;
            leftBackPower = leftBackPower / 2;
            rightFrontPower = rightFrontPower / 2;
            rightBackPower = rightBackPower / 2;


    }

    public void UpdateEncoders() {
        FrontLeft = TESTfLeft;
        FrontRight = TESTfRight;
        BackLeft = TESTbLeft;
        BackRight = TESTbRight;

        FrontLeft -= FrontLeftOld;
        BackLeft -= BackLeftOld;
        FrontRight -= FrontRightOld;
        BackRight -= BackRightOld;

        FrontLeftOld = TESTfLeft;
        FrontRightOld = TESTfRight;
        BackLeftOld = TESTbLeft;
        BackRightOld = TESTbRight;

        FrontLeft = FrontLeft / ticksPerRevolution;
        FrontLeft = MMPerRevolution * FrontLeft;
        FrontLeft = FrontLeft / MMPerInch;
        FrontLeft *= correctionFactor;

        FrontRight = FrontRight / ticksPerRevolution;
        FrontRight = MMPerRevolution * FrontRight;
        FrontRight = FrontRight / MMPerInch;
        FrontRight *= correctionFactor;

        BackRight = BackRight / ticksPerRevolution;
        BackRight = MMPerRevolution * BackRight;
        BackRight = BackRight / MMPerInch;
        BackRight *= correctionFactor;

        BackLeft = BackLeft / ticksPerRevolution;
        BackLeft = MMPerRevolution * BackLeft;
        BackLeft = BackLeft / MMPerInch;
        BackLeft *= correctionFactor;

    }

    YawPitchRollAngles orientation;
    AngularVelocity angularVelocity;



    }
