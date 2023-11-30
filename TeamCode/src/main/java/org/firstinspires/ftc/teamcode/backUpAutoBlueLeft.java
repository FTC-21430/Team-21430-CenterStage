
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;




import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.sun.tools.javac.jvm.Gen;
@Autonomous(name = "backUpAutoBlueLeft" , group = "CenterStage")
@Disabled
public class backUpAutoBlueLeft extends CameraVision {
    @Override
    public void runOpMode() throws InterruptedException {
        Init();
        CamInit();

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
//        PowerplayInit();

//        RobotX = -65;
//        RobotY = 61;

        DriverOrientationDriveMode = false;
        startOfsetRadians = 0;
//MAKE IT FASTER!!!!!!
// put autoPather Output here
        //purple pixcel placement for zone 2

//        IntakeClose();

        CamRun(5);

        if (Zone == 0){
    Zone = 2;}

        if (Zone==1) {
            RunToPoint(-14, 21);
//            IntakeOpen();}

        if (Zone==2) {
            RunToPoint(-2, 5);
            RunToPoint(0, 35);
//            IntakeOpen();}

        if (Zone==3) {
            RunToPoint(0, 5);
            Target = -90;
            RunToPoint(0, 29);
            RunToPoint(4, 29);
//            IntakeOpen();
            RunToPoint(-2, 29);
        }
        //if statments stop

        RunToPoint(-2,5);
        RunToPoint(-44,5);


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
}}}