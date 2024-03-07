package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;




import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.sun.tools.javac.jvm.Gen;
    @Autonomous(name = "backUpAutoRedRight" , group = "CenterStage")
    @Disabled
    public class backUpAutoRedRight extends OldCodeForPowerPlay {
        @Override
        public void runOpMode() throws InterruptedException {
            Init();
            waitForStart();
            runtime.reset();
            leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            IMUReset();
            PowerplayInit();

//        RobotX = -65;
//        RobotY = 61;

            DriverOrientationDriveMode = false;
            startOfsetRadians = 0;
//MAKE IT FASTER!!!!!!
// put autoPather Output here
            //purple pixcel placement for zone 2

            IntakeClose();

            // if (Zone==1)
            RunToPoint(0,5,1);
            Target = 90;
            RunToPoint(0,29,1);
            RunToPoint(-4,29,1);
            IntakeOpen();
            RunToPoint(2,29,1);
            //if (Zone==2)
//        RunToPoint(2,5);
//        RunToPoint(0,37);
//        IntakeOpen();
            //if (Zone==3)
//        RunToPoint(14,21);
//        IntakeOpen();
            //if statments stop

            RunToPoint(2,5,1);
            RunToPoint(44,5,1);


            // code for just parking in the corner
            //RunToPoint(0,5);
            //RunToPoint(-92,5);



// end Here
            telemetry.addData("Y", RobotY);
            telemetry.addData("X", RobotX);
            telemetry.addData("Angle", RobotAngle);
            telemetry.addData( "a motor", FrontLeft);
            telemetry.addData("leftfrontmotorencoder", leftFrontMotor.getCurrentPosition());
            telemetry.addData("leftbackmotorencoder", leftBackMotor.getCurrentPosition());
            telemetry.addData("rightfrontmotorencoder", rightFrontMotor.getCurrentPosition());
            telemetry.addData("rightbackmoterencoder", rightBackMotor.getCurrentPosition());
            telemetry.addData("stick data",(leftFrontPower));
            telemetry.addData("stick Data Y", drive);
            telemetry.addData("dpad",gamepad1.dpad_up);
            telemetry.update();




        }
    }


